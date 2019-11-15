package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.*;
import com.verifone.pages.vhqPages.VHQHomePage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.appUtils.MPUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.verifone.tests.steps.mpPortal.Steps.*;
import static com.verifone.tests.steps.mpPortal.Steps.createVHQMumbaiUser;

public class AssignedDeviceToMoreThanOneGroupUI extends BaseTest {


    private ArrayList<String> listOfApp;
    private ArrayList<String> listOfDevices;

    private static String deviceSerialNumber;
    private String firstGroupName;
    private String secondGroupName;

    WebDriver driver;


    // This test describe all the actions related to creation of group and assign device to it.
    @Test(priority = 1, testName = "LogIn & Create First Group", description = "LogIn into CBA Marketplace, Create first group and Assign Device to it.")
    public void CBACreateDeviceGroupTestUI() throws Exception {
        //Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        listOfDevices = new ArrayList<>(BaseTest.envConfig.getListOfDevices().subList(0, 1));
        listOfApp = new ArrayList<>(BaseTest.envConfig.getListOfAppName().subList(0, 1));

        firstGroupName = BaseTest.envConfig.getGroupInfo("MPThirdGroupName");
        String firstGroupDescription = BaseTest.envConfig.getGroupInfo("MPThirdGroupDescription");

        // Get windows handle
        driver = new CBAAssignGroupPage().getDriver();
        MPUtils.getWindowsHandle(driver);

        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();

        //Move to user section
        assignGroup.moveToUsers();

        //Create group & assign device
        //assignGroup.createUsersGroup(firstGroupName, firstGroupDescription, listOfApp, listOfGroup);
        assignGroup.createUsersGroup(firstGroupName, firstGroupDescription, listOfApp, firstGroupName);
        assignGroup.addDeviceToGroup(firstGroupName, listOfDevices, "AddUser");
    }

    //This test describe the availability of the app in the marketplace.
    @Test(priority = 2, testName = "LogIn & Verify App", description = "LogIn into CBA MarketPlace & verify availability of the app to assign.")
    public void CBAVerifyAvailabilityOfAppTestUI() throws Exception {
        //Login to CBAMarket Place
        loginCBA(createAssignUser());

        // Get windows handle
        driver = new CBAAssignPage().getDriver();
        MPUtils.getWindowsHandle(driver);
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();

        //Move to assign app section
        assignApp.moveToAssignApps();

        // Get windows handle
        driver = new CBAAssignGroupPage().getDriver();
        MPUtils.getWindowsHandle(driver);
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();

        //Move to Groups section
        assignGroup.moveToGroups();

        //Verify the availability of the app and if the app is not available then purchase it from the market place.
        assignGroup.getAppToAssignGroups(listOfApp.get(0));
    }

    //This test describe the application assignment to the group
    @Test(priority = 3, testName = "LogIn & Assign App to Group", description = "LogIn in to CBA Marketplace and assign app to first group.")
    public void CBAAssignAppToUserTestUI() throws Exception {
        // Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        //Move to Assign Apps section and assign app to Groups.
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();

        // Get windows handle
        driver = new CBAAssignGroupPage().getDriver();
        MPUtils.getWindowsHandle(driver);
        assignApp.moveToAssignApps();
        assignGroup.moveToGroups();

        // Get windows handle
        driver = new CBAAssignPage().getDriver();
        MPUtils.getWindowsHandle(driver);
        assignApp.searchAppToAssign(listOfApp.get(0));
        assignApp.searchUserToAssign(firstGroupName);
        assignApp.userAssignment();
        assignApp.isAssignUpdated();
    }

    //This test describe the creation of second group and assignment of the device
    @Test(priority = 4, testName = "LogIn & Create Second Group", description = "LogIn into CBA MarketPlace, Create second group and Assign same device to it.")
    public void CBAAssignDeviceToSecondGroupTestUI() throws Exception {
        // Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        secondGroupName = BaseTest.envConfig.getGroupInfo("MPFourthGroupName");
        String secondGroupDescription = BaseTest.envConfig.getGroupInfo("MPFourthGroupDescription");

        // Get windows handle
        driver = new CBAAssignGroupPage().getDriver();
        MPUtils.getWindowsHandle(driver);
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();

        //Move to user section
        assignGroup.moveToUsers();

        //Create group & assign device
        System.out.println("secondGroupName :" + secondGroupName + " secondGroupDescription " + secondGroupDescription);
        assignGroup.createUsersGroup(secondGroupName, secondGroupDescription, listOfApp, secondGroupName);
        assignGroup.addDeviceToGroup(secondGroupName, listOfDevices, "AddUser");
    }

    //This test describe the installation job on vhq which should not be created
    @Test(priority = 5, testName = "LogIn & Verify Install Job Status", description = "Log into VHQ portal and verify Installation job should not be created.")
    public void VHQVerifyInstallAppTestUI() throws Exception {
        //LogIn into VHQ Portal
        loginVHQ(createVHQMumbaiUser());

        System.out.println("listOfDevices - INSTALL :" + listOfDevices);
        System.out.println("listOfApp - INSTALL :" + listOfApp);

        //search jobName in the second device.
        deviceSerialNumber = listOfDevices.get(0);

        // Get windows handle
        driver = new VHQHomePage().getDriver();
        MPUtils.getWindowsHandle(driver);
        VHQHomePage vhqDashboard = PageFactory.getVHQHomePage();
        vhqDashboard.deviceSearch(deviceSerialNumber);
        vhqDashboard.deviceProfile();

        // Get windows handle
        driver = new CBAAssignGroupPage().getDriver();
        MPUtils.getWindowsHandle(driver);
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.searchDeviceJob(listOfApp, "INSTALL", CBAAssignGroupPage.jobCreatedOnGroups, deviceSerialNumber, "negative");
    }

    //This method describe the removal of device from the second group
    @Test(priority = 6, testName = "LogIn & Remove Device from The Second Group", description = "LogIn to CBA Marketplace and remove the device from the second group")
    public void CBARemoveDeviceFromSecondGroupTestUI() {

        //Login into CBA Marketplace.
        loginCBA(createAssignUser());

        // Get windows handle
        driver = new CBAAssignGroupPage().getDriver();
        MPUtils.getWindowsHandle(driver);
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.moveToUsers();
        assignGroup.addDeviceToGroup(secondGroupName, listOfDevices, "RemoveUser");
    }

    //This method describe the deletion of second group.
    @Test(priority = 7, testName = "LogIn & Delete Second Group", description = "LogIn to CBA Marketplace and delete the group.")
    public void CBADeleteSecondGroupTestUI() throws Exception {

        //Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        listOfApp = new ArrayList<>(BaseTest.envConfig.getListOfAppName().subList(0, 1));
        secondGroupName = BaseTest.envConfig.getGroupInfo("MPFourthGroupName");

        // Get windows handle
        driver = new CBAAssignGroupPage().getDriver();
        MPUtils.getWindowsHandle(driver);
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();

        //Delete the group
        assignGroup.moveToUsers();
        assignGroup.verifyApplicationAssignment(listOfApp, secondGroupName);
    }

    //This test describe the UnInstallation job on vhq which should not be created
    @Test(priority = 8, testName = "LogIn & Verify Uninstall Job Status", description = "Log into VHQ portal and verify UnInstallation job should not be created.")
    public void VHQVerifyUninstallAppTestUI() throws Exception {
        //LogIn into VHQ Portal
        loginVHQ(createVHQMumbaiUser());

        deviceSerialNumber = listOfDevices.get(0);

        // Get windows handle
        driver = new VHQHomePage().getDriver();
        MPUtils.getWindowsHandle(driver);
        VHQHomePage vhqDashboard = PageFactory.getVHQHomePage();

        //search jobName in the first device
        vhqDashboard.deviceSearch(deviceSerialNumber);
        vhqDashboard.deviceProfile();

        // Get windows handle
        driver = new CBAAssignGroupPage().getDriver();
        MPUtils.getWindowsHandle(driver);
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.searchDeviceJob(listOfApp, "UNINSTALL", CBAAssignGroupPage.jobCreatedOnGroups, deviceSerialNumber, "negative");
    }

    @Test(priority = 9, testName = "LogIn & Remove Devices from the First Group", description = "LogIn to CBA Marketplace and remove devices from the group.")
    public void CBARemoveDevicesFromFirstGroupTestUI() {
        // Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        // Get windows handle
        driver = new CBAAssignGroupPage().getDriver();
        MPUtils.getWindowsHandle(driver);
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.moveToUsers();
        assignGroup.addDeviceToGroup(firstGroupName, listOfDevices, "RemoveUser");
    }

    //This method describe the deletion of second group.
    @Test(priority = 10, testName = "LogIn & Delete First Group", description = "LogIn to CBA Marketplace and delete the group.")
    public void CBADeleteFirstGroupTestUI() throws Exception {

        //Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        // Get windows handle
        driver = new CBAAssignGroupPage().getDriver();
        MPUtils.getWindowsHandle(driver);
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        
        //Delete the group
        assignGroup.moveToUsers();
        assignGroup.verifyApplicationAssignment(listOfApp, firstGroupName);
    }
}
