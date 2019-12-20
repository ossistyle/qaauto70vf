package com.verifone.pages.mpAngularPages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class MainMenu {

    private SelenideElement applications = $(byXpath("//span[text() = 'Applications']"));
    private SelenideElement appCatalogue = $(byCssSelector("a[ng-reflect-router-link$=catalog]"));
    private SelenideElement myApps = $(byCssSelector("a[ng-reflect-router-link$=apps]"));
    private SelenideElement devices = $(byXpath("//span[text() = 'Devices']"));
    private SelenideElement settings = $(byXpath("//span[contains(text(),'Settings')]"));
    private SelenideElement users = $(byXpath("//a[contains(text(),'Users')]"));
    private SelenideElement logOutButton = $(byCssSelector("svg[class*='sign-out']"));

    /**
     * Click 'Applications' dropdown
     */
    public void clickApplications() {
        applications.should(exist).click();
    }

    /**
     * Click 'App Catalogue' item
     */
    public void clickAppCatalogue() {
        appCatalogue.should(exist).click();
    }

    /**
     * Click 'My Apps' item
     */
    public void clickMyApps() {
        myApps.should(exist).click();
    }

    /**
     * Click 'Devices' dropdown
     */
    public void clickDevices() {
        devices.should(exist).click();
    }

    /**
     * Click 'Settings' dropdown
     */
    public void clickSettings() {
        settings.should(exist).click();
    }

    /**
     * Click 'Settings' item
     */
    public void clickUsers() {
        users.should(exist).click();
    }

    /**
     * Click 'Log Out' button
     */
    public void clickLogOut() {
        logOutButton.should(exist).click();
    }

}
