package com.verifone.pages.mpMobilePages.components;

import com.aventstack.extentreports.ExtentTest;
import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Header {

    private ExtentTest testLog;

    private SelenideElement title = $(byCssSelector("ion-title"));
    private SelenideElement menuButton = $(byCssSelector("ion-menu-toggle"));

    /**
     * Get title
     * @return Title text
     */
    public String getTitle() {
        testLog.info("Get title");
        return title.should(exist).text();
    }

    /**
     * Click 'Menu' button
     */
    public void clickMenuButton() {
        testLog.info("Click 'Menu' button");
        menuButton.should(exist).click();
    }

    public Header() {
        this.testLog = BasePage.testLog;
    }
}
