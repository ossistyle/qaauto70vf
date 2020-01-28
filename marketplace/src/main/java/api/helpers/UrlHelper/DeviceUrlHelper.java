package api.helpers.UrlHelper;

public class DeviceUrlHelper {

    private static String getRequestHost(String env) {
        return "https://" + env + ".vfappmarket.verifonecp.com";
    }

    public static String getDivecesWithAppAssigned(String env, String merchantEntityUid, String appmarketAppId){
        return getRequestHost(env) + "/v1/devices/merchants/" + merchantEntityUid + "/app/" + appmarketAppId;
    }

    public static String getDivecesWithBundleAssigned(String env, String merchantEntityUid, String bundleId){
        return getRequestHost(env) + "/v1/devices/merchants/" + merchantEntityUid + "/bundle/" + bundleId;
    }

}
