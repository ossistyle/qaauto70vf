package com.verifone.pages.mpAngularPages.pages;

import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;
import com.verifone.pages.mpAngularPages.components.MainMenu;

import static com.codeborne.selenide.Selenide.*;

public class HomePage extends BasePage {

    public MainMenu mainMenu;

    private final static String URL = "home";
    private final static String TITLE = "Marketplace";

    private SelenideElement companyNameTitle = $x("//h3[contains(@class, 'title')][contains(text(), 'Com')]");
    private SelenideElement devicesOverviewTitle = $x("//h3[contains(@class, 'title')][contains(text(), 'Device')]");
    private SelenideElement manageYourAccountTitle = $x("//h3[contains(@class, 'title')][contains(text(), 'Account')]");
    private SelenideElement installedAppsTitle = $x("//h3[contains(@class, 'title')][contains(text(), 'App')]");


    public HomePage() {
        super(URL, TITLE);
        this.mainMenu = new MainMenu();
    }

}
