package api.DTO.assign;


public class AssignToDevicePois {

    private Pois poi;

    public AssignToDevicePois(String deviceId, String id) {
        poi = new Pois(deviceId,id);
    }

    public Pois getPoi() {
        return poi;
    }

    public void setPoi(Pois poi) {
        this.poi = poi;
    }


}
