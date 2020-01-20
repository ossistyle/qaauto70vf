package web.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainMenu {

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

    @Step("Get logo")
    public SelenideElement getLogo() {
        return logo;
    }

    @Step("Click 'Applications' dropdown")
    public void clickApplications() {
        applications.should(exist).click();
    }

    @Step("Click 'App Catalogue' item")
    public void clickAppCatalogue() {
        appCatalogue.should(exist).click();
    }

    @Step("Click 'My Apps' item")
    public void clickMyApps() {
        myApps.should(exist).click();
    }

    @Step("Click 'Devices' dropdown")
    public void clickDevices() {
        devices.should(exist).click();
    }

    @Step("Click 'Device List' item")
    public void clickDeviceList() {
        deviceList.should(exist).click();
    }

    @Step("Click 'Device Groups' item")
    public void clickDeviceGroups() {
        deviceGroups.should(exist).click();
    }

    @Step("Click 'Device Tags' item")
    public void clickDeviceTags() {
        deviceTags.should(exist).click();
    }

    @Step("Click 'Settings' dropdown")
    public void clickSettings() {
        settings.should(exist).click();
    }

    @Step("Click 'Users' item")
    public void clickUsers() {
        users.should(exist).click();
    }

    @Step("Open current user profile")
    public void openCurrentUserProfile() {
        currentUser.should(exist).click();
    }

    @Step("Click 'Log Out' button")
    public void clickLogOut() {
        logOutButton.should(exist).click();
    }
}
