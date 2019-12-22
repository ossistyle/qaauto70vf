package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.*;
import com.verifone.pages.vhqPages.*;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.verifone.pages.BasePage.testLog;
import static com.verifone.tests.steps.mpPortal.Steps.*;


public class UnsubscribeAppsFromTheGroup extends BaseTest {

    private ArrayList<String> listOfApp;
    private ArrayList<String> listOfDevices;
    private ArrayList<String> searchAppNameOnVHQ;

    private static String deviceSerialNumber;
    private String groupName;
    private String groupDescription;

    @Test(priority = 0, testName = "LogIn & Delete Group If Exists", description = "LogIn in to CBA Marketplace and delete group if it exists.")
    public void CBADeleteGroupIfExistsTestUI() throws Exception {
        loginCBA(createAssignUser());

        listOfDevices = new ArrayList<>(BaseTest.envConfig.getListOfDevices().subList(0, 1));
        listOfApp = BaseTest.envConfig.getListOfCommonApp("MPPurchaseFreeAppThree");
        groupName = BaseTest.envConfig.getGroupInfo("MPSecondGroupName");
        groupDescription = BaseTest.envConfig.getGroupInfo("MPSecondGroupDescription");

        WebDriver driver = new CBAHomePage().getDriver();
        ArrayList<String> availableWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        testLog.info("-------------------------------- Navigate to Assign App ------------------------------");

        //Move to assign app section
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.moveToAssignApps();

        availableWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        testLog.info("-------------------------------- Navigate to Groups ------------------------------");
        //Move to Groups section
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.clickOnGroupsTab();

        availableWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        //check visibility of group name.
        //click on group if it's visible
        testLog.info("-------------------------------- Search Group " + groupName + " ------------------------------");
        if (assignApp.checkVisibilityOfApp(groupName) == 1) {
            testLog.info("--------------- Is group name available : true  ------------");
            assignApp.clickOnAssignApp();
        }

        //check visibility of app
        //click on app name only if it's checked
        testLog.info("-------------------------------- Search App " + listOfApp.get(0) + " ------------------------------");
        if (assignApp.checkVisibilityOfRightPanel(listOfApp.get(0)) == 1) {
            if (assignApp.checkAssignmentOfRightPanel() == 1) {
                testLog.info("------------------ Is app selected  : true -------------------");
                assignApp.clickOnAssignUser();
            }
        }

        //check if next button is disabled
        //click on this button only if it's enabled
        testLog.info("-------------------------------- Check Next button state ------------------------------");
        if (assignApp.checkStateOfNextBtn() != 1) {
            testLog.info("-------------------------------- Is next button enabled : true ------------------------------");
            assignApp.userAssignment();
        }

        availableWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        //delete group
        assignGroup.deleteGroupTest(groupName);
    }

    @Test(priority = 1, testName = "LogIn & Create Group", description = "LogIn in to CBA Marketplace and create group with more than one user.")
    public void CBACreateDeviceGroupTestUI() throws Exception {
        //Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        listOfDevices = new ArrayList<>(BaseTest.envConfig.getListOfDevices().subList(0, 1));
        listOfApp = BaseTest.envConfig.getListOfCommonApp("MPPurchaseFreeAppThree");
        groupName = BaseTest.envConfig.getGroupInfo("MPSecondGroupName");
        groupDescription = BaseTest.envConfig.getGroupInfo("MPSecondGroupDescription");

        System.out.println("listOfDevices :" + listOfDevices);
        System.out.println("listOfApp :" + listOfApp);

        //Move to user section
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.moveToUsers();

        //Create group
        System.out.println("groupName :" + groupName + " groupDescription :" + groupDescription);
        assignGroup.createUsersGroup(groupName, groupDescription, listOfApp, groupName);
        assignGroup.addDeviceToGroup(groupName, listOfDevices, "AddUser");
    }

    @Test(priority = 2, testName = "LogIn & Verify App", description = "LogIn in to CBA MarketPlace & verify availability of the app to assign.")
    public void CBAVerifyAvailabilityOfAppTestUI() throws Exception {
        //Login to CBAMarket Place
        loginCBA(createAssignUser());

        //Move to assign app section
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.moveToAssignApps();

        //Move to Groups section
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.moveToGroups();

        //Verify the availability of the app and if the app is not available then purchase it from the market place.
        assignGroup.getAppToAssignGroups(listOfApp.get(0));
    }

    @Test(priority = 3, testName = "LogIn & Assign App to Group", description = "LogIn in to CBA Marketplace and assign app to newly created group.")
    public void CBAAssignAppToUserTestUI() throws Exception {
        // Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        //Move to Assign Apps section and assign app to Groups.
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();

        assignApp.moveToAssignApps();
        assignGroup.moveToGroups();
        assignApp.searchAppToAssign(listOfApp.get(0));
        assignApp.searchUserToAssign(groupName);
        assignApp.userAssignment();
        assignApp.isAssignUpdated();
    }

    @Test(priority = 4, testName = "LogIn & Verify Install Job Status", description = "Log into VHQ portal and verify whether the job is created after Subscribing the Application.")
    public void VHQVerifyInstallAppTestUI() throws Exception {
        //LogIn into VHQ Portal
        loginVHQ(createVHQMumbaiUser());

        searchAppNameOnVHQ = BaseTest.envConfig.getListOfCommonApp("VHQSearchApp02");
        listOfDevices = new ArrayList<>(BaseTest.envConfig.getListOfDevices().subList(0, 1));

        System.out.println("listOfDevices - INSTALL :" + listOfDevices);
        System.out.println("listOfApp - INSTALL :" + listOfApp);

        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        VHQHomePage vhqDashboard = PageFactory.getVHQHomePage();

        //search jobName in the second device.
        deviceSerialNumber = listOfDevices.get(0);
        vhqDashboard.deviceSearch(deviceSerialNumber);
        vhqDashboard.deviceProfile();
        assignGroup.searchDeviceJob(searchAppNameOnVHQ, "INSTALL", CBAAssignPage.jobCreatedOnSubscription, deviceSerialNumber, "positive");
    }

    @Test(priority = 5, testName = "LogIn & Cancel (UnSubscribe) an app", description = "log in to CBA account and remove/cancel app from apps list")
    public void CBAUnsubscribeAppTestUI() throws InterruptedException {

        //loginCBA(createMerchantUser());
        loginCBA(createAssignUser());

        //Cancel apps subscription
        CBAAccountPage account = PageFactory.getCBAAccount();
        account.cancelSubscribsion(listOfApp.get(0));
    }

    @Test(priority = 7, testName = "LogIn & Verify Uninstall Job Status", description = "Log into VHQ portal and verify UnInstallation job is created.")
    public void VHQVerifyUninstallAppTestUI() throws Exception {
        //LogIn into VHQ Portal
        loginVHQ(createVHQMumbaiUser());

        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        VHQHomePage vhqDashboard = PageFactory.getVHQHomePage();

        //search jobName in the first device
        deviceSerialNumber = listOfDevices.get(0);
        vhqDashboard.deviceSearch(deviceSerialNumber);
        vhqDashboard.deviceProfile();
        assignGroup.searchDeviceJob(searchAppNameOnVHQ, "UNINSTALL", CBAAccountPage.jobCreatedOnUnsubscription, deviceSerialNumber, "positive");
    }

    @Test(priority = 8, testName = "LogIn & Delete Group", description = "LogIn to CBA Marketplace and delete the group.")
    public void CBADeleteGroupTestUI() throws Exception {

        //Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        //Delete the group
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.moveToUsers();
        assignGroup.verifyApplicationAssignment(listOfApp, groupName);
    }
}
