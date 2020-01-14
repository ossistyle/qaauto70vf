package com.verifone.tests.api.tests.e2ePOC.helpers.api;

import com.google.gson.Gson;
import com.verifone.tests.api.tests.e2ePOC.DTO.bundles.AssignAppToBundleRequestBody;
import com.verifone.tests.api.tests.e2ePOC.DTO.bundles.CreateUpdateBundleRequestBody;
import com.verifone.tests.api.tests.e2ePOC.DTO.internalCustomObjects.ApiResponse;
import com.verifone.tests.api.tests.e2ePOC.helpers.ApiUrlHelper;
import com.verifone.tests.api.tests.e2ePOC.helpers.RequestExecutorHelper;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.List;

public class BundlesApiHandler {
    private String env;
    private RequestExecutorHelper executorHelper = new RequestExecutorHelper();

    public BundlesApiHandler(String env) {
        this.env = env;
    }

    public ApiResponse doCreateBundle(String bearerToken, String eoId, String bundleName) throws Exception {

        CreateUpdateBundleRequestBody requestBodyObject = new CreateUpdateBundleRequestBody(bundleName, null, null);

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        ApiResponse createBundleResponse = executorHelper.getPostResponseData(ApiUrlHelper.getCreateBundleRequestUrl(env, eoId), headers, new Gson().toJson(requestBodyObject));

        return createBundleResponse;
    }

    public ApiResponse doUpdateBundle(String bearerToken, String eoId, String bundleId, CreateUpdateBundleRequestBody updateData) throws Exception {

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        ApiResponse updateBundleResponse = executorHelper.getPutResponseData(ApiUrlHelper.getUpdateBundleRequestUrl(env, eoId, bundleId), headers, new Gson().toJson(updateData));

        return updateBundleResponse;
    }

    public ApiResponse doAssignAppsToBundle(String bearerToken, String eoId, String bundleId, AssignAppToBundleRequestBody requestBody) throws Exception {

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        ApiResponse assignAppsResponse = executorHelper.getPostResponseData(ApiUrlHelper.getAssignAppToBundleRequestUrl(env, eoId, bundleId), headers, new Gson().toJson(requestBody));

        return assignAppsResponse;
    }


    public ApiResponse doUnassignAppsToBundle(String bearerToken, String eoId, String bundleId, String appmarketId) throws Exception {

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        ApiResponse assignAppsResponse = executorHelper.getPostResponseData(ApiUrlHelper.getUnAssignAppToBundleRequestUrl(env, eoId, bundleId, appmarketId), headers, "");

        return assignAppsResponse;
    }


    public ApiResponse getAllBundlesForEo(String bearerToken, String eoId) throws Exception {

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Accept", "application/json"));

        ApiResponse allBundlesResponse = executorHelper.getGetResponseData(ApiUrlHelper.getGetAllBundlesForEoRequestUrl(env, eoId), headers);

        return allBundlesResponse;
    }

    public ApiResponse getBundleById(String bearerToken, String eoId, String bundleId) throws Exception {

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Accept", "application/json"));

        ApiResponse getBundleByIdResponse = executorHelper.getGetResponseData(ApiUrlHelper.getGetEoBundleByIdRequestUrl(env, eoId, bundleId), headers);

        return getBundleByIdResponse;
    }

    public ApiResponse doDeleteBundle(String bearerToken, String eoId, String bundleId) throws Exception {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        //headers.add(new BasicHeader("Accept", "application/json"));

        ApiResponse deletedBundleResponse = executorHelper.getDeleteResponseData(ApiUrlHelper.getDeleteBundleRequestUrl(env, eoId, bundleId), headers);

        return deletedBundleResponse;
    }


}
