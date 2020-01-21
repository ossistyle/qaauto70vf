package api.apiHandlers;

import api.DTO.internalCustomObjects.ApiResponse;
import api.helpers.RequestExecutorHelper;
import com.google.gson.Gson;
import io.qameta.allure.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseApiHandler {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected String env;
    protected RequestExecutorHelper executorHelper = new RequestExecutorHelper();
    protected Gson jsonParser = new Gson();

    public BaseApiHandler(String env) {
        this.env = env;
    }

    @Attachment(value = "{url}", type = "text/plain")
    public static String reportRequestData(String url, ApiResponse apiResponse, String requestBody) {

        return "Request body: " + requestBody + " \nResponse code: " + apiResponse.getResponseCode() + " Response body: \n" + apiResponse.getResponseBody();
    }

    @Attachment(value = "{url}", type = "text/plain")
    public static String reportRequestData(String url, ApiResponse apiResponse) {
        return "Response code: " + apiResponse.getResponseCode() + "\n Response body: \n" + apiResponse.getResponseBody();
    }


}
