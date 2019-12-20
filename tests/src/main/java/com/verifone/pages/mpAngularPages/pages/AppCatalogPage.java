package com.verifone.pages.mpAngularPages.pages;

import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;
import com.verifone.pages.mpAngularPages.components.MainMenu;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AppCatalogPage extends BasePage {

    private final static String URL = "/app-catalog";
    private final static String TITLE = " Application Catalog ";

    public MainMenu mainMenu;

    private SelenideElement filterAppsField = $(byCssSelector("input[placeholder^=Filter]"));
    private SelenideElement searchButton = $(byXpath("//button[contains(text(), 'Search')]"));
    private SelenideElement cardsViewTrigger = $(byXpath("//span[contains(text(),'Cards')]"));
    private SelenideElement gridViewTrigger = $(byXpath("//span[contains(text(),'Grid')]"));
    private List<SelenideElement> cardImages = $$(byCssSelector(".card-image img"));
    private List<SelenideElement> appPageButtons = $$(byCssSelector("button[class*=card][class*=modal]"));
    private List<SelenideElement> quickViewButtons = $$(byXpath("//button[contains(text(), 'Quick View')]"));

    /**
     * Enter text into 'Filter App' field
     * @param app Application name
     */
    public void enterFilterApps(String app) {
        filterAppsField.should(exist).sendKeys(app);
    }

    /**
     * Click 'Search' button
     */
    public void clickSearch() {
        searchButton.should(exist).click();
        WebDriver driver = getDriver();
    }

    /**
     * Switch to 'Card' view
     */
    public void clickCardsView() {
        cardsViewTrigger.should(exist).click();
        hoverAndClickOnElement(cardsViewTrigger);
    }

    /**
     * Switch to 'Grid' view
     */
    public void clickGridView() {
        gridViewTrigger.should(exist).click();
    }

    /**
     * Click 'App page' button
     * @param index App page button index
     */
    public void clickAppPageButton(int index) {
        appPageButtons.get(index).should(exist).click();
    }

    /**
     * Click 'Quick View' button
     * @param index Quick View button index
     */
    public void clickQuickViewButton(int index) {
        quickViewButtons.get(index).should(exist).click();
    }

    public AppCatalogPage() {
        super(URL, TITLE);
        this.mainMenu = new MainMenu();
    }
}
