package com.verifone.pages.mpAngularPages.pages;

import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;
import com.verifone.pages.mpAngularPages.components.MainMenu;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class HomePage extends BasePage {

    public MainMenu mainMenu;

    private final static String URL = "/home";
    private final static String TITLE = "Marketplace";

    private SelenideElement companyNameTitle = $(byXpath("//h3[contains(@class, 'title')][contains(text(), 'Com')]"));
    private SelenideElement devicesOverviewTitle = $(byXpath("//h3[contains(@class, 'title')][contains(text(), 'Device')]"));
    private SelenideElement manageYourAccountTitle = $(byXpath("//h3[contains(@class, 'title')][contains(text(), 'Account')]"));
    private SelenideElement installedAppsTitle = $(byXpath("//h3[contains(@class, 'title')][contains(text(), 'App')]"));


    public HomePage() {
        super(URL, TITLE);
        this.mainMenu = new MainMenu();
    }

}
