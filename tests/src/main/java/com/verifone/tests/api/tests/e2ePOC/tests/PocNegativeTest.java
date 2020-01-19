package com.verifone.tests.api.tests.e2ePOC.tests;

import com.google.gson.Gson;
import com.verifone.tests.api.tests.e2ePOC.DTO.bundles.AssignAppToBundleRequestBody;
import com.verifone.tests.api.tests.e2ePOC.DTO.bundles.BundlesData;
import com.verifone.tests.api.tests.e2ePOC.DTO.bundles.BundlesResponse;
import com.verifone.tests.api.tests.e2ePOC.DTO.internalCustomObjects.ApiResponse;
import com.verifone.tests.api.tests.e2ePOC.helpers.LoginHelper;
import com.verifone.tests.api.tests.e2ePOC.helpers.api.BundlesApiHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PocNegativeTest extends BaseTest {

    private String createdBundleId;
    private BundlesApiHandler bundlesHandler;
    private Gson jsonParser = new Gson();


    @BeforeClass
    public void createBundle() throws Exception {
        Gson jsonPaser = new Gson();

        String eoToken = LoginHelper.getRequestToken("qa", "vfameo@getnada.com", "Veri1234");

        bundlesHandler = new BundlesApiHandler("qa");

        //create a bundle
        ApiResponse createBundleResponse = bundlesHandler.doCreateBundle(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", "DELETE_ME_Automation-" + System.currentTimeMillis());
        BundlesData createdBundle = jsonPaser.fromJson(createBundleResponse.getResponseBody(), BundlesData.class);
        System.out.println("Created bundle ID = " + createdBundle.getId());
        createdBundleId = createdBundle.getId();
    }

    @AfterClass
    public void cleanUpData() throws Exception {

        String eoToken = LoginHelper.getRequestToken("qa", "vfameo@getnada.com", "Veri1234");
        bundlesHandler = new BundlesApiHandler("qa");
        ApiResponse deleteBundleResponse = bundlesHandler.doDeleteBundle(eoToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundleId);
        System.out.println("Delete bundle response code is:" + deleteBundleResponse.getResponseCode());

    }


    @Test
    public void createBundleAsMerchantTest() throws Exception {

        String merchantToken = LoginHelper.getRequestToken("qa", "VFAMMerchant1@getnada.com", "Veri1234");

        bundlesHandler = new BundlesApiHandler("qa");

        //create a bundle
        ApiResponse createBundleResponse = bundlesHandler.doCreateBundle(merchantToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", "DELETE_ME_Automation-" + System.currentTimeMillis());
        BundlesResponse createError = jsonParser.fromJson(createBundleResponse.getResponseBody(), BundlesResponse.class);
        System.out.println("Response code is: " + createBundleResponse.getResponseCode());
        System.out.println("Error message: " + createError.getErrors().get(0).getMessage());

    }

    @Test
    public void assigAppToBundleAsMerchant() throws Exception {
        String merchantToken = LoginHelper.getRequestToken("qa", "VFAMMerchant1@getnada.com", "Veri1234");

        AssignAppToBundleRequestBody assignAppsRequest = new AssignAppToBundleRequestBody(qa_free_app);
        ApiResponse assignAppResp = bundlesHandler.doAssignAppsToBundle(merchantToken, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundleId, assignAppsRequest);

        if (assignAppResp.getResponseCode() == 201) {
            System.out.println("Assign apps request response code correct: " + assignAppResp.getResponseCode());
        } else {
            System.out.println("Wrong response code of Assign apps request: " + assignAppResp.getResponseCode());
            System.out.println("response body " + assignAppResp.getResponseBody());
        }

    }


}
