package api.DTO.device;

import java.util.List;

public class GetDevicesByAppResponse {

    private String status;
    private List<GetDeviceByAppData> data;
    private Integer numberDevices;
    private LicensesData licensesData;

    public GetDevicesByAppResponse(String status, List<GetDeviceByAppData> data, Integer numberDevices, LicensesData licensesData) {
        this.status = status;
        this.data = data;
        this.numberDevices = numberDevices;
        this.licensesData = licensesData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GetDeviceByAppData> getData() {
        return data;
    }

    public void setData(List<GetDeviceByAppData> data) {
        this.data = data;
    }

    public Integer getNumberDevices() {
        return numberDevices;
    }

    public void setNumberDevices(Integer numberDevices) {
        this.numberDevices = numberDevices;
    }

    public LicensesData getLicensesData() {
        return licensesData;
    }

    public void setLicensesData(LicensesData licensesData) {
        this.licensesData = licensesData;
    }
}
