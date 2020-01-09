package com.verifone.pages.mpAngularPages.components.applications.catalog;

import com.aventstack.extentreports.ExtentTest;
import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class QuickView {

    private ExtentTest testLog;

    private SelenideElement closeButton = $(byCssSelector("button[class*=del]"));
    private SelenideElement appName = $(byCssSelector(".media-content p[class^=title]"));
    private SelenideElement appImage = $(byCssSelector(".media img"));
    private SelenideElement appId = $(byCssSelector(".media-content p[class^=subtitle]"));
    private SelenideElement appVersionLabel = $(byXpath("//marketplace-app-details//div[contains(text(), 'Ver')]"));
    private SelenideElement appVersionValue = $(byXpath("//marketplace-app-details//div[contains(text(), 'Ver')]/following-sibling::div"));
    private SelenideElement appDescriptionLabel = $(byXpath("//marketplace-app-details//div[contains(text(), 'Desc')]"));
    private SelenideElement appDescriptionValue = $(byXpath("//marketplace-app-details//div[contains(text(), 'Desc')]/following-sibling::div"));
    private SelenideElement appPublishDateLabel = $(byXpath("//marketplace-app-details//div[contains(text(), 'Pub')]"));
    private SelenideElement appPublishDateValue = $(byXpath("//marketplace-app-details//div[contains(text(), 'Pub')]/following-sibling::div"));
    private SelenideElement appUpdateDateLabel = $(byXpath("//marketplace-app-details//div[contains(text(), 'Upd')]"));
    private SelenideElement appUpdateDateValue = $(byXpath("//marketplace-app-details//div[contains(text(), 'Upd')]/following-sibling::div"));

    /**
     * Click 'Close' quick view button
     */
    public void clickCloseButton() {
        testLog.info("Click 'Close' quick view button");
        closeButton.should(exist).click();
    }

    /**
     * Get App name
     * @return SelenideElement App name
     */
    public SelenideElement getAppName() {
        return appName;
    }

    /**
     * Get App image
     * @return SelenideElement App image
     */
    public SelenideElement getAppImage() {
        return appImage.should(visible);
    }

    /**
     * Get App id
     * @return SelenideElement App id
     */
    public SelenideElement getAppId() {
        return appId;
    }

    /**
     * Get App version label
     * @return SelenideElement App version label
     */
    public SelenideElement getAppVersionLabel() {
        return appVersionLabel;
    }

    /**
     * Get App version value
     * @return SelenideElement App version value
     */
    public SelenideElement getAppVersionValue() {
        return appVersionValue;
    }

    /**
     * Get App description label
     * @return SelenideElement App description label
     */
    public SelenideElement getAppDescriptionLabel() {
        return appDescriptionLabel;
    }

    /**
     * Get App description value
     * @return SelenideElement App description value
     */
    public SelenideElement getAppDescriptionValue() {
        return appDescriptionValue;
    }

    /**
     * Get App publish date label
     * @return SelenideElement App publish date label
     */
    public SelenideElement getAppPublishDateLabel() {
        return appPublishDateLabel;
    }

    /**
     * Get App publish date value
     * @return SelenideElement App publish date value
     */
    public SelenideElement getAppPublishDateValue() {
        return appPublishDateValue;
    }

    /**
     * Get App update date label
     * @return SelenideElement App update date label
     */
    public SelenideElement getAppUpdateDateLabel() {
        return appUpdateDateLabel;
    }

    /**
     * Get App update date value
     * @return SelenideElement App update date value
     */
    public SelenideElement getAppUpdateDateValue() {
        return appUpdateDateValue;
    }

    public QuickView() {
        this.testLog = BasePage.testLog;
    }
}
