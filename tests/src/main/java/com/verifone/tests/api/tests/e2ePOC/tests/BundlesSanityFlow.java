package com.verifone.tests.api.tests.e2ePOC.tests;

import com.google.gson.Gson;
import com.verifone.tests.api.tests.e2ePOC.DTO.bundles.AssignAppToBundleRequestBody;
import com.verifone.tests.api.tests.e2ePOC.DTO.bundles.BundlesData;
import com.verifone.tests.api.tests.e2ePOC.DTO.bundles.BundlesResponse;
import com.verifone.tests.api.tests.e2ePOC.DTO.bundles.CreateUpdateBundleRequestBody;
import com.verifone.tests.api.tests.e2ePOC.DTO.internalCustomObjects.ApiResponse;
import com.verifone.tests.api.tests.e2ePOC.helpers.LoginHelper;
import com.verifone.tests.api.tests.e2ePOC.helpers.api.BundlesApiHandler;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BundlesSanityFlow extends BaseTest {
    private Gson jsonParser = new Gson();
    private BundlesApiHandler bundlesHandler;
    private String eoToken;
    //private String createdbundleId;
    private BundlesData createdBundle;

    @BeforeClass
    @Parameters("env")
    public void preconditions(String env) throws Exception {
        eoToken = LoginHelper.getRequestToken(env, "vfameo@getnada.com", "Veri1234");
        bundlesHandler = new BundlesApiHandler(env);

    }


    @Test(priority = 100)
    public void createBundleTest() throws Exception {
        //create a bundle
        ApiResponse createBundleResponse = bundlesHandler.doCreateBundle(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", "DELETE_ME_Automation-" + System.currentTimeMillis());
        BundlesData createdBundle = jsonParser.fromJson(createBundleResponse.getResponseBody(), BundlesData.class);
        System.out.println("Created bundle ID = " + createdBundle.getId());
        Assert.assertEquals(createBundleResponse.getResponseCode().intValue(), 201, "Unexpected Response code");
        Assert.assertNotNull(createdBundle.getId());
        //createdbundleId = createdBundle.getId();
        this.createdBundle = createdBundle;

    }

    @Test(priority = 101, dependsOnMethods = {"createBundleTest"})
    public void getAllEoBundles() throws Exception {
        //Get all bundles for EO and check if created bundle is in the list
        ApiResponse getAllEoBundlesResponse = bundlesHandler.getAllBundlesForEo(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2");
        BundlesResponse allBundlesResponse = jsonParser.fromJson(getAllEoBundlesResponse.getResponseBody(), BundlesResponse.class);

        //Check if created bundle is presented in the list
        BundlesData foundBundle = allBundlesResponse.getData().stream().filter(bundle -> StringUtils.equalsIgnoreCase(bundle.getId(), createdBundle.getId())).findFirst().orElse(null);
        Assert.assertNotNull(foundBundle, "Check that created bundle returned in the list of bundles for EO");

    }

    @Test(priority = 102, dependsOnMethods = {"createBundleTest"})
    public void getBundleById() throws Exception {
        //get bundle by ID
        ApiResponse bundleByIdResp = bundlesHandler.getBundleById(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundle.getId());
        BundlesResponse bundlesByIdRespObject = jsonParser.fromJson(bundleByIdResp.getResponseBody(), BundlesResponse.class);

        System.out.println("Get bundle by ID request returned " + bundlesByIdRespObject.getData().size() + " bundles");
        Assert.assertEquals(bundlesByIdRespObject.getData().size(), 1, "Check that api returned 1 created bundle by id");

    }


    @Test(priority = 103, dependsOnMethods = {"createBundleTest"})
    public void updateBundle() throws Exception {
        //update a bundle
        String newBundleName = "UPDATED_" + createdBundle.getBundleName();
        String newBundleDesc = "UPDATED_BundleDesc";

        CreateUpdateBundleRequestBody updateBundleRequest = new CreateUpdateBundleRequestBody(newBundleName, newBundleDesc, null, null);
        ApiResponse updateResp = bundlesHandler.doUpdateBundle(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundle.getId(), updateBundleRequest);
        System.out.println("Update bundle response code = " + updateResp.getResponseCode());
        Assert.assertEquals(updateResp.getResponseCode().intValue(), 200, "Unexpected response code");

        BundlesData updatedBundle = jsonParser.fromJson(updateResp.getResponseBody(), BundlesData.class);

        Assert.assertEquals(updatedBundle.getBundleName(), newBundleName, "Bundle name was not updated correctly");
        Assert.assertEquals(updatedBundle.getBundleDesc(), newBundleDesc, "Bundle desc was not updated correctly");

    }


    @Test(priority = 104, dependsOnMethods = {"createBundleTest"})
    public void assignFreeAppToBundle() throws Exception {
        //Assign app to bundle
        AssignAppToBundleRequestBody assignAppsRequest = new AssignAppToBundleRequestBody(qa_free_app);
        ApiResponse assignAppResp = bundlesHandler.doAssignAppsToBundle(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundle.getId(), assignAppsRequest);
        Assert.assertEquals(assignAppResp.getResponseCode().intValue(), 201, "Unexpected response code");

        //verify assigned app is returned in get bundle by id request
        ApiResponse bundleByIdResp = bundlesHandler.getBundleById(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundle.getId());
        BundlesResponse bundlesByIdRespObject = jsonParser.fromJson(bundleByIdResp.getResponseBody(), BundlesResponse.class);

        Assert.assertTrue(bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().size() > 0, "List of assigned apps is empty");
        Assert.assertTrue(bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().contains(qa_free_app), "Verify Id of assigned app is presented in the assigned apps list for bundle");

    }


    @Test(priority = 105, dependsOnMethods = {"createBundleTest", "assignFreeAppToBundle"})
    public void unassignAppFromBundle() throws Exception {
        //unassign app from bundle

        ApiResponse unassignAppResponse = bundlesHandler.doUnassignAppsToBundle(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundle.getId(), qa_free_app);
        System.out.println("Unassign bundle response: " + unassignAppResponse.getResponseCode());
        Assert.assertEquals(unassignAppResponse.getResponseCode().intValue(), 204, "Unexpected response code");

        ApiResponse bundleByIdResp = bundlesHandler.getBundleById(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundle.getId());
        BundlesResponse bundlesByIdRespObject = jsonParser.fromJson(bundleByIdResp.getResponseBody(), BundlesResponse.class);

        Assert.assertEquals(bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().size(), 0,
                "List of assigned apps is not empty: " + bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().toString());


    }


    @Test(priority = 106, dependsOnMethods = {"createBundleTest"})
    public void deleteBundle() throws Exception {
        //delete a bundle
        ApiResponse deleteBundleResponse = bundlesHandler.doDeleteBundle(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundle.getId());
        System.out.println("Delete bundle response code is:" + deleteBundleResponse.getResponseCode());
        Assert.assertEquals(deleteBundleResponse.getResponseCode().intValue(), 204, "Unexpected response code");

        ApiResponse bundleByIdResp = bundlesHandler.getBundleById(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundle.getId());
        Assert.assertEquals(bundleByIdResp.getResponseCode().intValue(), 404, "Unexpected response code when requested deleted bundle");
    }


}
