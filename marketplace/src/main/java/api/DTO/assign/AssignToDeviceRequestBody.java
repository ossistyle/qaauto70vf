package api.DTO.assign;

public class AssignToDeviceRequestBody {

    private AssignToDeviceData data;

    public AssignToDeviceRequestBody(String deviceId, String id) {
        this.data = new AssignToDeviceData(deviceId, id);
    }

    public AssignToDeviceRequestBody(String offerId, String deviceId, String id) {
        this.data = new AssignToDeviceData(offerId, deviceId, id);
    }

    public AssignToDeviceData getData() {
        return data;
    }

    public void setData(AssignToDeviceData data) {
        this.data = data;
    }
}
