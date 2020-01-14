package com.verifone.tests.api.tests.e2ePOC.helpers;

import com.google.gson.Gson;
import com.verifone.tests.api.tests.e2ePOC.DTO.auth.AuthResponse;
import com.verifone.tests.api.tests.e2ePOC.DTO.internalCustomObjects.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class LoginHelper {

    private static String qaClientID = "dZ9Yoz8H1QPcuxsD2bqkWnMVAcMa";
    private static String qaClientSecret = "nEoz_FxyLYV4AlgOnOSXFAuLNlka";

    private static String identityServerUrl = ".account.verifonecp.com/oauth2/token";


    public static String getRequestToken(String env, String username, String password) throws Exception {
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
