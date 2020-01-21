package api.DTO.assign;

public class AssignToDeviceData {

    private String offerId;
    private AssignToDevicePois pois;

    public AssignToDeviceData(String offerId, String deviceId, String id) {
        this.offerId = offerId;
        this.pois = new AssignToDevicePois(deviceId, id);
    }

    public AssignToDeviceData(String deviceId, String id) {
        this.pois = new AssignToDevicePois(deviceId, id);
    }





    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public AssignToDevicePois getPois() {
        return pois;
    }

    public void setPois(AssignToDevicePois pois) {
        this.pois = pois;
    }

}
