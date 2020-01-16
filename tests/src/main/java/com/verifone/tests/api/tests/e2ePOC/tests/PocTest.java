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
import org.testng.annotations.Test;

public class PocTest extends BaseTest {
    private final String qa_free_app = "776a8141-73ed-4ec3-a993-7baa2be09b65";
    private final String qa_free_app_1 = "0b9a8ca9-969d-4cf5-a35e-932a7fd8e9f8";

    @Test
    public void firstPOCTest() throws Exception {

        Gson jsonPaser = new Gson();

        String token = LoginHelper.getRequestToken("qa", "vfameo@getnada.com", "Veri1234");

        BundlesApiHandler bundlesHandler = new BundlesApiHandler("qa");

        //create a bundle
        ApiResponse createBundleResponse = bundlesHandler.doCreateBundle(token, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", "DELETE_ME_Automation-" + System.currentTimeMillis());
        BundlesData createdBundle = jsonPaser.fromJson(createBundleResponse.getResponseBody(), BundlesData.class);
        System.out.println("Created bundle ID = " + createdBundle.getId());

        //Get all bundles for EO and check if created bundle is in the list
        ApiResponse getAllEoBundlesResponse = bundlesHandler.getAllBundlesForEo(token, "0a79306b-7b84-4aec-8f4f-d472662cbdf2");
        BundlesResponse allBundlesResponse = jsonPaser.fromJson(getAllEoBundlesResponse.getResponseBody(), BundlesResponse.class);

        //Check if created bundle is presented in the list
        BundlesData foundBundle = allBundlesResponse.getData().stream().filter(bundle -> StringUtils.equalsIgnoreCase(bundle.getId(), createdBundle.getId())).findFirst().orElse(null);
        if (null != foundBundle) {
            System.out.println("Created bundle with id=" + createdBundle.getId() + " was found in the list of all bundles for EO");
        } else {
            System.out.println("Created bundle with id=" + createdBundle.getId() + " is MISSING in the list of all bundles for EO");
        }

        //get bundle by ID
        ApiResponse bundleByIdResp = bundlesHandler.getBundleById(token, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundle.getId());
        BundlesResponse bundlesByIdRespObject = jsonPaser.fromJson(bundleByIdResp.getResponseBody(), BundlesResponse.class);

        if (bundlesByIdRespObject.getData().size() == 1) {
            System.out.println("Created bundle was returned by ID");
        }

        //update a bundle

        CreateUpdateBundleRequestBody updateBundleRequest = new CreateUpdateBundleRequestBody("UPDATED_" + createdBundle.getBundleName(), "BundleDesc", null, null);
        ApiResponse updateResp = bundlesHandler.doUpdateBundle(token, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundle.getId(), updateBundleRequest);

        System.out.println("Update bundle response code = " + updateResp.getResponseCode());

        BundlesData updatedBundle = jsonPaser.fromJson(updateResp.getResponseBody(), BundlesData.class);
        if (StringUtils.equals(updatedBundle.getBundleName(), "UPDATED_" + createdBundle.getBundleName())) {
            System.out.println("bundle name updated correctly " + updatedBundle.getBundleName());
        } else {
            System.out.println("bundle name was not updated:  " + updatedBundle.getBundleName() + " But expected: " + "UPDATED_" + createdBundle.getBundleName());
        }

        if (StringUtils.equals(updatedBundle.getBundleDesc(), "BundleDesc")) {
            System.out.println("bundle description updated correctly " + updatedBundle.getBundleDesc());
        } else {
            System.out.println("bundle description was not updated:  " + updatedBundle.getBundleDesc() + " But expected: " + "BundleDesc");
        }

        //Assign app to bundle

        AssignAppToBundleRequestBody assignAppsRequest = new AssignAppToBundleRequestBody(qa_free_app);
        ApiResponse assignAppResp = bundlesHandler.doAssignAppsToBundle(token, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundle.getId(), assignAppsRequest);

        if (assignAppResp.getResponseCode() == 201) {
            System.out.println("Assign apps request response code correct: " + assignAppResp.getResponseCode());
        } else {
            System.out.println("Wrong response code of Assign apps request: " + assignAppResp.getResponseCode());
            System.out.println("response body " + assignAppResp.getResponseBody());
        }

        //verify assigned app is returned in get bundle by id request

        bundleByIdResp = bundlesHandler.getBundleById(token, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundle.getId());
        bundlesByIdRespObject = jsonPaser.fromJson(bundleByIdResp.getResponseBody(), BundlesResponse.class);

        if (bundlesByIdRespObject.getData().size() == 1) {
            System.out.println("Created bundle was returned by ID");

            if (bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().size() > 0 &&
                    bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().contains(qa_free_app)) {
                System.out.println("Correct AppmarketAppId returned inside get bundle request:" + qa_free_app);

            } else {
                System.out.println("Correct AppmarketAppId was not returned inside get bundle request");
            }

        }

        //unassign app from bundle

        ApiResponse unassignBundleResponse = bundlesHandler.doUnassignAppsToBundle(token, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundle.getId(), qa_free_app);

        System.out.println("Unassign bundle response: " + unassignBundleResponse.getResponseCode());

        bundleByIdResp = bundlesHandler.getBundleById(token, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundle.getId());
        bundlesByIdRespObject = jsonPaser.fromJson(bundleByIdResp.getResponseBody(), BundlesResponse.class);

        if (bundlesByIdRespObject.getData().size() == 1) {
            System.out.println("Created bundle was returned by ID");

            if (bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().size() > 0 &&
                    bundlesByIdRespObject.getData().get(0).getAssignedAppmarketAppIds().contains(qa_free_app)) {
                System.out.println("Correct AppmarketAppId returned inside get bundle request:" + qa_free_app);

            } else {
                System.out.println("Correct AppmarketAppId was not returned inside get bundle request");
            }

        }


        //delete a bundle
        ApiResponse deleteBundleResponse = bundlesHandler.doDeleteBundle(token, "0a79306b-7b84-4aec-8f4f-d472662cbdf2", createdBundle.getId());
        System.out.println("Delete bundle response code is:" + deleteBundleResponse.getResponseCode());


    }


}
