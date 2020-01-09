package com.verifone.pages.mpAngularPages.components.applications.common;

import com.aventstack.extentreports.ExtentTest;
import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class FilterPanel {

    private ExtentTest testLog;

    private SelenideElement header = $(byCssSelector("h1[class=title]"));
    private SelenideElement filterAppsField = $(byCssSelector("input[placeholder^=Filter]"));
    private SelenideElement searchButton = $(byXpath("//button[contains(text(), 'Search')]"));
    private SelenideElement cardsViewTrigger = $(byXpath("//span[contains(text(),'Cards')]"));
    private SelenideElement gridViewTrigger = $(byXpath("//span[contains(text(),'Grid')]"));

    /**
     * Enter text into 'Filter App' field
     * @param app Application name
     */
    public void enterFilterApps(String app) {
        testLog.info(String.format("Enter '%s' into 'Filter Apps' input field", app));
        filterAppsField.should(exist).sendKeys(app);
    }

    /**
     * Click 'Search' button
     */
    public void clickSearch() {
        testLog.info("Click 'Search' button");
        searchButton.should(exist).click();
    }

    /**
     * Switch to 'Card' view
     */
    public void clickCardsView() {
        testLog.info("Switch to 'Card' view");
        cardsViewTrigger.should(exist).click();
    }

    /**
     * Switch to 'Grid' view
     */
    public void clickGridView() {
        testLog.info("Switch to 'Grid' view");
        gridViewTrigger.should(exist).click();
    }

    /**
     * Get page header
     * @return SelenideElement Page header
     */
    public SelenideElement getHeader() {
        return header;
    }

    public FilterPanel() {
        this.testLog = BasePage.testLog;
    }
}
