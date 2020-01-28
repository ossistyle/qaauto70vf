package com.verifone.tests.api.tests.e2ePOC.DTO.apps;

public class AppsResponse {
    private String status;
    private AppData data;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AppData getData() {
        return data;
    }

    public void setData(AppData data) {
        this.data = data;
    }
}
