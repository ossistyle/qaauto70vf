package api.helpers.UrlHelper;

public class AssignmentController {

    private static String getRequestHost(String env) {
        return "https://" + env + ".vfappmarket.verifonecp.com";
    }

    public static String getEvaluateAppToDeviceUrl(String env, String merchantEntityUid, String appmarketAppId){
        return getRequestHost(env) + "/v1/assignments/merchants/" + merchantEntityUid + "/apps/" + appmarketAppId + "/assign/evaluate";
    }

    public static String getAssignAppToDeviceUrl(String env, String merchantEntityUid, String appmarketAppId){
        return getRequestHost(env) + "/v1/assignments/merchants/" + merchantEntityUid + "/apps/" + appmarketAppId + "/assign";
    }

    public static String getUnEvaluateAppToDeviceUrl(String env, String merchantEntityUid, String appmarketAppId){
        return getRequestHost(env) + "/v1/assignments/merchants/" + merchantEntityUid + "/apps/" + appmarketAppId + "/unassign/evaluate";
    }

    public static String getUnAssignAppToDeviceUrl(String env, String merchantEntityUid, String appmarketAppId){
        return getRequestHost(env) + "/v1/assignments/merchants/" + merchantEntityUid + "/apps/" + appmarketAppId + "/unassign";
    }

    public static String getEvaluateBundleToDeviceUrl(String env, String merchantEntityUid, String bundleId){
        return getRequestHost(env) + "/v1/assignments/merchants/" + merchantEntityUid + "/bundles/" + bundleId + "/assign/evaluate";
    }

    public static String getAssignBundleToDeviceUrl(String env, String merchantEntityUid, String bundleId){
        return getRequestHost(env) + "/v1/assignments/merchants/" + merchantEntityUid + "/bundles/" + bundleId + "/assign";
    }

    public static String getUnEvaluateBundleToDeviceUrl(String env, String merchantEntityUid, String bundleId){
        return getRequestHost(env) + "/v1/assignments/merchants/" + merchantEntityUid + "/bundles/" + bundleId + "/unassign/evaluate";
    }

    public static String getUnAssignBundleToDeviceUrl(String env, String merchantEntityUid, String bundleId){
        return getRequestHost(env) + "/v1/assignments/merchants/" + merchantEntityUid + "/bundles/" + bundleId + "/unassign";
    }
}
