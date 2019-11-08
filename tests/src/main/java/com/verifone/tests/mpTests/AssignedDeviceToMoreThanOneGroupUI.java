package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAccount;
import com.verifone.pages.mpPages.CBAAssignGroupPage;
import com.verifone.pages.mpPages.CBAAssignPage;
import com.verifone.pages.vhqPages.VHQHomePage;
import com.verifone.tests.BaseTest;
import org.apache.maven.lifecycle.internal.LifecycleStarter;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.verifone.tests.steps.mpPortal.Steps.*;
import static com.verifone.tests.steps.mpPortal.Steps.createVHQMumbaiUser;

public class AssignedDeviceToMoreThanOneGroupUI extends BaseTest {

    private ArrayList<String> listOfGroup;
    private ArrayList<String> listOfApp;
    private ArrayList<String> listOfDevices;
    private ArrayList<String> listOfSecondGroup;
    private static String deviceSerialNumber;

    // This test describe all the actions related to creation of group and assign device to it.
    @Test(priority = 1, testName = "LogIn & Create First Group", description = "LogIn in to CBA Marketplace and create group with more than one user.")
    public void CBACreateDeviceGroupTestUI() throws Exception {
        //Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        listOfDevices = new ArrayList<>(BaseTest.envConfig.getListOfDevices().subList(0, 1));
        listOfApp = new ArrayList<>(BaseTest.envConfig.getListOfAppName().subList(0, 1));
        listOfGroup = BaseTest.envConfig.getList();

        System.out.println("listOfDevices :" + listOfDevices);
        System.out.println("listOfApp :" + listOfApp);

        //Move to user section
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.moveToUsers();

        //Create group & assign device
        System.out.println("listOfGroup 0 :" + listOfGroup.get(0) + " listOfGroup 1" + listOfGroup.get(1));
        assignGroup.createUsersGroup(listOfGroup.get(0), listOfGroup.get(1), listOfApp, listOfGroup);
        assignGroup.addDeviceToGroup(listOfGroup.get(0), listOfDevices, "AddUser");
    }

    //This test describe the availability of the app in the marketplace.
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

    //This test describe the application assignment to the group
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
        assignApp.searchUserToAssign(listOfGroup.get(0));
        assignApp.userAssignment();
        assignApp.isAssignUpdated();
    }

    //This test describe the creation of second group and assignment of the device
    @Test(priority = 4, testName = "LogIn & Assign Device to Second Group", description = "LogIn into CBA MarketPlace and assign device to newly created group.")
    public void CBAAssignDeviceToSecondGroupTestUI() throws Exception {
        // Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        listOfSecondGroup = BaseTest.envConfig.getListOfGroup();

        //Move to user section
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.moveToUsers();

        //Create group & assign device
        System.out.println("listOfGroup 0 :" + listOfGroup.get(0) + " listOfGroup 1" + listOfGroup.get(1));
        assignGroup.createUsersGroup(listOfSecondGroup.get(0), listOfSecondGroup.get(1), listOfApp, listOfGroup);
        assignGroup.addDeviceToGroup(listOfSecondGroup.get(0), listOfDevices, "AddUser");
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
        assignGroup.searchDeviceJob(listOfApp, "INSTALL", CBAAssignGroupPage.jobCreatedOnGroups, deviceSerialNumber, "negative");
    }

    //This method describe the removal of device from the second group
    @Test(priority = 6, testName = "LogIn & Remove Device from The Second Group", description = "LogIn to CBA Marketplace and remove the device from the second group")
    public void CBARemoveDeviceFromSecondGroupTestUI() throws Exception {

        //Login into CBA Marketplace.
        loginCBA(createAssignUser());

        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.moveToUsers();
        assignGroup.addDeviceToGroup(listOfSecondGroup.get(0), listOfDevices, "RemoveUser");
    }

    //This method describe the deletion of second group.
    @Test(priority = 7, testName = "LogIn & Delete Second Group", description = "LogIn to CBA Marketplace and delete the group.")
    public void CBADeleteSecondGroupTestUI() throws Exception {

        //Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        //Delete the group
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.moveToUsers();
        assignGroup.verifyApplicationAssignment(listOfApp, listOfSecondGroup);
    }

    //This test describe the UnInstallation job on vhq which should not be created
    @Test(priority = 8, testName = "LogIn & Verify Uninstall Job Status", description = "Log into VHQ portal and verify UnInstallation job should not be created.")
    public void VHQVerifyUninstallAppTestUI() throws Exception {
        //LogIn into VHQ Portal
        loginVHQ(createVHQMumbaiUser());

        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        VHQHomePage vhqDashboard = PageFactory.getVHQHomePage();

        //search jobName in the first device
        deviceSerialNumber = listOfDevices.get(0);
        vhqDashboard.deviceSearch(deviceSerialNumber);
        vhqDashboard.deviceProfile();
        assignGroup.searchDeviceJob(listOfApp, "UNINSTALL", CBAAssignGroupPage.jobCreatedOnGroups, deviceSerialNumber, "negative");
    }

    @Test(priority = 9, testName = "LogIn & Remove Devices from the First Group", description = "LogIn to CBA Marketplace and remove devices from the group.")
    public void CBARemoveDevicesFromFirstGroupTestUI() {
        // Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.moveToUsers();
        assignGroup.addDeviceToGroup(listOfGroup.get(0), listOfDevices, "RemoveUser");
    }

    //This method describe the deletion of second group.
    @Test(priority = 10, testName = "LogIn & Delete First Group", description = "LogIn to CBA Marketplace and delete the group.")
    public void CBADeleteFirstGroupTestUI() throws Exception {

        //Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        //Delete the group
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.moveToUsers();
        assignGroup.verifyApplicationAssignment(listOfApp, listOfGroup);
    }
}
