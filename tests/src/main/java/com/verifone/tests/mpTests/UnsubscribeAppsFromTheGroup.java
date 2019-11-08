package com.verifone.tests.mpTests;

import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.*;
import com.verifone.pages.vhqPages.*;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.verifone.tests.steps.mpPortal.Steps.*;


public class UnsubscribeAppsFromTheGroup extends BaseTest {
    private ArrayList<String> listOfGroup;
    private ArrayList<String> listOfApp;
    private ArrayList<String> listOfDevices;
    private static String deviceSerialNumber;

    private static String groupToSearch;


    @Test(priority = 1, testName = "LogIn & Create Group", description = "LogIn in to CBA Marketplace and create group with more than one user.")
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

        //Create group
        System.out.println("listOfGroup 0 :" + listOfGroup.get(0) + " listOfGroup 1" + listOfGroup.get(1));
        assignGroup.createUsersGroup(listOfGroup.get(0), listOfGroup.get(1), listOfApp, listOfGroup);
        assignGroup.addDeviceToGroup(listOfGroup.get(0), listOfDevices, "AddUser");
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
        assignApp.searchUserToAssign(listOfGroup.get(0));
        assignApp.userAssignment();
        assignApp.isAssignUpdated();
    }

    @Test(priority = 4, testName = "LogIn & Verify Install Job Status", description = "Log into VHQ portal and verify whether the job is created after Subscribing the Application.")
    public void VHQVerifyInstallAppTestUI() throws Exception {
        //LogIn into VHQ Portal
        loginVHQ(createVHQMumbaiUser());

        //getCmFiveDeviceSerialNo01 = BaseTest.envConfig.getCmFiveDeviceSerialNo01();
        //listOfDevices = BaseTest.envConfig.getListOfDevices();
        //listOfApp = BaseTest.envConfig.getListOfAppName();

        System.out.println("listOfDevices - INSTALL :" + listOfDevices);
        System.out.println("listOfApp - INSTALL :" + listOfApp);

        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        VHQHomePage vhqDashboard = PageFactory.getVHQHomePage();

        //search jobName in the second device.
        deviceSerialNumber = listOfDevices.get(0);
        vhqDashboard.deviceSearch(deviceSerialNumber);
        vhqDashboard.deviceProfile();
        assignGroup.searchDeviceJob(listOfApp, "INSTALL", CBAAssignPage.jobCreatedOnSubscription, deviceSerialNumber, "positive");
    }

    @Test(priority = 5, testName = "LogIn & Cancel (UnSubscribe) an app", description = "log in to CBA account and remove/cancel app from apps list")
    public void CBAUnsubscribeAppTestUI() throws InterruptedException {

        //loginCBA(createMerchantUser());
        loginCBA(createAssignUser());

        //Cancel apps subscription
        CBAAccount account = PageFactory.getCBAAccount();
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
        assignGroup.searchDeviceJob(listOfApp, "UNINSTALL", CBAAccount.jobCreatedOnUnsubscription, deviceSerialNumber, "positive");
    }

    @Test(priority = 8, testName = "LogIn & Delete Group", description = "LogIn to CBA Marketplace and delete the group.")
    public void CBADeleteGroupTestUI() throws Exception {

        //Login in to CBA Marketplace.
        loginCBA(createAssignUser());

        //Delete the group
        CBAAssignGroupPage assignGroup = PageFactory.getCBAAssignGroupPage();
        assignGroup.moveToUsers();
        assignGroup.verifyApplicationAssignment(listOfApp, listOfGroup);
    }
}
