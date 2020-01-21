package mobile.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;

public class Header {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SelenideElement title = $(byCssSelector("ion-title"));
    private SelenideElement menuButton = $(byCssSelector("ion-menu-toggle"));

    @Step("Get title")
    public SelenideElement getTitle() {
        logger.info("Get title");
        return title;
    }

    @Step("Click 'Menu' button")
    public void clickMenuButton() {
        logger.info("Click 'Menu' button");
        menuButton.should(exist).click();
    }
}
