package api.helpers;

import api.DTO.internalCustomObjects.ApiResponse;
import org.apache.http.Header;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.util.Arrays;
import java.util.List;

public class RequestExecutorHelper {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private CloseableHttpClient httpClient;

    public RequestExecutorHelper() {
        try {
            SSLContextBuilder sslContextBuilder = SSLContextBuilder.create();
            sslContextBuilder.loadTrustMaterial(new org.apache.http.conn.ssl.TrustSelfSignedStrategy());
            SSLContext sslContext = sslContextBuilder.build();
            SSLConnectionSocketFactory sslSocketFactory =
                    new SSLConnectionSocketFactory(sslContext, new org.apache.http.conn.ssl.DefaultHostnameVerifier());

            HttpClientBuilder httpClientBuilder = HttpClients.custom().setSSLSocketFactory(sslSocketFactory);
            httpClient = httpClientBuilder.build();
        } catch (Exception e) {
            logger.error("Error creating httpclient with invalid certificate handling: " + e);
            logger.warn("Create default http client");
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
