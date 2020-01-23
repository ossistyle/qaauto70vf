package api.DTO.assign;


import java.util.List;

public class AssignToDevicePois {

    private Pois poi;
    private List<Pois> poiList ;

    public AssignToDevicePois(String deviceId, String id) {
        poi = new Pois(deviceId,id);
        poiList.add(poi);
    }

    public Pois getPoi() {
        return poi;
    }

    public void setPoi(Pois poi) {
        this.poi = poi;
    }

    public List<Pois> getPoiList() {
        return poiList;
    }

    public void setPoiList(List<Pois> poiList) {
        this.poiList = poiList;
    }

}
