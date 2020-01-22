package api.helpers;

import api.DTO.auth.AuthResponse;
import api.DTO.internalCustomObjects.ApiResponse;
import api.apiHandlers.BaseApiHandler;
import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class LoginHelper {

    private static String qaClientID = "dZ9Yoz8H1QPcuxsD2bqkWnMVAcMa";
    private static String qaClientSecret = "nEoz_FxyLYV4AlgOnOSXFAuLNlka";
    private static String devClientID = "1LV2F79QB8YHACcxvQn9WilpiMIa";
    private static String devClientSecret = "0x8TWfMiAnP1Pw8ff1m6rsnQMSoa";


    private static String identityServerUrl = ".account.verifonecp.com/oauth2/token";


    @Step
    @Description("Get bearer access token")
    public static String getAccessToken(String env, String username, String password) throws Exception {
        String accessTokenUrl = "https://" + env + identityServerUrl;
        if (StringUtils.equalsIgnoreCase("qa", env)) {
            String basicToken = generateBasicToken(qaClientID, qaClientSecret);
            List<Header> headers = new ArrayList<>();

            headers.add(new BasicHeader("Authorization", "Basic " + basicToken));
            headers.add(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));

            String requestBody = getRequestBody(username, password);
            RequestExecutorHelper executorHelper = new RequestExecutorHelper();
            ApiResponse authResponse = executorHelper.getPostResponseData(accessTokenUrl, headers, requestBody);

            AuthResponse respObject = new Gson().fromJson(authResponse.getResponseBody(), AuthResponse.class);

            BaseApiHandler.reportRequestData(accessTokenUrl, authResponse);
            return respObject.getAccess_token();

        }
        else if(StringUtils.equalsIgnoreCase("dev", env)){
            String basicToken = generateBasicToken(devClientID, devClientSecret);
            List<Header> headers = new ArrayList<>();

            headers.add(new BasicHeader("Authorization", "Basic " + basicToken));
            headers.add(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));

            String requestBody = getRequestBody(username, password);
            RequestExecutorHelper executorHelper = new RequestExecutorHelper();
            ApiResponse authResponse = executorHelper.getPostResponseData(accessTokenUrl, headers, requestBody);

            AuthResponse respObject = new Gson().fromJson(authResponse.getResponseBody(), AuthResponse.class);

            BaseApiHandler.reportRequestData(accessTokenUrl, authResponse);
            return respObject.getAccess_token();
        }

        return "";
    }

    private static String generateBasicToken(String clientId, String clientSecret) {

        return Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
    }

    private static String getRequestBody(String username, String password) {

        return "grant_type=password&username=" + username + "&password=" + password;

    }


}
