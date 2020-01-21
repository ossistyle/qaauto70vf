package api.DTO.assign;

public class AssignToDeviceResponseData {

    private String timeCreated;
    private String timeUpdated;
    private String createdBy;
    private String updatedBy;
    private String id;
    private String merchantEntityUid;
    private String appmarketAppId;
    private String poiId;
    private String deviceId;
    private String assignmentType;
    private String assignmentStatus;
    private String deploymentStatus;
    private Double price;

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(String timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantEntityUid() {
        return merchantEntityUid;
    }

    public void setMerchantEntityUid(String merchantEntityUid) {
        this.merchantEntityUid = merchantEntityUid;
    }

    public String getAppmarketAppId() {
        return appmarketAppId;
    }

    public void setAppmarketAppId(String appmarketAppId) {
        this.appmarketAppId = appmarketAppId;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public String getAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentStatus(String assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    public String getDeploymentStatus() {
        return deploymentStatus;
    }

    public void setDeploymentStatus(String deploymentStatus) {
        this.deploymentStatus = deploymentStatus;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
