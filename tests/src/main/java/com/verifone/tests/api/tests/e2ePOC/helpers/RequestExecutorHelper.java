package com.verifone.tests.api.tests.e2ePOC.helpers;

import com.verifone.tests.api.tests.e2ePOC.DTO.internalCustomObjects.ApiResponse;
import org.apache.http.Header;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.util.Arrays;
import java.util.List;

public class RequestExecutorHelper {
    private CloseableHttpClient httpClient;

    public RequestExecutorHelper() {
        try {
            org.apache.http.ssl.SSLContextBuilder sslContextBuilder = SSLContextBuilder.create();
            sslContextBuilder.loadTrustMaterial(new org.apache.http.conn.ssl.TrustSelfSignedStrategy());
            SSLContext sslContext = sslContextBuilder.build();
            org.apache.http.conn.ssl.SSLConnectionSocketFactory sslSocketFactory =
                    new SSLConnectionSocketFactory(sslContext, new org.apache.http.conn.ssl.DefaultHostnameVerifier());

            HttpClientBuilder httpClientBuilder = HttpClients.custom().setSSLSocketFactory(sslSocketFactory);
            httpClient = httpClientBuilder.build();
        } catch (Exception e) {
            System.out.println("Error creating httpclient with invalid sertificate handling: " + e.getMessage());
            e.printStackTrace();
            System.out.println("Create default http client");
            httpClient = HttpClients.createDefault();

        }

    }

    public ApiResponse getPostResponseData(String url, List<Header> headers, String body) throws Exception {

        HttpPost request = new HttpPost(url);
        headers.forEach(request::addHeader);
        request.setEntity(new StringEntity(body));

        CloseableHttpResponse response = httpClient.execute(request);

        ApiResponse apiResponse = new ApiResponse(response.getStatusLine().getStatusCode(),
                null == response.getEntity() ? null : EntityUtils.toString(response.getEntity()), Arrays.asList(response.getAllHeaders()));

        return apiResponse;
    }

    public ApiResponse getPutResponseData(String url, List<Header> headers, String body) throws Exception {

        HttpPut request = new HttpPut(url);
        headers.forEach(request::addHeader);
        request.setEntity(new StringEntity(body));

        CloseableHttpResponse response = httpClient.execute(request);

        ApiResponse apiResponse = new ApiResponse(response.getStatusLine().getStatusCode(),
                null == response.getEntity() ? null : EntityUtils.toString(response.getEntity()), Arrays.asList(response.getAllHeaders()));

        return apiResponse;
    }

    public ApiResponse getGetResponseData(String url, List<Header> headers) throws Exception {

        HttpGet request = new HttpGet(url);
        headers.forEach(request::addHeader);

        CloseableHttpResponse response = httpClient.execute(request);

        ApiResponse apiResponse = new ApiResponse(response.getStatusLine().getStatusCode(),
                null == response.getEntity() ? null : EntityUtils.toString(response.getEntity()), Arrays.asList(response.getAllHeaders()));

        return apiResponse;
    }

    public ApiResponse getDeleteResponseData(String url, List<Header> headers) throws Exception {
        HttpDelete request = new HttpDelete(url);

        headers.forEach(request::addHeader);
        CloseableHttpResponse response = httpClient.execute(request);

        ApiResponse apiResponse = new ApiResponse(response.getStatusLine().getStatusCode(),
                null == response.getEntity() ? null : EntityUtils.toString(response.getEntity()), Arrays.asList(response.getAllHeaders()));

        return apiResponse;
    }


}
