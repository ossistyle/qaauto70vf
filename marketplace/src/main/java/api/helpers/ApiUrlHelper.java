package api.helpers;

public class ApiUrlHelper {


    public static String getCreateBundleRequestUrl(String env, String eoId) {
        return getRequestHost(env) + "/v1/bundles/estateOwner/" + eoId;
    }

    public static String getAssignAppToBundleRequestUrl(String env, String eoId, String bundleId) {
        return getRequestHost(env) + "/v1/bundles/estateOwner/" + eoId + "/bundle/" + bundleId + "/assign";
    }

    public static String getUnAssignAppToBundleRequestUrl(String env, String eoId, String bundleId, String appmarketId) {
        return getRequestHost(env) + "/v1/bundles/estateOwner/" + eoId + "/bundle/" + bundleId + "/app/" + appmarketId + "/unassign";
    }

    public static String getUpdateBundleRequestUrl(String env, String eoId, String bundleId) {
        return getRequestHost(env) + "/v1/bundles/estateOwner/" + eoId + "/bundle/" + bundleId;
    }

    public static String getDeleteBundleRequestUrl(String env, String eoId, String bundleId) {
        return getRequestHost(env) + "/v1/bundles/estateOwner/" + eoId + "/bundle/" + bundleId;
    }

    public static String getGetEoBundleByIdRequestUrl(String env, String eoId, String bundleId) {
        return getRequestHost(env) + "/v1/bundles/estateOwner/" + eoId + "/bundle/" + bundleId;
    }

    public static String getGetAllBundlesForEoRequestUrl(String env, String eoId) {
        return getRequestHost(env) + "/v1/bundles/estateOwner/" + eoId;
    }

    private static String getRequestHost(String env) {
        return "https://" + env + ".vfappmarket.verifonecp.com";
    }


    //assignment-controller


    private static String getEvaluateAppToDeviceUrl(String env, String merchantEntityUid, String appmarketAppId){
        return getRequestHost(env) + "/v1/assignments/merchants/" + merchantEntityUid + "/apps/" + appmarketAppId + "/assign/evaluate";
    }

    private static String getAssignAppToDeviceUrl(String env, String merchantEntityUid, String appmarketAppId){
        return getRequestHost(env) + "/v1/assignments/merchants/" + merchantEntityUid + "/apps/" + appmarketAppId + "/assign";
    }

    private static String getUnEvaluateAppToDeviceUrl(String env, String merchantEntityUid, String appmarketAppId){
        return getRequestHost(env) + "/v1/assignments/merchants/" + merchantEntityUid + "/apps/" + appmarketAppId + "/unassign/evaluate";
    }

    private static String getUnAssignAppToDeviceUrl(String env, String merchantEntityUid, String appmarketAppId){
        return getRequestHost(env) + "/v1/assignments/merchants/" + merchantEntityUid + "/apps/" + appmarketAppId + "/unassign";
    }

    private static String getEvaluateBundleToDeviceUrl(String env, String merchantEntityUid, String bundleId){
        return getRequestHost(env) + "/v1/assignments/merchants/" + merchantEntityUid + "/bundles/" + bundleId + "/assign/evaluate";
    }

    private static String getAssignBundleToDeviceUrl(String env, String merchantEntityUid, String bundleId){
        return getRequestHost(env) + "/v1/assignments/merchants/" + merchantEntityUid + "/bundles/" + bundleId + "/assign";
    }

    private static String getUnEvaluateBundleToDeviceUrl(String env, String merchantEntityUid, String bundleId){
        return getRequestHost(env) + "/v1/assignments/merchants/" + merchantEntityUid + "/bundles/" + bundleId + "/unassign/evaluate";
    }

    private static String getUnAssignBundleToDeviceUrl(String env, String merchantEntityUid, String bundleId){
        return getRequestHost(env) + "/v1/assignments/merchants/" + merchantEntityUid + "/bundles/" + bundleId + "/unassign";
    }

}
