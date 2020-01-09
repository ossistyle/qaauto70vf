package com.verifone.pages.mpAngularPages.components;

import com.aventstack.extentreports.ExtentTest;
import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class MainMenu {

    private ExtentTest testLog;

    private SelenideElement logo = $(byCssSelector("a[data-e2e*=logo] img"));
    private SelenideElement applications = $(byXpath("//span[text() = 'Applications']"));
    private SelenideElement appCatalogue = $(byCssSelector("a[ng-reflect-router-link$=catalog]"));
    private SelenideElement myApps = $(byCssSelector("a[ng-reflect-router-link$=apps]"));
    private SelenideElement devices = $(byXpath("//span[text() = 'Devices']"));
    private SelenideElement deviceList = $(byCssSelector("a[href$=devices]"));
    private SelenideElement deviceGroups = $(byCssSelector("a[href$=groups]"));
    private SelenideElement deviceTags = $(byCssSelector("a[href$=tags]"));
    private SelenideElement settings = $(byXpath("//span[contains(text(),'Settings')]"));
    private SelenideElement users = $(byXpath("//a[contains(text(),'Users')]"));
    private SelenideElement currentUser = $(byCssSelector(".menu-footer a:first-child"));
    private SelenideElement logOutButton = $(byCssSelector("svg[class*='sign-out']"));

    /**
     * Get Verifone logo
     * @return SelenideElement Verifone logo
     */
    public SelenideElement getLogo() {
        return logo.should(visible);
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
