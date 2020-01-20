package api.DTO.bundles;

import java.util.List;

public class BundlesResponse {
    private String status;
    private List<BundlesData> data;
    private List<BundleError> errors;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BundlesData> getData() {
        return data;
    }

    public void setData(List<BundlesData> data) {
        this.data = data;
    }

    public List<BundleError> getErrors() {
        return errors;
    }

    public void setErrors(List<BundleError> errors) {
        this.errors = errors;
    }
}
