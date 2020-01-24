package web.merchant.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainMenu {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
        logger.info("Get logo");
        return logo;
    }

    @Step("Click 'Applications' dropdown")
    public void clickApplications() {
        logger.info("Click 'Applications' dropdown");
        applications.should(exist).click();
    }

    @Step("Click 'App Catalogue' item")
    public void clickAppCatalogue() {
        logger.info("Click 'App Catalogue' item");
        appCatalogue.should(exist).click();
    }

    @Step("Click 'My Apps' item")
    public void clickMyApps() {
        logger.info("Click 'My Apps' item");
        myApps.should(exist).click();
    }

    @Step("Click 'Devices' dropdown")
    public void clickDevices() {
        logger.info("Click 'Devices' dropdown");
        devices.should(exist).click();
    }

    @Step("Click 'Device List' item")
    public void clickDeviceList() {
        logger.info("Click 'Device List' item");
        deviceList.should(exist).click();
    }

    @Step("Click 'Device Groups' item")
    public void clickDeviceGroups() {
        logger.info("Click 'Device Groups' item");
        deviceGroups.should(exist).click();
    }

    @Step("Click 'Device Tags' item")
    public void clickDeviceTags() {
        logger.info("Click 'Device Tags' item");
        deviceTags.should(exist).click();
    }

    @Step("Click 'Settings' dropdown")
    public void clickSettings() {
        logger.info("Click 'Settings' dropdown");
        settings.should(exist).click();
    }

    @Step("Click 'Users' item")
    public void clickUsers() {
        logger.info("Click 'Users' item");
        users.should(exist).click();
    }

    @Step("Open current user profile")
    public void openCurrentUserProfile() {
        logger.info("Open current user profile");
        currentUser.should(exist).click();
    }

    @Step("Click 'Log Out' button")
    public void clickLogOut() {
        logger.info("Click 'Log Out' button");
        logOutButton.should(exist).click();
    }
}
