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

    public void clickCloseButton() {
        testLog.info("Click 'Close' quick view button");
        closeButton.should(exist).click();
    }

    public SelenideElement getAppName() {
        testLog.info("Get app name");
        return appName;
    }

    public SelenideElement getAppImage() {
        testLog.info("Get app image");
        return appImage.shouldBe(visible);
    }

    public SelenideElement getAppId() {
        testLog.info("Get app id");
        return appId;
    }

    public SelenideElement getAppVersionLabel() {
        testLog.info("Get app version label");
        return appVersionLabel;
    }

    public SelenideElement getAppVersionValue() {
        testLog.info("Get app version value");
        return appVersionValue;
    }

    public SelenideElement getAppDescriptionLabel() {
        testLog.info("Get app description label");
        return appDescriptionLabel;
    }

    public SelenideElement getAppDescriptionValue() {
        testLog.info("Get app description value");
        return appDescriptionValue;
    }

    public SelenideElement getAppPublishDateLabel() {
        testLog.info("Get app publish date label");
        return appPublishDateLabel;
    }

    public SelenideElement getAppPublishDateValue() {
        testLog.info("Get app publish date value");
        return appPublishDateValue;
    }

    public SelenideElement getAppUpdateDateLabel() {
        testLog.info("Get app update date label");
        return appUpdateDateLabel;
    }

    public SelenideElement getAppUpdateDateValue() {
        testLog.info("Get app update date value");
        return appUpdateDateValue;
    }

    public QuickView() {
        this.testLog = BasePage.testLog;
    }
}
