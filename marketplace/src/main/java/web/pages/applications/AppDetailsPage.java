package web.pages.applications;

import com.codeborne.selenide.SelenideElement;
import web.components.MainMenu;
import io.qameta.allure.Step;
import web.pages.BaseWebPage;

import static com.codeborne.selenide.Selenide.$;

public class AppDetailsPage extends BaseWebPage {

    private final static String URL = null;
    private final static String TITLE = "Marketplace";

    public MainMenu mainMenu;

    private SelenideElement appName = $(".hero-body .title");
    private SelenideElement appId = $(".hero-body .subtitle");
    private SelenideElement appDescription = $(".hero-body .columns .subtitle");


    @Step("Get app name")
    public SelenideElement getAppName() {
        return appName;
    }

    @Step("Get app id")
    public SelenideElement getAppId() {
        return appId;
    }

    @Step("Get app description")
    public SelenideElement getAppDescription() {
        return appDescription;
    }

    public AppDetailsPage() {
        super(URL);
        this.mainMenu = new MainMenu();
    }
}
