package web.pages.applications;

import com.codeborne.selenide.SelenideElement;
import web.components.MainMenu;
import io.qameta.allure.Step;
import web.pages.BaseWebPage;

import static com.codeborne.selenide.Selenide.$;

public class AppDetailsPage extends BaseWebPage {

    private String url = null;

    public MainMenu mainMenu;

    private SelenideElement appName = $(".hero-body .title");
    private SelenideElement appId = $(".hero-body .subtitle");
    private SelenideElement appDescription = $(".hero-body .columns .subtitle");


    @Step("Get app name")
    public SelenideElement getAppName() {
        logger.info("Get app name");
        return appName;
    }

    @Step("Get app id")
    public SelenideElement getAppId() {
        logger.info("Get app id");
        return appId;
    }

    @Step("Get app description")
    public SelenideElement getAppDescription() {
        logger.info("Get app description");
        return appDescription;
    }

    public AppDetailsPage() {
        super();
        super.url = this.url;
        this.mainMenu = new MainMenu();
    }
}
