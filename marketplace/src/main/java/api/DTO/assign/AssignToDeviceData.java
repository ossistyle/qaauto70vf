package api.DTO.assign;

import java.util.List;
import java.util.Map;

public class AssignToDeviceData {

    private String offerId;
    private List<Map> pois;

    public AssignToDeviceData(String offerId, String deviceId, String id) {
        AssignToDevicePois assignToDevicePois = new AssignToDevicePois(deviceId, id);
        this.pois = assignToDevicePois.getPoiList();
        this.offerId = offerId;
    }

    public AssignToDeviceData(String deviceId, String id) {
        AssignToDevicePois assignToDevicePois = new AssignToDevicePois(deviceId, id);
        this.pois = assignToDevicePois.getPoiList();
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public List<Map> getPois() {
        return pois;
    }

    public void setPois(List<Map> pois) {
        this.pois = pois;
    }

}
