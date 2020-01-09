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

    private List<SelenideElement> appNames = $$(byCssSelector("marketplace-app-card .card-header-title"));
    private List<SelenideElement> moreButtons = $$(byCssSelector("svg[data-icon='angle-down']"));
    private List<SelenideElement> cardImages = $$(byCssSelector(".card-image img"));
    private List<SelenideElement> appDescriptions = $$(byCssSelector(".card-content p"));
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
     * Click 'More' button
     * @param index App page button index
     */
    public void clickMoreButton(int index) {
        testLog.info(String.format("Click 'More' button for app with %d index", index));
        moreButtons.get(index).should(exist).click();
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
     * Get App name for App with index
     * @return SelenideElement App name
     */
    public SelenideElement getAppName(int index) {
        testLog.info(String.format("Get app name for app with %d index", index));
        return appNames.get(index).should(exist);
    }

    /**
     * Get App image for App with index
     * @return SelenideElement App image
     */
    public SelenideElement getCardImage(int index) {
        testLog.info(String.format("Get app image for app with %d index", index));
        return cardImages.get(index).should(exist);
    }

    /**
     * Get App description for App with index
     * @return SelenideElement App descriptions
     */
    public SelenideElement getAppDescription(int index) {
        testLog.info(String.format("Get app description for app with %d index", index));
        return appDescriptions.get(index).should(exist);
    }

    public CardsViewTable() {
        this.testLog = BasePage.testLog;
    }
}
