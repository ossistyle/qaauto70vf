package api.DTO.assign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignToDevicePois {

    private String deviceId;
    private String id;
    private Map<String,String> poi = new HashMap<>();
    private List<Map> poiList = new ArrayList<>();


    public AssignToDevicePois(String deviceId, String id) {
        this.deviceId = deviceId;
        this.id = id;
        poi.put("deviceId", deviceId);
        poi.put("id", id);
    }

    public List<Map> getPoiList(){
        poiList.add(poi);
        return poiList;
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
