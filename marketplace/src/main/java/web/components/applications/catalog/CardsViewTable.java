package web.components.applications.catalog;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$$;

public class CardsViewTable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ElementsCollection appNames = $$("marketplace-app-card .card-header-title");
    private ElementsCollection moreButtons = $$("svg[data-icon='angle-down']");
    private ElementsCollection cardImages = $$(".card-image img");
    private ElementsCollection appDescriptions = $$(".card-content p");
    private ElementsCollection appPageButtons = $$("footer a:first-child");
    private ElementsCollection quickViewButtons = $$("footer a:last-child");

    @Step("Click 'App page' button for app with index")
    public void clickAppPageButton(int index) {
        logger.info(String.format("Click 'App page' button for app with %d index", index));
        appPageButtons.get(index).should(exist).click();
    }

    @Step("Click 'More' button for app with index")
    public void clickMoreButton(int index) {
        logger.info(String.format("Click 'More' button for app with %d index", index));
        moreButtons.get(index).should(exist).click();
    }

    @Step("Click 'Quick View' button for app with index")
    public void clickQuickViewButton(int index) {
        logger.info(String.format("Click 'Quick View' button for app with %d index", index));
        quickViewButtons.get(index).should(exist).click();
    }

    @Step("Get app names")
    public ElementsCollection getAppNames() {
        logger.info("Get app names");
        return appNames;
    }

    @Step("Get app images")
    public ElementsCollection getCardImages() {
        logger.info("Get app images");
        return cardImages;
    }

    @Step("Get app descriptions")
    public ElementsCollection getAppDescriptions() {
        logger.info("Get app descriptions");
        return appDescriptions;
    }
}
