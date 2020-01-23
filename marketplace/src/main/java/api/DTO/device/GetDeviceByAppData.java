package api.DTO.device;

import java.util.List;

public class GetDeviceByAppData {

    private String appmarketAppId;
    private List<Devices> devices;


    public GetDeviceByAppData(String appmarketAppId, List<Devices> devices) {
        this.appmarketAppId = appmarketAppId;
        this.devices = devices;
    }

    public String getAppmarketAppId() {
        return appmarketAppId;
    }

    public void setAppmarketAppId(String appmarketAppId) {
        this.appmarketAppId = appmarketAppId;
    }

    public List<Devices> getDevices() {
        return devices;
    }

    public void setDevices(List<Devices> devices) {
        this.devices = devices;
    }
}
