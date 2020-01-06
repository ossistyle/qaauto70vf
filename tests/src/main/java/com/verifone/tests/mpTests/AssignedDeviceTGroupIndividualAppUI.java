package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.*;
import com.verifone.pages.vhqPages.VHQHomePage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class AssignedDeviceTGroupIndividualAppUI extends BaseTest {
    private ArrayList<String> listOfApp;
    private ArrayList<String> listOfDevices;

    private static String deviceSerialNumber;
    private String groupName;
    private String groupDescription;

    //This test describe all the actions which are require to verify the app availability
    @Test(priority = 1, testName = "LogIn & Verify App", description = "Login in to CBA MarketPlace & verify availability of the app")
    public void CBAVerifyAvailabilityOfAppTestUI() throws Exception {
        /*Login to CBAMarket Place*/
        loginCBA(createAssignUser());

        listOfDevices = BaseTest.envConfig.getListOfDevices();
        listOfApp = BaseTest.envConfig.getListOfIndividualApp();

        groupName = BaseTest.envConfig.getGroupInfo("MPFifthGroupName");
        groupDescription = BaseTest.envConfig.getGroupInfo("MPFifthGroupDescription");

        /* Verify the purchased app is present in the My App*/
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        for (String app : listOfApp) {
            assignApp.getAppToAssignUser(app);
        }
    }

    //This test describe all the actions which are require to assign the apps to device.
    @Test(priority = 2, testName = "Login & Assign App to user", description = "Login in to CBA MarketPlace and assign application to the user")
    public void CBAAssignToUserAppUI() throws Exception {

        /* Login to CBAMarket Place */
        loginCBA(createAssignUser());

        /* Move to Assign Apps to User */
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.moveToAssignApps();
        assignApp.btnSelectAssignAppsToUser();

        for (int i = 0; i < listOfApp.size(); i++) {
            assignApp.searchAppToAssign(listOfApp.get(i));
            Thread.sleep(1000);
            assignApp.searchUserToAssign(listOfDevices.get(0));
        }

        assignApp.userAssignment();
        assignApp.isAssignUpdated();
    }

    // This test describe all the actions related to creation of group and assign device to it.
    @Test(priority = 3, testName = "LogIn & Create First Group", description = "LogIn into CBA Marketplace and create group by assigning the device.")
    public void CBACreateDeviceGroupTestUI() throws Exception {
        //Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        listOfDevices = new ArrayList<>(BaseTest.envConfig.getListOfDevices().subList(0, 1));
        //listOfApp = new ArrayList<>(BaseTest.envConfig.getListOfAssignAppsToGroup());
        //listOfApp = new ArrayList<>(BaseTest.envConfig.getListOfAppName().subList(0, 1));

        System.out.println("listOfDevices :" + listOfDevices);
        System.out.println("listOfApp :" + listOfApp);

        //Move to user section
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.moveToUsers();

        //Create group & assign device
        System.out.println("groupName :" + groupName + " firstGroupDescription" + groupDescription);
        assignGroup.createUsersGroup(groupName, groupDescription, listOfApp, groupName);
        assignGroup.addDeviceToGroup(groupName, listOfDevices, "AddUser");
    }

    //This test describe the application assignment to the group
    @Test(priority = 4, testName = "LogIn & Assign App to Group", description = "LogIn in to CBA Marketplace and assign same app to newly created group.")
    public void CBAAssignAppToUserTestUI() throws Exception {
        // Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        //listOfApp = BaseTest.envConfig.getListOfAppName();

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
        assignApp.isAssignUpdated();
    }

    //This test describe the installation job on vhq which should not be created
    @Test(priority = 5, testName = "LogIn & Verify Install Job Status", description = "Log into VHQ portal and verify Installation job should not be created.")
    public void VHQVerifyInstallAppTestUI() throws Exception {
        //LogIn into VHQ Portal
        loginVHQ(createVHQMumbaiUser());

        System.out.println("listOfDevices - INSTALL :" + listOfDevices);
        System.out.println("listOfApp - INSTALL :" + listOfApp);

        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        VHQHomePage vhqDashboard = PageFactory.getVHQHomePage();

        //search jobName in the second device.
        deviceSerialNumber = listOfDevices.get(0);
        vhqDashboard.deviceSearch(deviceSerialNumber);
        vhqDashboard.deviceProfile();
        assignGroup.searchDeviceJob(listOfApp, "INSTALL", CBAAssignPage.jobCreatedOnSubscription, deviceSerialNumber, "negative");
    }

    //This test describe the application UnAssignment from the group
    @Test(priority = 6, testName = "LogIn & UnAssign App from the Group", description = "LogIn in to CBA Marketplace and UnAssign app from newly created group.")
    public void CBAUnAssignAppToUserTestUI() throws Exception {
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
        assignApp.isAssignUpdated();
    }

    //This test describe the UnInstallation job on vhq which should be created
    @Test(priority = 7, testName = "LogIn & Verify Uninstall Job Status", description = "Log into VHQ portal and verify UnInstallation job should be created.")
    public void VHQVerifyUninstallAppTestUI() throws Exception {
        //LogIn into VHQ Portal
        loginVHQ(createVHQMumbaiUser());

        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        VHQHomePage vhqDashboard = PageFactory.getVHQHomePage();

        //search jobName in the first device
        deviceSerialNumber = listOfDevices.get(0);
        vhqDashboard.deviceSearch(deviceSerialNumber);
        vhqDashboard.deviceProfile();
        assignGroup.searchDeviceJob(listOfApp, "UNINSTALL", CBAAssignPage.jobCreatedOnSubscription, deviceSerialNumber, "positive");
    }

    //This test describe all actions related to delete the group
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
