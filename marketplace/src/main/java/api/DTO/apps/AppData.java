package api.DTO.apps;

public class AppData {
    private String appId;
    private String appVersionId;
    private String appName;
    private String appVersion;
    private String pricingModelId;
    private String pricingModel;
    private String price;
    private String appmarketAppId;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppVersionId() {
        return appVersionId;
    }

    public void setAppVersionId(String appVersionId) {
        this.appVersionId = appVersionId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getPricingModelId() {
        return pricingModelId;
    }

    public void setPricingModelId(String pricingModelId) {
        this.pricingModelId = pricingModelId;
    }

    public String getPricingModel() {
        return pricingModel;
    }

    public void setPricingModel(String pricingModel) {
        this.pricingModel = pricingModel;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAppmarketAppId() {
        return appmarketAppId;
    }

    public void setAppmarketAppId(String appmarketAppId) {
        this.appmarketAppId = appmarketAppId;
    }
}
