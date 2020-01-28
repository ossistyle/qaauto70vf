package api.apiHandlers;

import api.DTO.internalCustomObjects.ApiResponse;
import api.helpers.UrlHelper.DeviceUrlHelper;
import io.qameta.allure.Step;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import java.util.ArrayList;
import java.util.List;

public class DeviceHandler extends BaseApiHandler{
    public DeviceHandler(String env) {
        super(env);
    }

    @Step
    public ApiResponse getDevicesWithAppId(String bearerToken, String merchantEntityUid, String appmarketAppId) throws Exception {

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        String url = DeviceUrlHelper.getDivecesWithAppAssigned(env, merchantEntityUid, appmarketAppId);
        ApiResponse getDevicesWithAppIdResponse = executorHelper.getGetResponseData(url, headers);

        reportRequestData(url, getDevicesWithAppIdResponse);
        return getDevicesWithAppIdResponse;
    }

    @Step
    public ApiResponse getDevicesWithBundleId(String bearerToken, String merchantEntityUid, String bundleId) throws Exception {

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", "Bearer " + bearerToken));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Accept", "application/json"));

        String url = DeviceUrlHelper.getDivecesWithBundleAssigned(env, merchantEntityUid, bundleId);
        ApiResponse getDevicesWithBundleIdResponse = executorHelper.getGetResponseData(url, headers);

        reportRequestData(url, getDevicesWithBundleIdResponse);
        return getDevicesWithBundleIdResponse;
    }

}
