package test.api.bundles;

import api.DTO.bundles.AssignAppToBundleRequestBody;
import api.DTO.bundles.BundlesData;
import api.DTO.bundles.BundlesResponse;
import api.DTO.bundles.CreateUpdateBundleRequestBody;
import api.DTO.internalCustomObjects.ApiResponse;
import api.apiHandlers.BundlesApiHandler;
import api.helpers.LoginHelper;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.api.BaseApiTest;

import static utils.allure.LogUtil.reportMessage;


public class BundlesSanityFlow extends BaseApiTest {

    private BundlesApiHandler bundlesHandler;
    private String eoToken;
    //private String createdbundleId;
    private BundlesData createdBundle;

    @BeforeClass
    public void preconditions() throws Exception {
        eoToken = LoginHelper.getAccessToken(env, testData.getString("eo.username"), testData.getString("eo.password"));
        reportMessage("eoToken: " + eoToken);
        bundlesHandler = new BundlesApiHandler(env);

    }


    @Test(priority = 100, description = "Create a bundle as EO")
    public void createBundleTest() throws Exception {
        //create a bundle
        ApiResponse createBundleResponse = bundlesHandler.doCreateBundle(eoToken, testData.getString("eo.orgId"), "DELETE_ME_Automation-" + System.currentTimeMillis());
        BundlesData createdBundle = jsonParser.fromJson(createBundleResponse.getResponseBody(), BundlesData.class);

        logger.info("Created bundle ID = " + createdBundle.getId());

        Assert.assertEquals(createBundleResponse.getResponseCode().intValue(), 201, "Unexpected Response code");
        Assert.assertNotNull(createdBundle.getId());
        //createdbundleId = createdBundle.getId();
        this.createdBundle = createdBundle;

    }

    @Test(priority = 101, dependsOnMethods = {"createBundleTest"}, description = "Get all bundles for eo and verify just created bundle appears there")
    public void getAllEoBundles() throws Exception {
        //Get all bundles for EO and check if created bundle is in the list
        ApiResponse getAllEoBundlesResponse = bundlesHandler.getAllBundlesForEo(eoToken, testData.getString("eo.orgId"));
        BundlesResponse allBundlesResponse = jsonParser.fromJson(getAllEoBundlesResponse.getResponseBody(), BundlesResponse.class);

        //Check if created bundle is presented in the list
        BundlesData foundBundle = allBundlesResponse.getData().stream().filter(bundle -> StringUtils.equalsIgnoreCase(bundle.getId(), createdBundle.getId())).findFirst().orElse(null);
        Assert.assertNotNull(foundBundle, "Check that created bundle returned in the list of bundles for EO");

    }

    @Test(priority = 102, dependsOnMethods = {"createBundleTest"}, description = "Get created bundle by id")
    public void getBundleById() throws Exception {
        //get bundle by ID
        ApiResponse bundleByIdResp = bundlesHandler.getBundleById(eoToken, testData.getString("eo.orgId"), createdBundle.getId());
        BundlesResponse bundlesByIdRespObject = jsonParser.fromJson(bundleByIdResp.getResponseBody(), BundlesResponse.class);

        logger.info("Get bundle by ID request returned " + bundlesByIdRespObject.getData().size() + " bundles");
        Assert.assertEquals(bundlesByIdRespObject.getData().size(), 1, "Check that api returned 1 created bundle by id");

    }


    @Test(priority = 103, dependsOnMethods = {"createBundleTest"}, description = "Update bundle name and description")
    public void updateBundle() throws Exception {
        //update a bundle
        String newBundleName = "UPDATED_" + createdBundle.getBundleName();
        String newBundleDesc = "UPDATED_BundleDesc";

        CreateUpdateBundleRequestBody updateBundleRequest = new CreateUpdateBundleRequestBody(newBundleName, newBundleDesc, null, null);
        ApiResponse updateResp = bundlesHandler.doUpdateBundle(eoToken, testData.getString("eo.orgId"), createdBundle.getId(), updateBundleRequest);
        logger.info("Update bundle response code = " + updateResp.getResponseCode());
        Assert.assertEquals(updateResp.getResponseCode().intValue(), 200, "Unexpected response code");

        BundlesData updatedBundle = jsonParser.fromJson(updateResp.getResponseBody(), BundlesData.class);

        Assert.assertEquals(updatedBundle.getBundleName(), newBundleName, "Bundle name was not updated correctly");
        Assert.assertEquals(updatedBundle.getBundleDesc(), newBundleDesc, "Bundle desc was not updated correctly");

    }


    @Test(priority = 104, dependsOnMethods = {"createBundleTest"}, description = "Assign free app to a bundle and verify it appeared in bundle's apps list")
    public void assignFreeAppToBundle() throws Exception {
        //Assign app to bundle
        AssignAppToBundleRequestBody assignAppsRequest = new AssignAppToBundleRequestBody(testData.getString("free.app.appmarketAppId"));
        ApiResponse assignAppResp = bundlesHandler.doAssignAppsToBundle(eoToken, testData.getString("eo.orgId"), createdBundle.getId(), assignAppsRequest);
        Assert.assertEquals(assignAppResp.getResponseCode().intValue(), 201, "Unexpected response code");

        //verify assigned app is returned in get bundle by id request
        reportMessage("Get bundle by Id and verify assigned app appeared under bundle");
        ApiResponse bundleByIdResp = bundlesHandler.getBundleById(eoToken, testData.getString("eo.orgId"), createdBundle.getId());
        BundlesResponse bundlesByIdRespObject = jsonParser.fromJson(bundleByIdResp.getResponseBody(), BundlesResponse.class);

        Assert.assertTrue(bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().size() > 0, "List of assigned apps is empty");
        Assert.assertTrue(bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().contains(testData.getString("free.app.appmarketAppId")), "Verify Id of assigned app is presented in the assigned apps list for bundle");

    }


    @Test(priority = 105, dependsOnMethods = {"createBundleTest", "assignFreeAppToBundle"}, description = "Unassign app from bundle and verify it is no longer appear in bundle's list of apps")
    public void unassignAppFromBundle() throws Exception {
        //unassign app from bundle

        ApiResponse unassignAppResponse = bundlesHandler.doUnassignAppsToBundle(eoToken, testData.getString("eo.orgId"), createdBundle.getId(), testData.getString("free.app.appmarketAppId"));
        logger.info("Unassign bundle response: " + unassignAppResponse.getResponseCode());
        Assert.assertEquals(unassignAppResponse.getResponseCode().intValue(), 204, "Unexpected response code");

        reportMessage("Get bundle by Id and verify assigned app is no longer presented under the bundle");
        ApiResponse bundleByIdResp = bundlesHandler.getBundleById(eoToken, testData.getString("eo.orgId"), createdBundle.getId());
        BundlesResponse bundlesByIdRespObject = jsonParser.fromJson(bundleByIdResp.getResponseBody(), BundlesResponse.class);

        Assert.assertEquals(bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().size(), 0,
                "List of assigned apps is not empty: " + bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().toString());


    }


    @Test(priority = 106, dependsOnMethods = {"createBundleTest"}, description = "Delete bundle and verify it cannot be received by id")
    public void deleteBundle() throws Exception {
        //delete a bundle
        ApiResponse deleteBundleResponse = bundlesHandler.doDeleteBundle(eoToken, testData.getString("eo.orgId"), createdBundle.getId());
        logger.info("Delete bundle response code is:" + deleteBundleResponse.getResponseCode());
        Assert.assertEquals(deleteBundleResponse.getResponseCode().intValue(), 204, "Unexpected response code");

        reportMessage("Get bundle by Id and verify bundle is no longer exists");
        ApiResponse bundleByIdResp = bundlesHandler.getBundleById(eoToken, testData.getString("eo.orgId"), createdBundle.getId());
        Assert.assertEquals(bundleByIdResp.getResponseCode().intValue(), 404, "Unexpected response code when requested deleted bundle");
    }


}
