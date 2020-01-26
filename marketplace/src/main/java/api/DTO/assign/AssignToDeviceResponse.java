package api.DTO.assign;

import java.util.List;

public class AssignToDeviceResponse {

    private String status;
    private List<Errors> errors;
    private List<AssignToDeviceResponseData> data;
    private String totalPrice;
    private String offerId;
    private List<Integer> statusCodes;

    public List<Errors> getErrors() {
        return errors;
    }

    public void setErrors(List<Errors> errors) {
        this.errors = errors;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

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
