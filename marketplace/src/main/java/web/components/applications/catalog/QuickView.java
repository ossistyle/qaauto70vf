package web.components.applications.catalog;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class QuickView {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SelenideElement closeButton = $("button[class*=del]");
    private SelenideElement appName = $(".media-content p[class^=title]");
    private SelenideElement appImage = $(".media img");
    private SelenideElement appId = $(".media-content p[class^=subtitle]");
    private SelenideElement appVersionLabel = $x("//marketplace-app-details//div[contains(text(), 'Ver')]");
    private SelenideElement appVersionValue = $x("//marketplace-app-details//div[contains(text(), 'Ver')]/following-sibling::div");
    private SelenideElement appDescriptionLabel = $x("//marketplace-app-details//div[contains(text(), 'Desc')]");
    private SelenideElement appDescriptionValue = $x("//marketplace-app-details//div[contains(text(), 'Desc')]/following-sibling::div");
    private SelenideElement appPublishDateLabel = $x("//marketplace-app-details//div[contains(text(), 'Pub')]");
    private SelenideElement appPublishDateValue = $x("//marketplace-app-details//div[contains(text(), 'Pub')]/following-sibling::div");
    private SelenideElement appUpdateDateLabel = $x("//marketplace-app-details//div[contains(text(), 'Upd')]");
    private SelenideElement appUpdateDateValue = $x("//marketplace-app-details//div[contains(text(), 'Upd')]/following-sibling::div");

    @Step("Click 'Close' quick view button")
    public void clickCloseButton() {
        logger.info("Click 'Close' quick view button");
        closeButton.should(exist).click();
    }

    @Step("Get app name")
    public SelenideElement getAppName() {
        logger.info("Get app name");
        return appName;
    }

    @Step("Get app image")
    public SelenideElement getAppImage() {
        logger.info("Get app image");
        return appImage;
    }

    @Step("Get app id")
    public SelenideElement getAppId() {
        logger.info("Get app id");
        return appId;
    }

    @Step("Get app version label")
    public SelenideElement getAppVersionLabel() {
        logger.info("Get app version label");
        return appVersionLabel;
    }

    @Step("Get app version value")
    public SelenideElement getAppVersionValue() {
        logger.info("Get app version value");
        return appVersionValue;
    }

    @Step("Get app description label")
    public SelenideElement getAppDescriptionLabel() {
        logger.info("Get app description label");
        return appDescriptionLabel;
    }

    @Step("Get app description value")
    public SelenideElement getAppDescriptionValue() {
        logger.info("Get app description value");
        return appDescriptionValue;
    }

    @Step("Get app publish date label")
    public SelenideElement getAppPublishDateLabel() {
        logger.info("Get app publish date label");
        return appPublishDateLabel;
    }

    @Step("Get app publish date value")
    public SelenideElement getAppPublishDateValue() {
        logger.info("Get app publish date value");
        return appPublishDateValue;
    }

    @Step("Get app update date label")
    public SelenideElement getAppUpdateDateLabel() {
        logger.info("Get app update date label");
        return appUpdateDateLabel;
    }

    @Step("Get app update date value")
    public SelenideElement getAppUpdateDateValue() {
        logger.info("Get app update date label");
        return appUpdateDateValue;
    }
}
