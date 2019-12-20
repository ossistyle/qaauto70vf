package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.*;
import com.verifone.pages.vhqPages.VHQHomePage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.verifone.pages.BasePage.testLog;
import static com.verifone.tests.steps.mpPortal.Steps.*;

/**
 * This test case described Device Groups (Virtual Users and User (Device) Groups)
 *
 * @author : Prashant Lokhande
 */
public class AssignGroupsToApps extends BaseTest {

    private ArrayList<String> listOfApp;
    private ArrayList<String> listOfDevices;

    private static String deviceSerialNumber;
    private String groupName;
    private String groupDescription;

    @Test(priority = 0, testName = "LogIn & Delete Group If Exists", description = "LogIn in to CBA Marketplace and delete group if it exists.")
    public void CBADeleteGroupIfExistsTestUI() throws Exception {
        //Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        //get the list of devices and apps
        listOfDevices = BaseTest.envConfig.getListOfDevices();
        listOfApp = BaseTest.envConfig.getListOfAssignAppsToGroup();

        //get the group name and description
        groupName = BaseTest.envConfig.getGroupInfo("MPFirstGroupName");
        groupDescription = BaseTest.envConfig.getGroupInfo("MPFirstGroupDescription");

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

        //check visibility of app
        //click on app name only if it's checked
        testLog.info("-------------------------------- Search App " + listOfApp.get(1) + " ------------------------------");
        if (assignApp.checkVisibilityOfRightPanel(listOfApp.get(1)) == 1) {
            if (assignApp.checkAssignmentOfRightPanel() == 1) {
                testLog.info("------------------ Is app selected  : true -------------------");
                assignApp.clickOnAssignUser();
            }
        }

        //check if next button is disabled
        //click on this button only if it's disabled
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

        listOfDevices = BaseTest.envConfig.getListOfDevices();
        listOfApp = BaseTest.envConfig.getListOfAssignAppsToGroup();

        groupName = BaseTest.envConfig.getGroupInfo("MPFirstGroupName");
        groupDescription = BaseTest.envConfig.getGroupInfo("MPFirstGroupDescription");

        //Move to user section
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.moveToUsers();

        //Create group
        System.out.println("groupName :" + groupName + " groupDescription" + groupDescription);
        assignGroup.createUsersGroup(groupName, groupDescription, listOfApp, groupName);
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
        for (String app : listOfApp) {
            System.out.println("list of app name : " + app);
            assignGroup.getAppToAssignGroups(app);
        }
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

        //Assign list of apps to the group
        for (String app : listOfApp) {
            assignApp.searchAppToAssign(app);
            assignApp.searchUserToAssign(groupName);
        }

        assignApp.userAssignment();
    }

    @Test(priority = 4, testName = "LogIn & Assign Group to Device", description = "LogIn to CBA Marketplace and assign app to device.")
    public void CBAAssignGroupToDeviceTestUI() {
        // Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.moveToUsers();
        assignGroup.addDeviceToGroup(groupName, listOfDevices, "AddUser");
    }

    @Test(priority = 5, testName = "LogIn & Verify Install Job Status", description = "Log into VHQ portal and verify whether the job is created after Subscribing the Application.")
    public void VHQVerifyInstallAppTestUI() throws Exception {
        //LogIn into VHQ Portal
        loginVHQ(createVHQMumbaiUser());

        //getCmFiveDeviceSerialNo01 = BaseTest.envConfig.getCmFiveDeviceSerialNo01();
        listOfDevices = BaseTest.envConfig.getListOfDevices();
        listOfApp = BaseTest.envConfig.getListOfAssignAppsToGroup();

        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        VHQHomePage vhqDashboard = PageFactory.getVHQHomePage();

        //search jobName in the first device
        deviceSerialNumber = listOfDevices.get(0);
        vhqDashboard.deviceSearch(deviceSerialNumber);
        vhqDashboard.deviceProfile();
        assignGroup.searchDeviceJob(listOfApp, "INSTALL", CBAAssignGroupPage.jobCreatedOnGroups, deviceSerialNumber, "positive");

        //search jobName in the second device.
        deviceSerialNumber = listOfDevices.get(1);
        vhqDashboard.deviceSearch(deviceSerialNumber);
        vhqDashboard.deviceProfile();
        assignGroup.searchDeviceJob(listOfApp, "INSTALL", CBAAssignGroupPage.jobCreatedOnGroups, deviceSerialNumber, "positive");
    }


    @Test(priority = 6, testName = "LogIn & Remove Devices from the Group", description = "LogIn to CBA Marketplace and remove devices from the group.")
    public void CBARemoveDevicesFromGroupTestUI() {
        // Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.moveToUsers();
        assignGroup.addDeviceToGroup(groupName, listOfDevices, "RemoveUser");
    }


    @Test(priority = 7, testName = "LogIn & Verify Uninstall Job Status", description = "Log into VHQ portal and verify UnInstallation job is created.")
    public void VHQVerifyUninstallAppTestUI() throws Exception {
        //LogIn into VHQ Portal
        loginVHQ(createVHQMumbaiUser());

        //getCmFiveDeviceSerialNo01 = BaseTest.envConfig.getCmFiveDeviceSerialNo01();
        listOfDevices = BaseTest.envConfig.getListOfDevices();
        listOfApp = BaseTest.envConfig.getListOfAssignAppsToGroup();

        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        VHQHomePage vhqDashboard = PageFactory.getVHQHomePage();

        //search jobName in the first device
        deviceSerialNumber = listOfDevices.get(0);
        vhqDashboard.deviceSearch(deviceSerialNumber);
        vhqDashboard.deviceProfile();
        assignGroup.searchDeviceJob(listOfApp, "UNINSTALL", CBAAssignGroupPage.jobCreatedOnGroups, deviceSerialNumber, "positive");

        //search jobName in the second device.
        deviceSerialNumber = listOfDevices.get(1);
        vhqDashboard.deviceSearch(deviceSerialNumber);
        vhqDashboard.deviceProfile();
        assignGroup.searchDeviceJob(listOfApp, "UNINSTALL", CBAAssignGroupPage.jobCreatedOnGroups, deviceSerialNumber, "positive");

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
