package web.merchant.components.applications.catalog;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$$;

public class GridViewTable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ElementsCollection appNames = $$("td[data-label*=Name]");
    private ElementsCollection appDescriptions = $$("td[data-label*=Desc]");
    private ElementsCollection appVersions = $$("td[data-label*=Ver]");

    @Step("Get app names")
    public ElementsCollection getAppNames() {
        logger.info("Get app names");
        return appNames;
    }

    @Step("Get app descriptions")
    public ElementsCollection getAppDescriptions() {
        logger.info("Get app descriptions");
        return appDescriptions;
    }

    @Step("Get app versions")
    public ElementsCollection getAppVersions() {
        logger.info("Get app versions");
        return appVersions;
    }
}
