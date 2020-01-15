package com.verifone.pages.mpAngularPages.components.applications.catalog;

import com.aventstack.extentreports.ExtentTest;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class CardsViewTable {

    private ExtentTest testLog;

    private ElementsCollection appNames = $$("marketplace-app-card .card-header-title");
    private ElementsCollection moreButtons = $$("svg[data-icon='angle-down']");
    private ElementsCollection cardImages = $$(".card-image img");
    private ElementsCollection appDescriptions = $$(".card-content p");
    private ElementsCollection appPageButtons = $$("footer a:first-child");
    private ElementsCollection quickViewButtons = $$("footer a:last-child");

    public void clickAppPageButton(int index) {
        testLog.info(String.format("Click 'App page' button for app with %d index", index));
        appPageButtons.get(index).should(exist).click();
    }

    public void clickMoreButton(int index) {
        testLog.info(String.format("Click 'More' button for app with %d index", index));
        moreButtons.get(index).should(exist).click();
    }

    public void clickQuickViewButton(int index) {
        testLog.info(String.format("Click 'Quick View' button for app with %d index", index));
        quickViewButtons.get(index).should(exist).click();
    }

    public SelenideElement getAppName(int index) {
        testLog.info(String.format("Get app name for app with %d index", index));
        return appNames.get(index).should(exist);
    }

    public SelenideElement getCardImage(int index) {
        testLog.info(String.format("Get app image for app with %d index", index));
        return cardImages.get(index);
    }

    public SelenideElement getAppDescription(int index) {
        testLog.info(String.format("Get app description for app with %d index", index));
        return appDescriptions.get(index).should(exist);
    }

    public CardsViewTable() {
        this.testLog = BasePage.testLog;
    }
}
