package com.verifone.pages.mpMobilePages.components;

import com.aventstack.extentreports.ExtentTest;
import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MainMenu {

    private ExtentTest testLog;

    private SelenideElement logo = $(byId("logo"));
    private SelenideElement applicationsItem = $(byXpath("//li[@class='menu-group']//span[contains(text(), 'App')]"));
    private SelenideElement appCatalogItem = $(byCssSelector("a[href$=catalog]"));
    private SelenideElement devicesItem = $(byXpath("//li[@class='menu-group']//span[contains(text(), 'Dev')]"));
    private SelenideElement reportsItem = $(byXpath("//li[@class='menu-group']//span[contains(text(), 'Rep')]"));
    private SelenideElement settingsItem = $(byXpath("//li[@class='menu-group']//span[contains(text(), 'Set')]"));
    private SelenideElement usersItem = $(byCssSelector("a[data-e2e='link-users']"));

    /**
     * Click 'Applications' menu item
     */
    public void clickApplications() {
        testLog.info("Click 'Applications' menu item");
        applicationsItem.should(exist).click();
    }

    /**
     * Click 'App Catalog' menu item
     */
    public void clickAppCatalog() {
        testLog.info("Click 'App Catalog' menu item");
        appCatalogItem.should(exist).click();
    }

    /**
     * Click 'Devices' menu item
     */
    public void clickDevices() {
        testLog.info("Click 'Devices' menu item");
        devicesItem.should(exist).click();
    }

    /**
     * Click 'Reports' menu item
     */
    public void clickReports() {
        testLog.info("Click 'Reports' menu item");
        reportsItem.should(exist).click();
    }

    /**
     * Click 'Settings' menu item
     */
    public void clickSettings() {
        testLog.info("Click 'Settings' menu item");
        settingsItem.should(exist).click();
    }

    /**
     * Click 'Users' menu item
     */
    public void clickUsers() {
        testLog.info("Click 'Users' menu item");
        usersItem.should(exist).click();
    }

    /**
     * Get Logo
     * @return SelenideElement Logo
     */
    public SelenideElement getLogo() {
        return logo;
    }

    public MainMenu() {
        this.testLog = BasePage.testLog;
    }
}
