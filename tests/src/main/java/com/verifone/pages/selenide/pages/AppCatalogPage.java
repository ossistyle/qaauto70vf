package com.verifone.pages.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.selenide.components.MainMenu;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AppCatalogPage extends Page {

    public MainMenu mainMenu;

    private SelenideElement filterAppsField = $(byCssSelector("input[placeholder^=Filter]"));
    private SelenideElement searchButton = $(byXpath("//button[contains(text(), 'Search')]"));
    private SelenideElement cardsViewTrigger = $(byXpath("//span[contains(text(),'Cards')]"));
    private SelenideElement gridViewTrigger = $(byXpath("//span[contains(text(),'Grid')]"));
    private List<SelenideElement> cardImages = $$(byCssSelector(".card-image img"));
    private List<SelenideElement> appPageButtons = $$(byCssSelector("button[class*=card][class*=modal]"));
    private List<SelenideElement> quickViewButtons = $$(byXpath("//button[contains(text(), 'Quick View')]"));

    public void enterFilterApps(String app) {
        filterAppsField.should(exist).sendKeys(app);
    }

    public void clickSearch() {
        searchButton.should(exist).click();
    }

    public void clickCardsView() {
        cardsViewTrigger.should(exist).click();
    }

    public void clickGridView() {
        gridViewTrigger.should(exist).click();
    }

    public void clickAppPageButton(int index) {
        appPageButtons.get(index).should(exist).click();
    }

    public void clickQuickViewButton(int index) {
        quickViewButtons.get(index).should(exist).click();
    }

    public AppCatalogPage() {
        this.mainMenu = new MainMenu();
    }
}
