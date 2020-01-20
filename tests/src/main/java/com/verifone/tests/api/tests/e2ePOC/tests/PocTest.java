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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PocTest extends BaseTest {
    private Gson jsonParser = new Gson();
    private BundlesApiHandler bundlesHandler;
    private String eoToken;
    private String createdbundleId;

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
        createdbundleId = createdBundle.getId();

    }

    @Test(priority = 101, dependsOnMethods = {"createBundleTest"})
    public void getAllEoBundles() throws Exception {
        //Get all bundles for EO and check if created bundle is in the list
        ApiResponse getAllEoBundlesResponse = bundlesHandler.getAllBundlesForEo(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2");
        BundlesResponse allBundlesResponse = jsonParser.fromJson(getAllEoBundlesResponse.getResponseBody(), BundlesResponse.class);

        //Check if created bundle is presented in the list
        BundlesData foundBundle = allBundlesResponse.getData().stream().filter(bundle -> StringUtils.equalsIgnoreCase(bundle.getId(), createdbundleId)).findFirst().orElse(null);
        if (null != foundBundle) {
            System.out.println("Created bundle with id=" + createdbundleId + " was found in the list of all bundles for EO");
        } else {
            System.out.println("Created bundle with id=" + createdbundleId + " is MISSING in the list of all bundles for EO");
        }

    }

    @Test(priority = 102, dependsOnMethods = {"createBundleTest"})
    public void getBundleById() throws Exception {
        //get bundle by ID
        ApiResponse bundleByIdResp = bundlesHandler.getBundleById(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdbundleId);
        BundlesResponse bundlesByIdRespObject = jsonParser.fromJson(bundleByIdResp.getResponseBody(), BundlesResponse.class);

        if (bundlesByIdRespObject.getData().size() == 1) {
            System.out.println("Created bundle was returned by ID");
        }

    }


    @Test(priority = 103, dependsOnMethods = {"createBundleTest"})
    public void updateBundle() throws Exception {
        //update a bundle
        CreateUpdateBundleRequestBody updateBundleRequest = new CreateUpdateBundleRequestBody("UPDATED_" + createdbundleId, "BundleDesc", null, null);
        ApiResponse updateResp = bundlesHandler.doUpdateBundle(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdbundleId, updateBundleRequest);

        System.out.println("Update bundle response code = " + updateResp.getResponseCode());

        BundlesData updatedBundle = jsonParser.fromJson(updateResp.getResponseBody(), BundlesData.class);
        if (StringUtils.equals(updatedBundle.getBundleName(), "UPDATED_DELETE_ME_Automation-" + System.currentTimeMillis())) {
            System.out.println("bundle name updated correctly " + updatedBundle.getBundleName());
        } else {
            System.out.println("bundle name was not updated:  " + updatedBundle.getBundleName() + " But expected: " + "UPDATED_DELETE_ME_Automation-" + System.currentTimeMillis());
        }

        if (StringUtils.equals(updatedBundle.getBundleDesc(), "BundleDesc")) {
            System.out.println("bundle description updated correctly " + updatedBundle.getBundleDesc());
        } else {
            System.out.println("bundle description was not updated:  " + updatedBundle.getBundleDesc() + " But expected: " + "BundleDesc");
        }
    }

    @Test(priority = 104, dependsOnMethods = {"createBundleTest"})
    public void assignFreeAppToBundle() throws Exception {
        //Assign app to bundle
        AssignAppToBundleRequestBody assignAppsRequest = new AssignAppToBundleRequestBody(qa_free_app);
        ApiResponse assignAppResp = bundlesHandler.doAssignAppsToBundle(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdbundleId, assignAppsRequest);

        if (assignAppResp.getResponseCode() == 201) {
            System.out.println("Assign apps request response code correct: " + assignAppResp.getResponseCode());
        } else {
            System.out.println("Wrong response code of Assign apps request: " + assignAppResp.getResponseCode());
            System.out.println("response body " + assignAppResp.getResponseBody());
        }

        //verify assigned app is returned in get bundle by id request

        ApiResponse bundleByIdResp = bundlesHandler.getBundleById(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdbundleId);
        BundlesResponse bundlesByIdRespObject = jsonParser.fromJson(bundleByIdResp.getResponseBody(), BundlesResponse.class);

        if (bundlesByIdRespObject.getData().size() == 1) {
            System.out.println("Created bundle was returned by ID");

            if (bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().size() > 0 &&
                    bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().contains(qa_free_app)) {
                System.out.println("Correct AppmarketAppId returned inside get bundle request:" + qa_free_app);

            } else {
                System.out.println("Correct AppmarketAppId was not returned inside get bundle request");
            }

        }

    }


    @Test(priority = 105, dependsOnMethods = {"createBundleTest", "assignFreeAppToBundle"})
    public void unassignAppFromBundle() throws Exception {
        //unassign app from bundle

        ApiResponse unassignBundleResponse = bundlesHandler.doUnassignAppsToBundle(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdbundleId, qa_free_app);
        System.out.println("Unassign bundle response: " + unassignBundleResponse.getResponseCode());

        ApiResponse bundleByIdResp = bundlesHandler.getBundleById(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdbundleId);
        BundlesResponse bundlesByIdRespObject = jsonParser.fromJson(bundleByIdResp.getResponseBody(), BundlesResponse.class);

        if (bundlesByIdRespObject.getData().size() == 1) {
            System.out.println("Created bundle was returned by ID");

            if (bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().size() > 0 &&
                    bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().contains(qa_free_app)) {
                System.out.println("Correct AppmarketAppId returned inside get bundle request:" + qa_free_app);

            } else {
                System.out.println("Correct AppmarketAppId was not returned inside get bundle request");
            }

        }

    }

    @Test(priority = 106, dependsOnMethods = {"createBundleTest"})
    public void deleteBundle() throws Exception {
        //delete a bundle
        ApiResponse deleteBundleResponse = bundlesHandler.doDeleteBundle(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdbundleId);
        System.out.println("Delete bundle response code is:" + deleteBundleResponse.getResponseCode());
    }


}
