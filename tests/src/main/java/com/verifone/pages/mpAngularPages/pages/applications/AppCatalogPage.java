package com.verifone.pages.mpAngularPages.pages.applications;

import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;
import com.verifone.pages.mpAngularPages.components.MainMenu;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AppCatalogPage extends BasePage {

    private final static String URL = "/app-catalog";
    private final static String TITLE = " Application Catalog ";

    public MainMenu mainMenu;

    private SelenideElement header = $(byCssSelector("h1[class=title]"));
    private SelenideElement filterAppsField = $(byCssSelector("input[placeholder^=Filter]"));
    private SelenideElement searchButton = $(byXpath("//button[contains(text(), 'Search')]"));
    private SelenideElement applicationsTab = $(byXpath("//*[@class='tabs']//a[contains(text(), 'App')]"));
    private SelenideElement bundlesTab = $(byXpath("//*[@class='tabs']//a[contains(text(), 'Bundle')]"));
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
     * Click 'Applications' tab
     */
    public void clickApplicationsTab() {
        testLog.info("Click 'Applications' tab");
        applicationsTab.should(exist).click();
    }

    /**
     * Click 'Bundles' tab
     */
    public void clickBundlesTab() {
        testLog.info("Click 'Bundles' tab");
        bundlesTab.should(exist).click();
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
     * Click 'App page' button
     * @param index App page button index
     */
    public void clickAppPageButton(int index) {
        testLog.info(String.format("Click 'App page' button for app with %d index", index));
        appPageButtons.get(index).should(exist).click();
    }

    /**
     * Click 'Quick View' button
     * @param index Quick View button index
     */
    public void clickQuickViewButton(int index) {
        testLog.info(String.format("Click 'Quick View' button for app with %d index", index));
        quickViewButtons.get(index).should(exist).click();
    }

    /**
     * Get page header
     * @return SelenideElement Page header
     */
    public SelenideElement getHeader() {
        return header;
    }

    public AppCatalogPage() {
        super(URL, TITLE);
        this.mainMenu = new MainMenu();
    }
}
