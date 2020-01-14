package com.verifone.tests.api.tests.e2ePOC.DTO.bundles;

import java.util.List;

public class BundlesResponse {
    private String status;
    private List<BundlesData> data;

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
}
