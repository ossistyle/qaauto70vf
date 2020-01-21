package api.helpers.api;

import api.DTO.bundles.AssignAppToBundleRequestBody;
import api.DTO.bundles.CreateUpdateBundleRequestBody;
import api.DTO.internalCustomObjects.ApiResponse;
import api.helpers.ApiUrlHelper;
import api.helpers.RequestExecutorHelper;
import com.google.gson.Gson;
import io.qameta.allure.Step;
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


    @Step
    public ApiResponse doCreateBundle(String bearerToken, String eoId, String bundleName) throws Exception {

        CreateUpdateBundleRequestBody requestBodyObject = new CreateUpdateBundleRequestBody(bundleName, null, null, null);

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        ApiResponse createBundleResponse = executorHelper.getPostResponseData(ApiUrlHelper.getCreateBundleRequestUrl(env, eoId), headers, new Gson().toJson(requestBodyObject));

        return createBundleResponse;
    }

    @Step
    public ApiResponse doUpdateBundle(String bearerToken, String eoId, String bundleId, CreateUpdateBundleRequestBody updateData) throws Exception {

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        ApiResponse updateBundleResponse = executorHelper.getPutResponseData(ApiUrlHelper.getUpdateBundleRequestUrl(env, eoId, bundleId), headers, new Gson().toJson(updateData));

        return updateBundleResponse;
    }

    @Step
    public ApiResponse doAssignAppsToBundle(String bearerToken, String eoId, String bundleId, AssignAppToBundleRequestBody requestBody) throws Exception {

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        ApiResponse assignAppsResponse = executorHelper.getPostResponseData(ApiUrlHelper.getAssignAppToBundleRequestUrl(env, eoId, bundleId), headers, new Gson().toJson(requestBody));

        return assignAppsResponse;
    }

    @Step
    public ApiResponse doUnassignAppsToBundle(String bearerToken, String eoId, String bundleId, String appmarketId) throws Exception {

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        ApiResponse assignAppsResponse = executorHelper.getPostResponseData(ApiUrlHelper.getUnAssignAppToBundleRequestUrl(env, eoId, bundleId, appmarketId), headers, "");

        return assignAppsResponse;
    }

    @Step
    public ApiResponse getAllBundlesForEo(String bearerToken, String eoId) throws Exception {

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Accept", "application/json"));

        ApiResponse allBundlesResponse = executorHelper.getGetResponseData(ApiUrlHelper.getGetAllBundlesForEoRequestUrl(env, eoId), headers);

        return allBundlesResponse;
    }

    @Step
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
