package api.DTO.device;

public class Devices {

    private String id;
    private String deviceId;

    public Devices(String id, String deviceId) {
        this.id = id;
        this.deviceId = deviceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
