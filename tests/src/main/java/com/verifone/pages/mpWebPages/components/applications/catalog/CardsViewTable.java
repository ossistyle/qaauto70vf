package com.verifone.pages.mpWebPages.components.applications.catalog;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class CardsViewTable {

    private ElementsCollection appNames = $$("marketplace-app-card .card-header-title");
    private ElementsCollection moreButtons = $$("svg[data-icon='angle-down']");
    private ElementsCollection cardImages = $$(".card-image img");
    private ElementsCollection appDescriptions = $$(".card-content p");
    private ElementsCollection appPageButtons = $$("footer a:first-child");
    private ElementsCollection quickViewButtons = $$("footer a:last-child");

    @Step("Click 'App page' button for app with index")
    public void clickAppPageButton(int index) {
        appPageButtons.get(index).should(exist).click();
    }

    @Step("Click 'More' button for app with index")
    public void clickMoreButton(int index) {
        moreButtons.get(index).should(exist).click();
    }

    @Step("Click 'Quick View' button for app with index")
    public void clickQuickViewButton(int index) {
        quickViewButtons.get(index).should(exist).click();
    }

    @Step("Get app names")
    public ElementsCollection getAppNames() {
        return appNames;
    }

    @Step("Get app images")
    public ElementsCollection getCardImages() {
        return cardImages;
    }

    @Step("Get app descriptions")
    public ElementsCollection getAppDescriptions() {
        return appDescriptions;
    }
}
