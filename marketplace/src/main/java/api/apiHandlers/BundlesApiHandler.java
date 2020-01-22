package api.apiHandlers;

import api.DTO.bundles.AssignAppToBundleRequestBody;
import api.DTO.bundles.CreateUpdateBundleRequestBody;
import api.DTO.internalCustomObjects.ApiResponse;
import api.helpers.ApiUrlHelper;
import io.qameta.allure.Step;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.List;

public class BundlesApiHandler extends BaseApiHandler {


    public BundlesApiHandler(String env) {
        super(env);
    }


    @Step
    public ApiResponse doCreateBundle(String bearerToken, String eoId, String bundleName) throws Exception {

        CreateUpdateBundleRequestBody requestBodyObject = new CreateUpdateBundleRequestBody(bundleName, null, null, null);

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        String url = ApiUrlHelper.getCreateBundleRequestUrl(env, eoId);
        String requestBody = jsonParser.toJson(requestBodyObject);
        ApiResponse createBundleResponse = executorHelper.getPostResponseData(url, headers, requestBody);

        reportRequestData(url, createBundleResponse, requestBody);
        return createBundleResponse;
    }

    @Step
    public ApiResponse doUpdateBundle(String bearerToken, String eoId, String bundleId, CreateUpdateBundleRequestBody updateData) throws Exception {

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        String url = ApiUrlHelper.getUpdateBundleRequestUrl(env, eoId, bundleId);
        String requestBody = jsonParser.toJson(updateData);
        ApiResponse updateBundleResponse = executorHelper.getPutResponseData(url, headers, requestBody);

        reportRequestData(url, updateBundleResponse, requestBody);
        return updateBundleResponse;
    }

    @Step
    public ApiResponse doAssignAppsToBundle(String bearerToken, String eoId, String bundleId, AssignAppToBundleRequestBody assignAppsRequest) throws Exception {

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        String url = ApiUrlHelper.getAssignAppToBundleRequestUrl(env, eoId, bundleId);
        String requestBody = jsonParser.toJson(assignAppsRequest);
        ApiResponse assignAppsResponse = executorHelper.getPostResponseData(url, headers, requestBody);

        reportRequestData(url, assignAppsResponse, requestBody);
        return assignAppsResponse;
    }

    @Step
    public ApiResponse doUnassignAppsToBundle(String bearerToken, String eoId, String bundleId, String appmarketId) throws Exception {

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        String url = ApiUrlHelper.getUnAssignAppToBundleRequestUrl(env, eoId, bundleId, appmarketId);
        ApiResponse unassignAppsResponse = executorHelper.getPostResponseData(url, headers, "");

        reportRequestData(url, unassignAppsResponse);
        return unassignAppsResponse;
    }

    @Step
    public ApiResponse getAllBundlesForEo(String bearerToken, String eoId) throws Exception {

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Accept", "application/json"));

        String url = ApiUrlHelper.getGetAllBundlesForEoRequestUrl(env, eoId);
        ApiResponse allBundlesResponse = executorHelper.getGetResponseData(url, headers);

        reportRequestData(url, allBundlesResponse);
        return allBundlesResponse;
    }

    @Step
    public ApiResponse getBundleById(String bearerToken, String eoId, String bundleId) throws Exception {

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Accept", "application/json"));

        String url = ApiUrlHelper.getGetEoBundleByIdRequestUrl(env, eoId, bundleId);
        ApiResponse getBundleByIdResponse = executorHelper.getGetResponseData(url, headers);

        reportRequestData(url, getBundleByIdResponse);
        return getBundleByIdResponse;
    }

    @Step
    public ApiResponse doDeleteBundle(String bearerToken, String eoId, String bundleId) throws Exception {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        //headers.add(new BasicHeader("Accept", "application/json"));

        String url = ApiUrlHelper.getDeleteBundleRequestUrl(env, eoId, bundleId);
        ApiResponse deletedBundleResponse = executorHelper.getDeleteResponseData(url, headers);

        reportRequestData(url, deletedBundleResponse);
        return deletedBundleResponse;
    }


}
