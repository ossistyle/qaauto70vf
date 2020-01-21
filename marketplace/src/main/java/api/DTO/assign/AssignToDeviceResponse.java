package api.DTO.assign;

import java.util.List;

public class AssignToDeviceResponse {

    private String status;
    private List<AssignToDeviceResponseData> data;
    private List<Integer> statusCodes;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AssignToDeviceResponseData> getData() {
        return data;
    }

    public void setData(List<AssignToDeviceResponseData> data) {
        this.data = data;
    }

    public List<Integer> getStatusCodes() {
        return statusCodes;
    }

    public void setStatusCodes(List<Integer> statusCodes) {
        this.statusCodes = statusCodes;
    }
}
