package mobile.pages.applications;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import mobile.MobilePage;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;

public class ApplicationsPage extends MobilePage {

    private SelenideElement filterAppsField = $(byCssSelector("input[placeholder*=Filter]"));

    @Step("Enter into search field")
    public void enterFilterApps(String text) {
        logger.info("Enter into filter apps: " + text);
        filterAppsField.should(exist).sendKeys(text);
    }

    public ApplicationsPage() {
        super();
    }
}
