package web.pages;

import com.codeborne.selenide.SelenideElement;
import web.components.MainMenu;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BaseWebPage {

    public MainMenu mainMenu;

    private String url = "home";

    private SelenideElement companyNameTitle = $x("//h3[contains(@class, 'title')][contains(text(), 'Com')]");
    private SelenideElement devicesOverviewTitle = $x("//h3[contains(@class, 'title')][contains(text(), 'Device')]");
    private SelenideElement manageYourAccountTitle = $x("//h3[contains(@class, 'title')][contains(text(), 'Account')]");
    private SelenideElement installedAppsTitle = $x("//h3[contains(@class, 'title')][contains(text(), 'App')]");


    public HomePage() {
        super();
        super.url = this.url;
        this.mainMenu = new MainMenu();
    }

}
