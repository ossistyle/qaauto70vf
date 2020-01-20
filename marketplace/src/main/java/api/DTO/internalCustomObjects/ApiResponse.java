package api.DTO.internalCustomObjects;

import org.apache.http.Header;

import java.util.List;

public class ApiResponse {

    private Integer responseCode;
    private String responseBody;
    private List<Header> headers;

    public ApiResponse(Integer responseCode, String responseBody, List<Header> headers) {
        this.responseCode = responseCode;
        this.responseBody = responseBody;
        this.headers = headers;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }
}
