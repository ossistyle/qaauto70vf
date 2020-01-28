package com.verifone.tests.api.tests.e2ePOC.helpers;

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
}
