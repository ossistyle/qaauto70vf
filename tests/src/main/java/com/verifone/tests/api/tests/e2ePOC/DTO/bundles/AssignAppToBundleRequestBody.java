package com.verifone.tests.api.tests.e2ePOC.DTO.bundles;

public class AssignAppToBundleRequestBody {
    private AssignAppToBundleData data;

    public AssignAppToBundleRequestBody(String appmarketAppId) {
        this.data = new AssignAppToBundleData(appmarketAppId);
    }

    public AssignAppToBundleRequestBody(String appmarketAppId, Integer bundlePrice, Integer discountPercent, Integer discountAmount) {
        this.data = new AssignAppToBundleData(appmarketAppId, bundlePrice, discountPercent, discountAmount);
    }


    public AssignAppToBundleData getData() {
        return data;
    }

    public void setData(AssignAppToBundleData data) {
        this.data = data;
    }
}
