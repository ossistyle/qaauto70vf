package test.api.bundles;

import api.DTO.bundles.AssignAppToBundleRequestBody;
import api.DTO.bundles.BundlesData;
import api.DTO.bundles.BundlesResponse;
import api.DTO.internalCustomObjects.ApiResponse;
import api.helpers.LoginHelper;
import api.apiHandlers.BundlesApiHandler;
import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.api.BaseApiTest;

import static utils.allure.LogUtil.reportMessage;

public class BundlesNegativeTests extends BaseApiTest {

    private String createdBundleId;
    private BundlesApiHandler bundlesHandler;
    private Gson jsonParser = new Gson();
    private String env;
    private String eoToken;


    @BeforeClass(description = "Create a data to be used in the tests")
    @Parameters("env")
    public void createBundle(String env) throws Exception {
        this.env = env;
        Gson jsonPaser = new Gson();
        eoToken = LoginHelper.getAccessToken(env, "vfameo@getnada.com", "Veri1234");
        bundlesHandler = new BundlesApiHandler(env);

        //create a bundle
        ApiResponse createBundleResponse = bundlesHandler.doCreateBundle(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", "DELETE_ME_Automation-" + System.currentTimeMillis());
        Assert.assertEquals(createBundleResponse.getResponseCode().intValue(), 201, "Unexpected Response code");

        BundlesData createdBundle = jsonPaser.fromJson(createBundleResponse.getResponseBody(), BundlesData.class);
        logger.info("Created bundle ID = " + createdBundle.getId());
        createdBundleId = createdBundle.getId();

    }

    @AfterClass(description = "Clean up the data used in the tests")
    public void cleanUpData() throws Exception {
        ApiResponse deleteBundleResponse = bundlesHandler.doDeleteBundle(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundleId);
        logger.info("Delete bundle response code is:" + deleteBundleResponse.getResponseCode());

    }


    @Test(description = "Try to create a bundle as a merchant and verify it is impossible")
    public void createBundleAsMerchantTest() throws Exception {

        String merchantToken = LoginHelper.getAccessToken("qa", "VFAMMerchant1@getnada.com", "Veri1234");

        bundlesHandler = new BundlesApiHandler("qa");

        //create a bundle as a merchant user
        ApiResponse createBundleResponse = bundlesHandler.doCreateBundle(merchantToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", "DELETE_ME_Automation-" + System.currentTimeMillis());
        BundlesResponse createError = jsonParser.fromJson(createBundleResponse.getResponseBody(), BundlesResponse.class);
        logger.info("Response code is: " + createBundleResponse.getResponseCode());
        logger.info("Error message: " + createError.getErrors().get(0).getMessage());
        Assert.assertEquals(createBundleResponse.getResponseCode().intValue(), 403, "Unexpected response code");


    }

    @Test(description = "Try to assign app to the bundle as a merchant and verify it is impossible")
    public void assignAppToBundleAsMerchant() throws Exception {
        String merchantToken = LoginHelper.getAccessToken("qa", "VFAMMerchant1@getnada.com", "Veri1234");

        AssignAppToBundleRequestBody assignAppsRequest = new AssignAppToBundleRequestBody(qa_free_app);
        ApiResponse assignAppResp = bundlesHandler.doAssignAppsToBundle(merchantToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundleId, assignAppsRequest);

        Assert.assertEquals(assignAppResp.getResponseCode().intValue(), 403, "Unexpected response code");

        reportMessage("Get bundle by Id and verify there are no assigned apps");
        ApiResponse bundleByIdResp = bundlesHandler.getBundleById(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundleId);
        BundlesResponse bundlesByIdRespObject = jsonParser.fromJson(bundleByIdResp.getResponseBody(), BundlesResponse.class);

        Assert.assertEquals(bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().size(), 0,
                "List of assigned apps is not empty: " + bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().toString());

    }


}
