package com.verifone.pages.mpAngularPages.components;

import com.aventstack.extentreports.ExtentTest;
import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainMenu {

    private ExtentTest testLog;

    private SelenideElement logo = $("a[data-e2e*=logo] img");
    private SelenideElement applications = $x("//span[text() = 'Applications']");
    private SelenideElement appCatalogue = $("a[ng-reflect-router-link$=catalog]");
    private SelenideElement myApps = $("a[ng-reflect-router-link$=apps]");
    private SelenideElement devices = $x("//span[text() = 'Devices']");
    private SelenideElement deviceList = $("a[href$=devices]");
    private SelenideElement deviceGroups = $("a[href$=groups]");
    private SelenideElement deviceTags = $("a[href$=tags]");
    private SelenideElement settings = $x("//span[contains(text(),'Settings')]");
    private SelenideElement users = $x("//a[contains(text(),'Users')]");
    private SelenideElement currentUser = $(".menu-footer a:first-child");
    private SelenideElement logOutButton = $("svg[class*='sign-out']");

    /**
     * Get Verifone logo
     * @return SelenideElement Verifone logo
     */
    public SelenideElement getLogo() {
        return logo.shouldBe(visible);
    }

    /**
     * Click 'Applications' dropdown
     */
    public void clickApplications() {
        testLog.info("Click 'Applications' dropdown");
        applications.should(exist).click();
    }

    /**
     * Click 'App Catalogue' item
     */
    public void clickAppCatalogue() {
        testLog.info("Click 'App Catalogue' item");
        appCatalogue.should(exist).click();
    }

    /**
     * Click 'My Apps' item
     */
    public void clickMyApps() {
        testLog.info("Click 'My Apps' item");
        myApps.should(exist).click();
    }

    /**
     * Click 'Devices' dropdown
     */
    public void clickDevices() {
        testLog.info("Click 'Devices' dropdown");
        devices.should(exist).click();
    }

    /**
     * Click 'Device List' item
     */
    public void clickDeviceList() {
        testLog.info("Click 'Device List' item");
        deviceList.should(exist).click();
    }

    /**
     * Click 'Device Groups' item
     */
    public void clickDeviceGroups() {
        testLog.info("Click 'Device Groups' item");
        deviceGroups.should(exist).click();
    }

    /**
     * Click 'Device Tags' item
     */
    public void clickDeviceTags() {
        testLog.info("Click 'Device Tags' item");
        deviceTags.should(exist).click();
    }

    /**
     * Click 'Settings' dropdown
     */
    public void clickSettings() {
        testLog.info("Click 'Settings' dropdown");
        settings.should(exist).click();
    }

    /**
     * Click 'Users' item
     */
    public void clickUsers() {
        testLog.info("Click 'Users' item");
        users.should(exist).click();
    }

    /**
     * Open current user profile
     */
    public void openCurrentUserProfile() {
        testLog.info("Open current user profile");
        currentUser.should(exist).click();
    }

    /**
     * Click 'Log Out' button
     */
    public void clickLogOut() {
        testLog.info("Click 'Log Out' button");
        logOutButton.should(exist).click();
    }

    public MainMenu() {
        this.testLog = BasePage.testLog;
    }

}
