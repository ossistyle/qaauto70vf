package com.verifone.pages.mpWebPages.pages.applications;

import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;
import com.verifone.pages.mpWebPages.components.MainMenu;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class AppDetailsPage extends BasePage {

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
        super(URL, TITLE);
        this.mainMenu = new MainMenu();
    }
}
