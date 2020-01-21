package api.DTO.assign;

public class AssignToDevicePois {

    private String deviceId;
    private String id;

    public AssignToDevicePois(String deviceId, String id) {
        this.deviceId = deviceId;
        this.id = id;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getId() {
        return id;
    }
}
