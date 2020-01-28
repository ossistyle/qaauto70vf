package api.DTO.assign;

public class Pois {

    private String deviceId;
    private String id;

    public Pois(String deviceId, String id) {
        this.deviceId = deviceId;
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
