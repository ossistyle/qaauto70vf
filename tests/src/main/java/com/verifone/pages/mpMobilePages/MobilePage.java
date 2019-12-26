package com.verifone.pages.mpMobilePages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.verifone.pages.BasePage;
import com.verifone.pages.mpMobilePages.components.Header;
import com.verifone.pages.mpMobilePages.components.MainMenu;
import io.appium.java_client.android.AndroidDriver;

public abstract class MobilePage extends BasePage {

    protected AndroidDriver<SelenideElement> androidDriver;
    private static final String URL = null;
    private static final String TITLE = "Ionic App";

    public Header header;
    public MainMenu mainMenu;

    public MobilePage() {
        super(URL, TITLE);
        this.androidDriver = WebDriverRunner.hasWebDriverStarted()
                ? (AndroidDriver<SelenideElement>) WebDriverRunner.getAndCheckWebDriver() : null;
        this.header = new Header();
        this.mainMenu = new MainMenu();
    }
}
