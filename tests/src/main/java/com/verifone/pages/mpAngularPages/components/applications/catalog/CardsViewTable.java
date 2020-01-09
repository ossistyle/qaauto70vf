package com.verifone.pages.mpAngularPages.components.applications.catalog;

import com.aventstack.extentreports.ExtentTest;
import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;

import java.util.List;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$$;

public class CardsViewTable {

    private ExtentTest testLog;

    private List<SelenideElement> appNames = $$(byCssSelector(".card-header-title"));
    private List<SelenideElement> cardImages = $$(byCssSelector(".card-image img"));
    private List<SelenideElement> appPageButtons = $$(byCssSelector("footer a:first-child"));
    private List<SelenideElement> quickViewButtons = $$(byCssSelector("footer a:last-child"));

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
     * Get App names
     * @return List<SelenideElement> App names
     */
    public List<SelenideElement> getAppNames() {
        return appNames;
    }

    /**
     * Get App images
     * @return List<SelenideElement> App images
     */
    public List<SelenideElement> getCardImages() {
        return cardImages;
    }

    public CardsViewTable() {
        this.testLog = BasePage.testLog;
    }
}
