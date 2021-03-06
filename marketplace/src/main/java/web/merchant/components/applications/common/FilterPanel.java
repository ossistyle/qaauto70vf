package web.merchant.components.applications.common;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FilterPanel {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SelenideElement header = $("h1[class=title]");
    private SelenideElement filterAppsField = $("input[placeholder^=Filter]");
    private SelenideElement searchButton = $x("//button[contains(text(), 'Search')]");
    private SelenideElement cardsViewTrigger = $x("//span[contains(text(),'Cards')]");
    private SelenideElement gridViewTrigger = $x("//span[contains(text(),'Grid')]");

    @Step("Enter into search field")
    public void enterSearch(String text) {
        logger.info("Enter into search field: " + text);
        filterAppsField.should(exist).sendKeys(text);
    }

    @Step("Click 'Search' button")
    public void clickSearch() {
        logger.info("Click 'Search' button");
        searchButton.should(exist).click();
    }

    @Step("Switch to 'Card' view")
    public void clickCardsView() {
        logger.info("Switch to 'Card' view");
        cardsViewTrigger.should(exist).click();
    }

    @Step("Switch to 'Grid' view")
    public void clickGridView() {
        logger.info("Switch to 'Grid' view");
        gridViewTrigger.should(exist).click();
    }

    @Step("Get header")
    public SelenideElement getHeader() {
        logger.info("Get header");
        return header;
    }
}
