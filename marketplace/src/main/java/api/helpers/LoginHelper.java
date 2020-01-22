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
import java.util.ResourceBundle;

public class LoginHelper {

    private static String identityServerUrl = ".account.verifonecp.com/oauth2/token";


    @Step
    @Description("Get bearer access token")
    public static String getAccessToken(String env, String username, String password) throws Exception {
        ResourceBundle testData = ResourceBundle.getBundle("testData/" + env.toLowerCase());
        String accessTokenUrl = "https://" + env + identityServerUrl;
        if (StringUtils.equalsIgnoreCase("qa", env)) {
            String basicToken = generateBasicToken(testData.getString("client.id"), testData.getString("clien.secret"));
            List<Header> headers = new ArrayList<>();

            headers.add(new BasicHeader("Authorization", "Basic " + basicToken));
            headers.add(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));

            String requestBody = getRequestBody(username, password);
            RequestExecutorHelper executorHelper = new RequestExecutorHelper();
            ApiResponse authResponse = executorHelper.getPostResponseData(accessTokenUrl, headers, requestBody);

            AuthResponse respObject = new Gson().fromJson(authResponse.getResponseBody(), AuthResponse.class);

            BaseApiHandler.reportRequestData(accessTokenUrl, authResponse, requestBody);
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
