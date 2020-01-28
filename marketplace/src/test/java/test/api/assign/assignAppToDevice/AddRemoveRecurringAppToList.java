package test.api.assign.assignAppToDevice;

import api.DTO.assign.AssignToDeviceResponse;
import api.DTO.assign.Pois;
import api.DTO.device.Devices;
import api.DTO.device.GetDevicesByAppResponse;
import api.DTO.internalCustomObjects.ApiResponse;
import api.apiHandlers.AssignmentHandler;
import api.apiHandlers.DeviceHandler;
import api.helpers.LoginHelper;
import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.api.BaseApiTest;

import java.util.ArrayList;
import java.util.List;

import static utils.allure.LogUtil.reportMessage;

public class AddRemoveRecurringAppToList extends BaseApiTest {

    private String merchantToken;
    private Gson jsonParser = new Gson();
    private AssignmentHandler assignmentHandler;
    private DeviceHandler deviceHandler;
    private String offerId;


    @BeforeClass
    @Parameters("env")
    public void preconditions(String env) throws Exception {
        merchantToken = LoginHelper.getAccessToken(env, testData.getString("merchant.username"), testData.getString("merchant.password"));
        reportMessage("Token: " + merchantToken);
        assignmentHandler = new AssignmentHandler(env);
        deviceHandler = new DeviceHandler(env);
    }


    @Test(description = "precondition-unassign apps from devices", priority = 312, enabled = true)
    public void unassignAppPrecondition() throws Exception {

        //Unassign first app
        ApiResponse getAppsAssignedDevices = deviceHandler.getDevicesWithAppId(merchantToken, testData.getString("merchant.Uuid"), testData.getString("recurringApp.assignTest"));
        GetDevicesByAppResponse deviceByApp = jsonParser.fromJson(getAppsAssignedDevices.getResponseBody(), GetDevicesByAppResponse.class);
        if (deviceByApp.getNumberDevices() != 0) {
            for (Devices devices : deviceByApp.getData().get(0).getDevices()) {

                List<Pois> oneDevice = new ArrayList<>();
                oneDevice.add(new Pois(devices.getDeviceId(), devices.getId()));

                ApiResponse evaluateUnAssignAppResponse = assignmentHandler.doEvaluationUnAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("recurringApp.assignTest"), oneDevice);
                AssignToDeviceResponse evaluateUnAssignApp = jsonParser.fromJson(evaluateUnAssignAppResponse.getResponseBody(), AssignToDeviceResponse.class);
                offerId = evaluateUnAssignApp.getOfferId();

                ApiResponse unAssignAppResponse = assignmentHandler.doUnAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("recurringApp.assignTest"), offerId, oneDevice);
                AssignToDeviceResponse unAssignApp = jsonParser.fromJson(unAssignAppResponse.getResponseBody(), AssignToDeviceResponse.class);

                reportMessage("Status of precondition unAssign  app with id-" + testData.getString("recurringApp.assignTest") + " is " + unAssignApp.getStatus());
            }
        } else if (deviceByApp.getNumberDevices() == 0) {
            reportMessage("Precondition: App wasn't assigned. Id: " + testData.getString("recurringApp.assignTest"));
        }
}


    @Test(description = "Assign  app to one device", priority = 313)
    public void AssignApp() throws Exception {

        List<Pois> oneDevice = new ArrayList<>();
        oneDevice.add(new Pois(testData.getString("deviceId.assignTest.one"), testData.getString("id.assignTest.one")));

        ApiResponse evaluateAssignAppResponse = assignmentHandler.doEvaluationAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("recurringApp.assignTest"), oneDevice);
        AssignToDeviceResponse evaluateAssignApp = jsonParser.fromJson(evaluateAssignAppResponse.getResponseBody(), AssignToDeviceResponse.class);
        offerId = evaluateAssignApp.getOfferId();

        reportMessage("Response code is: " + evaluateAssignAppResponse.getResponseCode());
        reportMessage("Price for the app is: " + evaluateAssignApp.getTotalPrice());
        reportMessage("Offer id is: " + evaluateAssignApp.getOfferId());
        Assert.assertEquals(evaluateAssignAppResponse.getResponseCode().intValue(), 201, "Evaluate assign fail");
        Assert.assertEquals(evaluateAssignApp.getTotalPrice(), "15.0", "price different from expected");
        Assert.assertEquals(evaluateAssignApp.getData().get(0).getLicensesToPurchase(), "0", "Licences different from 0");

        ApiResponse assignAppResponse = assignmentHandler.doAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("recurringApp.assignTest"), offerId, oneDevice);
        AssignToDeviceResponse assignToDeviceResponse = jsonParser.fromJson(assignAppResponse.getResponseBody(), AssignToDeviceResponse.class);

        Assert.assertEquals(assignToDeviceResponse.getData().get(0).getPrice(), 15.0, "price different from 0");
        Assert.assertEquals(assignToDeviceResponse.getData().get(0).getDeploymentStatus(), "INSTALL_PENDING_APPMARKET", "Deployment status is incorrect");
        reportMessage("Status of assignment of app " + testData.getString("recurringApp.assignTest") + " to device " + testData.getString("deviceId.assignTest.one") + "is: " + assignToDeviceResponse.getData().get(0).getAssignmentStatus());

    }

    @Test(description = "Assign app for list. one device already has app - Negative", priority = 314)
    public void AssignAppToListNegative() throws Exception {

        List<Pois> twoDevices = new ArrayList<>();
        twoDevices.add(new Pois(testData.getString("deviceId.assignTest.one"), testData.getString("id.assignTest.one")));
        twoDevices.add(new Pois(testData.getString("deviceId.assignTest.two"), testData.getString("id.assignTest.two")));

        ApiResponse evaluateAssignAppResponse = assignmentHandler.doEvaluationAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("recurringApp.assignTest"), twoDevices);
        AssignToDeviceResponse evaluateAssignApp = jsonParser.fromJson(evaluateAssignAppResponse.getResponseBody(), AssignToDeviceResponse.class);

        Assert.assertEquals(evaluateAssignAppResponse.getResponseCode().intValue(), 400, "Test get incorrect status");
        reportMessage("Response code is: " + evaluateAssignAppResponse.getResponseCode() + " - Should be 400");
        reportMessage("Error message is: " + evaluateAssignApp.getErrors().get(0).getMessage());
    }

    @Test(description = "Unassign app from device", priority = 315)
        public void UnassignApp() throws Exception{

        List<Pois> oneDevice = new ArrayList<>();
        oneDevice.add(new Pois(testData.getString("deviceId.assignTest.one"), testData.getString("id.assignTest.one")));

        ApiResponse evaluateUnAssignAppResponse = assignmentHandler.doEvaluationUnAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("recurringApp.assignTest"), oneDevice);
        AssignToDeviceResponse evaluateUnAssignApp = jsonParser.fromJson(evaluateUnAssignAppResponse.getResponseBody(), AssignToDeviceResponse.class);
        offerId = evaluateUnAssignApp.getOfferId();

        ApiResponse unAssignAppResponse = assignmentHandler.doUnAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("recurringApp.assignTest"), offerId, oneDevice);
        AssignToDeviceResponse unAssignApp = jsonParser.fromJson(unAssignAppResponse.getResponseBody(), AssignToDeviceResponse.class);

        reportMessage("Status of precondition unAssign  app with id-" + testData.getString("recurringApp.assignTest") + " is " + unAssignApp.getStatus());
        }

    @Test(description = "Assign  app to List of devices",priority = 316)
    public void AssignAppToList() throws Exception{

        List<Pois> twoDevices = new ArrayList<>();
        twoDevices.add(new Pois(testData.getString("deviceId.assignTest.one"), testData.getString("id.assignTest.one")));
        twoDevices.add(new Pois(testData.getString("deviceId.assignTest.two"), testData.getString("id.assignTest.two")));

        ApiResponse evaluateAssignAppResponse = assignmentHandler.doEvaluationAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("recurringApp.assignTest"), twoDevices);
        AssignToDeviceResponse evaluateAssignApp = jsonParser.fromJson(evaluateAssignAppResponse.getResponseBody(), AssignToDeviceResponse.class);
        offerId = evaluateAssignApp.getOfferId();
        Assert.assertEquals(evaluateAssignApp.getTotalPrice(), "30.0", "price different from expected");

        reportMessage("Response code is: " + evaluateAssignAppResponse.getResponseCode());
        reportMessage("Price for the apps is: " + evaluateAssignApp.getTotalPrice());
        reportMessage("Offer id is: " + evaluateAssignApp.getOfferId());
        Assert.assertEquals(evaluateAssignAppResponse.getResponseCode().intValue(), 201, "Evaluate assign to list fail");
        Assert.assertEquals(evaluateAssignApp.getData().get(0).getLicensesToPurchase(), "0", "Licences different from 0");

        ApiResponse assignAppResponse = assignmentHandler.doAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("recurringApp.assignTest"), offerId, twoDevices);
        AssignToDeviceResponse assignToDeviceResponse = jsonParser.fromJson(assignAppResponse.getResponseBody(), AssignToDeviceResponse.class);

        Assert.assertEquals(assignToDeviceResponse.getData().get(0).getDeploymentStatus(), "INSTALL_PENDING_APPMARKET", "Deployment status is incorrect");

        ApiResponse getAppsAssignedDevices = deviceHandler.getDevicesWithAppId(merchantToken, testData.getString("merchant.Uuid"), testData.getString("recurringApp.assignTest"));
        GetDevicesByAppResponse deviceByApp = jsonParser.fromJson(getAppsAssignedDevices.getResponseBody(), GetDevicesByAppResponse.class);
        Assert.assertEquals(deviceByApp.getNumberDevices().intValue(), 2, "Number of devices different than expected 2");
        reportMessage("Number of devices with app assigned: " + deviceByApp.getNumberDevices().intValue());
        for(Devices device: deviceByApp.getData().get(0).getDevices()){
            reportMessage("Poi with app assigned: "+ device.getId());
        }
    }

    @Test(description = "Unassign app from list of devices",priority = 317)
    public void UnassignAppFromList() throws Exception{

        List<Pois> twoDevices = new ArrayList<>();
        twoDevices.add(new Pois(testData.getString("deviceId.assignTest.one"), testData.getString("id.assignTest.one")));
        twoDevices.add(new Pois(testData.getString("deviceId.assignTest.two"), testData.getString("id.assignTest.two")));

        ApiResponse evaluateUnAssignAppResponse = assignmentHandler.doEvaluationUnAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("recurringApp.assignTest"), twoDevices);
        AssignToDeviceResponse evaluateUnAssignApp = jsonParser.fromJson(evaluateUnAssignAppResponse.getResponseBody(), AssignToDeviceResponse.class);
        offerId = evaluateUnAssignApp.getOfferId();

        reportMessage("Response code is: " + evaluateUnAssignAppResponse.getResponseCode());
        reportMessage("Price for the apps is: " + evaluateUnAssignApp.getTotalPrice());
        reportMessage("Offer id is: " + evaluateUnAssignApp.getOfferId());

        ApiResponse unAssignAppResponse = assignmentHandler.doUnAssignApp(merchantToken, testData.getString("merchant.Uuid"), testData.getString("recurringApp.assignTest"), offerId, twoDevices);
        AssignToDeviceResponse unAssignApp = jsonParser.fromJson(unAssignAppResponse.getResponseBody(), AssignToDeviceResponse.class);

        Assert.assertEquals(unAssignAppResponse.getResponseCode().intValue(), 201, "Unassigned app from list fail");
        reportMessage("Status of precondition unAssign  app with id-" + testData.getString("recurringApp.assignTest") + " is " + unAssignApp.getStatus());
    }
}







