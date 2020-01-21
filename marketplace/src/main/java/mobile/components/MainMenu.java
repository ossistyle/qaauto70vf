package mobile.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class MainMenu {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SelenideElement logo = $(byId("logo"));
    private SelenideElement applicationsItem = $(byXpath("//li[@class='menu-group']//span[contains(text(), 'App')]"));
    private SelenideElement appCatalogItem = $(byCssSelector("a[href$=catalog]"));
    private SelenideElement devicesItem = $(byXpath("//li[@class='menu-group']//span[contains(text(), 'Dev')]"));
    private SelenideElement reportsItem = $(byXpath("//li[@class='menu-group']//span[contains(text(), 'Rep')]"));
    private SelenideElement settingsItem = $(byXpath("//li[@class='menu-group']//span[contains(text(), 'Set')]"));
    private SelenideElement usersItem = $(byCssSelector("a[data-e2e='link-users']"));

    @Step("Click 'Applications' menu item")
    public void clickApplications() {
        logger.info("Click 'Applications' menu item");
        applicationsItem.should(exist).click();
    }

    @Step("Click 'App Catalog' menu item")
    public void clickAppCatalog() {
        logger.info("Click 'App Catalog' menu item");
        appCatalogItem.should(exist).click();
    }

    @Step("Click 'Devices' menu item")
    public void clickDevices() {
        logger.info("Click 'Devices' menu item");
        devicesItem.should(exist).click();
    }

    @Step("Click 'Reports' menu item")
    public void clickReports() {
        logger.info("Click 'Reports' menu item");
        reportsItem.should(exist).click();
    }

    @Step("Click 'Settings' menu item")
    public void clickSettings() {
        logger.info("Click 'Settings' menu item");
        settingsItem.should(exist).click();
    }

    @Step("Click 'Users' menu item")
    public void clickUsers() {
        logger.info("Click 'Users' menu item");
        usersItem.should(exist).click();
    }

    @Step("Get Logo")
    public SelenideElement getLogo() {
        logger.info("Get Logo");
        return logo;
    }
}
