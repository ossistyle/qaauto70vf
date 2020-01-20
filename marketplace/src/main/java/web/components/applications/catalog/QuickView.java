package web.components.applications.catalog;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class QuickView {

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
        closeButton.should(exist).click();
    }

    @Step("Get app name")
    public SelenideElement getAppName() {
        return appName;
    }

    @Step("Get app image")
    public SelenideElement getAppImage() {
        return appImage;
    }

    @Step("Get app id")
    public SelenideElement getAppId() {
        return appId;
    }

    @Step("Get app version label")
    public SelenideElement getAppVersionLabel() {
        return appVersionLabel;
    }

    @Step("Get app version value")
    public SelenideElement getAppVersionValue() {
        return appVersionValue;
    }

    @Step("Get app description label")
    public SelenideElement getAppDescriptionLabel() {
        return appDescriptionLabel;
    }

    @Step("Get app description value")
    public SelenideElement getAppDescriptionValue() {
        return appDescriptionValue;
    }

    @Step("Get app publish date label")
    public SelenideElement getAppPublishDateLabel() {
        return appPublishDateLabel;
    }

    @Step("Get app publish date value")
    public SelenideElement getAppPublishDateValue() {
        return appPublishDateValue;
    }

    @Step("Get app update date label")
    public SelenideElement getAppUpdateDateLabel() {
        return appUpdateDateLabel;
    }

    @Step("Get app update date value")
    public SelenideElement getAppUpdateDateValue() {
        return appUpdateDateValue;
    }
}
