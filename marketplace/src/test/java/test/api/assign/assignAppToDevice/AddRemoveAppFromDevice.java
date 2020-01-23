package test.api.assign.assignAppToDevice;

import api.DTO.assign.AssignToDeviceResponse;
import api.DTO.internalCustomObjects.ApiResponse;
import api.apiHandlers.AssignmentHandler;
import api.helpers.LoginHelper;
import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.api.BaseApiTest;
import static utils.allure.LogUtil.reportMessage;

public class AddRemoveAppFromDevice extends BaseApiTest {

    private String merchantToken;
    private Gson jsonParser = new Gson();
    private AssignmentHandler assignmentHandler;
    private String offerId;


    @BeforeClass
    @Parameters("env")
    public void preconditions(String env) throws Exception {
        merchantToken = LoginHelper.getAccessToken(env, "yonimerchant@getnada.com", "Veri1234");
        reportMessage("Token: " + merchantToken);
        assignmentHandler = new AssignmentHandler(env);
    }


    @Test(description = "Assign free app to one device")
    public void EvaluateAssignFreeApp() throws Exception {
        //Precondition
        try
        {
            ApiResponse evaluateUnAssignFreeAppResponse = assignmentHandler.doEvaluationUnAssignApp(merchantToken,"1b5bbd88-3705-47c5-a6c5-a2d65f4bdb8c","c131093d-73d5-4244-a494-d8e41671ddec","1b5bbd88-3705-47c5-a6c5-a2d65f4bdb8c", "1c6254ad-b8f8-4432-9440-ee286aa83e8b");
            AssignToDeviceResponse evaluateUnAssignApp = jsonParser.fromJson(evaluateUnAssignFreeAppResponse.getResponseBody(), AssignToDeviceResponse.class);
            Assert.assertEquals(evaluateUnAssignApp.getStatus(), "SUCCESS", "evaluate assign fail");
            offerId = evaluateUnAssignApp.getOfferId();
            ApiResponse unAssignFreeAppResponse = assignmentHandler.doUnAssignApp(merchantToken,"1b5bbd88-3705-47c5-a6c5-a2d65f4bdb8c","c131093d-73d5-4244-a494-d8e41671ddec", offerId,"1b5bbd88-3705-47c5-a6c5-a2d65f4bdb8c", "1c6254ad-b8f8-4432-9440-ee286aa83e8b");
            AssignToDeviceResponse unAssignApp = jsonParser.fromJson(unAssignFreeAppResponse.getResponseBody(), AssignToDeviceResponse.class);
            logger.info("Status of precondition unAssign free app: " + unAssignApp.getStatus());
        }
        catch(Exception e)
        {
            logger.info("Precondition - no free apps assigned");
        }

        //Evaluate + Assign for 1 device
        ApiResponse evaluateAssignFreeAppResponse = assignmentHandler.doEvaluationAssignApp(merchantToken,"1b5bbd88-3705-47c5-a6c5-a2d65f4bdb8c","c131093d-73d5-4244-a494-d8e41671ddec","1b5bbd88-3705-47c5-a6c5-a2d65f4bdb8c", "1c6254ad-b8f8-4432-9440-ee286aa83e8b");
        AssignToDeviceResponse evaluateAssignApp = jsonParser.fromJson(evaluateAssignFreeAppResponse.getResponseBody(), AssignToDeviceResponse.class);
        offerId = evaluateAssignApp.getOfferId();

        logger.info("Response code is: " + evaluateAssignFreeAppResponse.getResponseCode());
        logger.info("Price for the app is: " + evaluateAssignApp.getTotalPrice());
        logger.info("Offer id is: " + evaluateAssignApp.getOfferId());
        Assert.assertEquals(evaluateAssignFreeAppResponse.getResponseCode().intValue(), 201, "evaluate assign successfully");
        Assert.assertEquals(evaluateAssignApp.getTotalPrice(), "0.0", "price different from 0");
        Assert.assertEquals(evaluateAssignApp.getData().get(0).getLicensesToPurchase(), "0", "Lisences different from 0");


        ApiResponse assignFreeAppResponse = assignmentHandler.doAssignApp(merchantToken,"1b5bbd88-3705-47c5-a6c5-a2d65f4bdb8c","c131093d-73d5-4244-a494-d8e41671ddec",offerId,"1b5bbd88-3705-47c5-a6c5-a2d65f4bdb8c", "1c6254ad-b8f8-4432-9440-ee286aa83e8b");
        jsonParser.fromJson(assignFreeAppResponse.getResponseBody(), AssignToDeviceResponse.class);

    }
}
