package com.verifone.pages.mpAngularPages.pages.applications;

import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;
import com.verifone.pages.mpAngularPages.components.MainMenu;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AppDetailsPage extends BasePage {

    private final static String URL = null;
    private final static String TITLE = "Marketplace";

    public MainMenu mainMenu;

    private SelenideElement appName = $(byCssSelector(".hero-body .title"));
    private SelenideElement appId = $(byCssSelector(".hero-body .subtitle"));
    private SelenideElement appDescription = $(byCssSelector(".hero-body .columns .subtitle"));

    /**
     * Get App name
     * @return SelenideElement App name
     */
    public SelenideElement getAppName() {
        return appName;
    }

    /**
     * Get App id
     * @return SelenideElement App id
     */
    public SelenideElement getAppId() {
        return appId;
    }

    /**
     * Get App description
     * @return SelenideElement App description
     */
    public SelenideElement getAppDescription() {
        return appDescription;
    }

    public AppDetailsPage() {
        super(URL, TITLE);
        this.mainMenu = new MainMenu();
    }
}
