package test.api.bundles;

import api.DTO.bundles.AssignAppToBundleRequestBody;
import api.DTO.bundles.BundlesData;
import api.DTO.bundles.BundlesResponse;
import api.DTO.internalCustomObjects.ApiResponse;
import api.helpers.LoginHelper;
import api.helpers.api.BundlesApiHandler;
import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.api.BaseApiTest;

public class BundlesNegativeTests extends BaseApiTest {

    private String createdBundleId;
    private BundlesApiHandler bundlesHandler;
    private Gson jsonParser = new Gson();
    private String env;
    private String eoToken;


    @BeforeClass
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
        System.out.println("Created bundle ID = " + createdBundle.getId());
        createdBundleId = createdBundle.getId();

    }

    @AfterClass
    public void cleanUpData() throws Exception {
        ApiResponse deleteBundleResponse = bundlesHandler.doDeleteBundle(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundleId);
        System.out.println("Delete bundle response code is:" + deleteBundleResponse.getResponseCode());

    }


    @Test
    public void createBundleAsMerchantTest() throws Exception {

        String merchantToken = LoginHelper.getAccessToken("qa", "VFAMMerchant1@getnada.com", "Veri1234");

        bundlesHandler = new BundlesApiHandler("qa");

        //create a bundle as a merchant user
        ApiResponse createBundleResponse = bundlesHandler.doCreateBundle(merchantToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", "DELETE_ME_Automation-" + System.currentTimeMillis());
        BundlesResponse createError = jsonParser.fromJson(createBundleResponse.getResponseBody(), BundlesResponse.class);
        System.out.println("Response code is: " + createBundleResponse.getResponseCode());
        System.out.println("Error message: " + createError.getErrors().get(0).getMessage());
        Assert.assertEquals(createBundleResponse.getResponseCode().intValue(), 403, "Unexpected response code");


    }

    @Test
    public void assigAppToBundleAsMerchant() throws Exception {
        String merchantToken = LoginHelper.getAccessToken("qa", "VFAMMerchant1@getnada.com", "Veri1234");

        AssignAppToBundleRequestBody assignAppsRequest = new AssignAppToBundleRequestBody(qa_free_app);
        ApiResponse assignAppResp = bundlesHandler.doAssignAppsToBundle(merchantToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundleId, assignAppsRequest);

        Assert.assertEquals(assignAppResp.getResponseCode().intValue(), 403, "Unexpected response code");

        ApiResponse bundleByIdResp = bundlesHandler.getBundleById(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundleId);
        BundlesResponse bundlesByIdRespObject = jsonParser.fromJson(bundleByIdResp.getResponseBody(), BundlesResponse.class);

        Assert.assertEquals(bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().size(), 0,
                "List of assigned apps is not empty: " + bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().toString());

    }


}
