package com.verifone.pages.mpAngularPages.components.applications.common;

import com.aventstack.extentreports.ExtentTest;
import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class FilterPanel {

    private ExtentTest testLog;

    private SelenideElement header = $("h1[class=title]");
    private SelenideElement filterAppsField = $("input[placeholder^=Filter]");
    private SelenideElement searchButton = $x("//button[contains(text(), 'Search')]");
    private SelenideElement cardsViewTrigger = $x("//span[contains(text(),'Cards')]");
    private SelenideElement gridViewTrigger = $x("//span[contains(text(),'Grid')]");

    public void enterSearch(String text) {
        testLog.info(String.format("Enter '%s' into 'Search' input field", text));
        filterAppsField.should(exist).sendKeys(text);
    }

    public void clickSearch() {
        testLog.info("Click 'Search' button");
        searchButton.should(exist).click();
    }

    public void clickCardsView() {
        testLog.info("Switch to 'Card' view");
        cardsViewTrigger.should(exist).click();
    }

    public void clickGridView() {
        testLog.info("Switch to 'Grid' view");
        gridViewTrigger.should(exist).click();
    }

    public SelenideElement getHeader() {
        testLog.info("Get header");
        return header;
    }

    public FilterPanel() {
        this.testLog = BasePage.testLog;
    }
}
