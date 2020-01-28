package test.api.bundles;

import api.DTO.bundles.AssignAppToBundleRequestBody;
import api.DTO.bundles.BundlesData;
import api.DTO.bundles.BundlesResponse;
import api.DTO.internalCustomObjects.ApiResponse;
import api.apiHandlers.BundlesApiHandler;
import api.helpers.LoginHelper;
import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.api.BaseApiTest;

import static utils.allure.LogUtil.reportMessage;

public class BundlesNegativeTests extends BaseApiTest {

    private String createdBundleId;
    private BundlesApiHandler bundlesHandler;
    private String eoToken;


    @BeforeClass(description = "Create a data to be used in the tests")
    public void initAndCreateTestData() throws Exception {
        Gson jsonPaser = new Gson();
        eoToken = LoginHelper.getAccessToken(env, testData.getString("eo.username"), testData.getString("eo.password"));
        bundlesHandler = new BundlesApiHandler(env);

        //create a bundle
        ApiResponse createBundleResponse = bundlesHandler.doCreateBundle(eoToken, testData.getString("eo.orgId"), "DELETE_ME_Automation-" + System.currentTimeMillis());
        Assert.assertEquals(createBundleResponse.getResponseCode().intValue(), 201, "Unexpected Response code");

        BundlesData createdBundle = jsonPaser.fromJson(createBundleResponse.getResponseBody(), BundlesData.class);
        logger.info("Created bundle ID = " + createdBundle.getId());
        createdBundleId = createdBundle.getId();

    }

    @AfterClass(description = "Clean up the data used in the tests")
    public void cleanUpData() throws Exception {
        ApiResponse deleteBundleResponse = bundlesHandler.doDeleteBundle(eoToken, testData.getString("eo.orgId"), createdBundleId);
        logger.info("Delete bundle response code is:" + deleteBundleResponse.getResponseCode());

    }


    @Test(description = "Try to create a bundle as a merchant and verify it is impossible")
    public void createBundleAsMerchantTest() throws Exception {

        String merchantToken = LoginHelper.getAccessToken(env, testData.getString("merchant.username"), testData.getString("merchant.password"));

        //create a bundle as a merchant user
        ApiResponse createBundleResponse = bundlesHandler.doCreateBundle(merchantToken, testData.getString("eo.orgId"), "DELETE_ME_Automation-" + System.currentTimeMillis());
        BundlesResponse createError = jsonParser.fromJson(createBundleResponse.getResponseBody(), BundlesResponse.class);
        logger.info("Response code is: " + createBundleResponse.getResponseCode());
        logger.info("Error message: " + createError.getErrors().get(0).getMessage());
        Assert.assertEquals(createBundleResponse.getResponseCode().intValue(), 403, "Unexpected response code");

    }

    @Test(description = "Try to assign app to the bundle as a merchant and verify it is impossible")
    public void assignAppToBundleAsMerchant() throws Exception {
        String merchantToken = LoginHelper.getAccessToken(env, testData.getString("merchant.username"), testData.getString("merchant.password"));

        AssignAppToBundleRequestBody assignAppsRequest = new AssignAppToBundleRequestBody(testData.getString("free.app.appmarketAppId"));
        ApiResponse assignAppResp = bundlesHandler.doAssignAppsToBundle(merchantToken, testData.getString("eo.orgId"), createdBundleId, assignAppsRequest);

        Assert.assertEquals(assignAppResp.getResponseCode().intValue(), 403, "Unexpected response code");

        reportMessage("Get bundle by Id and verify there are no assigned apps");
        ApiResponse bundleByIdResp = bundlesHandler.getBundleById(eoToken, testData.getString("eo.orgId"), createdBundleId);
        BundlesResponse bundlesByIdRespObject = jsonParser.fromJson(bundleByIdResp.getResponseBody(), BundlesResponse.class);

        Assert.assertEquals(bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().size(), 0,
                "List of assigned apps is not empty: " + bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().toString());

    }


}
