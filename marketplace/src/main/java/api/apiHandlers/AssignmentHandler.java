package api.apiHandlers;

import api.DTO.assign.AssignToDeviceRequestBody;
import api.DTO.internalCustomObjects.ApiResponse;
import api.helpers.UrlHelper.AssignmentUrlHelper;
import io.qameta.allure.Step;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import java.util.ArrayList;
import java.util.List;

public class AssignmentHandler extends BaseApiHandler{


    public AssignmentHandler(String env) {
        super(env);
    }

    @Step
    public ApiResponse doEvaluationAssignApp(String bearerToken, String merchantEntityUid, String appmarketAppId, String deviceId, String id) throws Exception {

        AssignToDeviceRequestBody requestBodyObject = new AssignToDeviceRequestBody(deviceId, id);

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        String url = AssignmentUrlHelper.getEvaluateAppToDeviceUrl(env, merchantEntityUid, appmarketAppId);
        String requestBody = jsonParser.toJson(requestBodyObject);
        ApiResponse evaluateAssignAppResponse = executorHelper.getPostResponseData(url, headers, requestBody);

        reportRequestData(url, evaluateAssignAppResponse, requestBody);
        return evaluateAssignAppResponse;
    }


    @Step
    public ApiResponse doAssignApp(String bearerToken, String merchantEntityUid, String appmarketAppId, String offerId, String deviceId,String id) throws Exception {

        AssignToDeviceRequestBody requestBodyObject = new AssignToDeviceRequestBody(offerId, deviceId, id);

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        String url = AssignmentUrlHelper.getAssignAppToDeviceUrl(env, merchantEntityUid, appmarketAppId);
        String requestBody = jsonParser.toJson(requestBodyObject);
        ApiResponse evaluateAssignAppResponse = executorHelper.getPostResponseData(url, headers, requestBody);

        reportRequestData(url, evaluateAssignAppResponse, requestBody);
        return evaluateAssignAppResponse;
    }


    @Step
    public ApiResponse doEvaluationUnAssignApp(String bearerToken, String merchantEntityUid, String appmarketAppId, String deviceId,String id) throws Exception {

        AssignToDeviceRequestBody requestBodyObject = new AssignToDeviceRequestBody(deviceId, id);

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        String url = AssignmentUrlHelper.getUnEvaluateAppToDeviceUrl(env, merchantEntityUid, appmarketAppId);
        String requestBody = jsonParser.toJson(requestBodyObject);
        ApiResponse evaluateAssignAppResponse = executorHelper.getPostResponseData(url, headers, requestBody);

        reportRequestData(url, evaluateAssignAppResponse, requestBody);
        return evaluateAssignAppResponse;
    }


    @Step
    public ApiResponse doUnAssignApp(String bearerToken, String merchantEntityUid, String appmarketAppId, String offerId, String deviceId,String id) throws Exception {

        AssignToDeviceRequestBody requestBodyObject = new AssignToDeviceRequestBody(offerId, deviceId, id);

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        String url = AssignmentUrlHelper.getUnAssignAppToDeviceUrl(env, merchantEntityUid, appmarketAppId);
        String requestBody = jsonParser.toJson(requestBodyObject);
        ApiResponse evaluateAssignAppResponse = executorHelper.getPostResponseData(url, headers, requestBody);

        reportRequestData(url, evaluateAssignAppResponse, requestBody);
        return evaluateAssignAppResponse;
    }
}
