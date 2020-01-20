package mobile.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class MainMenu {

    private SelenideElement logo = $(byId("logo"));
    private SelenideElement applicationsItem = $(byXpath("//li[@class='menu-group']//span[contains(text(), 'App')]"));
    private SelenideElement appCatalogItem = $(byCssSelector("a[href$=catalog]"));
    private SelenideElement devicesItem = $(byXpath("//li[@class='menu-group']//span[contains(text(), 'Dev')]"));
    private SelenideElement reportsItem = $(byXpath("//li[@class='menu-group']//span[contains(text(), 'Rep')]"));
    private SelenideElement settingsItem = $(byXpath("//li[@class='menu-group']//span[contains(text(), 'Set')]"));
    private SelenideElement usersItem = $(byCssSelector("a[data-e2e='link-users']"));

    @Step("Click 'Applications' menu item")
    public void clickApplications() {
        applicationsItem.should(exist).click();
    }

    @Step("Click 'App Catalog' menu item")
    public void clickAppCatalog() {
        appCatalogItem.should(exist).click();
    }

    @Step("Click 'Devices' menu item")
    public void clickDevices() {
        devicesItem.should(exist).click();
    }

    @Step("Click 'Reports' menu item")
    public void clickReports() {
        reportsItem.should(exist).click();
    }

    @Step("Click 'Settings' menu item")
    public void clickSettings() {
        settingsItem.should(exist).click();
    }

    @Step("Click 'Users' menu item")
    public void clickUsers() {
        usersItem.should(exist).click();
    }

    @Step("Get Logo")
    public SelenideElement getLogo() {
        return logo;
    }
}
