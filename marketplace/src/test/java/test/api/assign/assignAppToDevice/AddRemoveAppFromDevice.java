package test.api.assign.assignAppToDevice;

import api.DTO.assign.AssignToDeviceResponse;
import api.DTO.device.Devices;
import api.DTO.device.GetDevicesByAppResponse;
import api.DTO.internalCustomObjects.ApiResponse;
import api.apiHandlers.AssignmentHandler;
import api.apiHandlers.DeviceHandler;
import api.helpers.LoginHelper;
import com.google.gson.Gson;
import com.google.protobuf.Api;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.api.BaseApiTest;

import java.util.List;

import static utils.allure.LogUtil.reportMessage;

public class AddRemoveAppFromDevice extends BaseApiTest {

    private String merchantToken;
    private Gson jsonParser = new Gson();
    private AssignmentHandler assignmentHandler;
    private DeviceHandler deviceHendler;
    private String offerId;


    @BeforeClass
    @Parameters("env")
    public void preconditions(String env) throws Exception {
        merchantToken = LoginHelper.getAccessToken(env, testData.getString("merchant.username"), testData.getString("merchant.password"));
        reportMessage("Token: " + merchantToken);
        assignmentHandler = new AssignmentHandler(env);
    }


    @Test(description = "precondition-unassign apps from devices",priority = 200)
    public void unassignAppPrecondition() throws Exception{

        //Unassign first app
        ApiResponse getAppsAssignedDevices = deviceHendler.getDevicesWithAppId(merchantToken, testData.getString("merchant.Uuid"), testData.getString("freeapp.assignTest.one"));
        GetDevicesByAppResponse deviceByApp = jsonParser.fromJson(getAppsAssignedDevices.getResponseBody(), GetDevicesByAppResponse.class);
        if(deviceByApp.getNumberDevices() !=0){
            for(Devices devices : deviceByApp.getData().get(0).getDevices()){

                ApiResponse evaluateUnAssignFreeAppResponse = assignmentHandler.doEvaluationUnAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("freeapp.assignTest.one"), devices.getDeviceId(),devices.getId());
                AssignToDeviceResponse evaluateUnAssignApp = jsonParser.fromJson(evaluateUnAssignFreeAppResponse.getResponseBody(), AssignToDeviceResponse.class);
                offerId = evaluateUnAssignApp.getOfferId();

                ApiResponse unAssignFreeAppResponse = assignmentHandler.doUnAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("freeapp.assignTest.one"), offerId, devices.getDeviceId(), devices.getId());
                AssignToDeviceResponse unAssignApp = jsonParser.fromJson(unAssignFreeAppResponse.getResponseBody(), AssignToDeviceResponse.class);

                logger.info("Status of precondition unAssign free app with id-" + testData.getString("freeapp.assignTest.one")+ " is " + unAssignApp.getStatus());
            }
        }
        //Unassign second app
        ApiResponse getAppsAssignedDevices2 = deviceHendler.getDevicesWithAppId(merchantToken, testData.getString("merchant.Uuid"), testData.getString("freeapp.assignTest.two"));
        GetDevicesByAppResponse deviceByApp2 = jsonParser.fromJson(getAppsAssignedDevices2.getResponseBody(), GetDevicesByAppResponse.class);
        if(deviceByApp2.getNumberDevices() !=0){
            for(Devices devices : deviceByApp2.getData().get(0).getDevices()){

                ApiResponse evaluateUnAssignFreeAppResponse = assignmentHandler.doEvaluationUnAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("freeapp.assignTest.two"), devices.getDeviceId(),devices.getId());
                AssignToDeviceResponse evaluateUnAssignApp = jsonParser.fromJson(evaluateUnAssignFreeAppResponse.getResponseBody(), AssignToDeviceResponse.class);
                offerId = evaluateUnAssignApp.getOfferId();

                ApiResponse unAssignFreeAppResponse = assignmentHandler.doUnAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("freeapp.assignTest.two"), offerId, devices.getDeviceId(), devices.getId());
                AssignToDeviceResponse unAssignApp = jsonParser.fromJson(unAssignFreeAppResponse.getResponseBody(), AssignToDeviceResponse.class);

                logger.info("Status of precondition unAssign free app with id-" + testData.getString("freeapp.assignTest.two")+ " is " + unAssignApp.getStatus());
            }
        }
    }

    @Test(description = "Assign free app to one device",priority = 201)
    public void EvaluateAssignFreeApp() throws Exception {

        ApiResponse evaluateAssignFreeAppResponse = assignmentHandler.doEvaluationAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("freeapp.assignTest.one"), testData.getString("deviceId.assignTest.one"), testData.getString("id.assignTest.one"));
        AssignToDeviceResponse evaluateAssignApp = jsonParser.fromJson(evaluateAssignFreeAppResponse.getResponseBody(), AssignToDeviceResponse.class);
        offerId = evaluateAssignApp.getOfferId();

        logger.info("Response code is: " + evaluateAssignFreeAppResponse.getResponseCode());
        logger.info("Price for the app is: " + evaluateAssignApp.getTotalPrice());
        logger.info("Offer id is: " + evaluateAssignApp.getOfferId());
        Assert.assertEquals(evaluateAssignFreeAppResponse.getResponseCode().intValue(), 201, "evaluate assign successfully");
        Assert.assertEquals(evaluateAssignApp.getTotalPrice(), "0.0", "price different from 0");
        Assert.assertEquals(evaluateAssignApp.getData().get(0).getLicensesToPurchase(), "0", "Lisences different from 0");


        ApiResponse assignFreeAppResponse = assignmentHandler.doAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("freeapp.assignTest.one"),offerId, testData.getString("deviceId.assignTest.one"), testData.getString("id.assignTest.one"));
        jsonParser.fromJson(assignFreeAppResponse.getResponseBody(), AssignToDeviceResponse.class);

    }
}
