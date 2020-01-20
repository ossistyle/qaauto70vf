package com.verifone.tests.api.tests.e2ePOC.DTO.bundles;

public class AssignAppToBundleData {
    private String appmarketAppId;
    private Integer bundlePrice;
    private Integer discountPercent;
    private Integer discountAmount;

    public AssignAppToBundleData(String appmarketAppId) {
        this.appmarketAppId = appmarketAppId;
    }

    public AssignAppToBundleData(String appmarketAppId, Integer bundlePrice, Integer discountPercent, Integer discountAmount) {
        this.appmarketAppId = appmarketAppId;
        this.bundlePrice = bundlePrice;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
    }

    public String getAppmarketAppId() {
        return appmarketAppId;
    }

    public void setAppmarketAppId(String appmarketAppId) {
        this.appmarketAppId = appmarketAppId;
    }

    public Integer getBundlePrice() {
        return bundlePrice;
    }

    public void setBundlePrice(Integer bundlePrice) {
        this.bundlePrice = bundlePrice;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }
}
