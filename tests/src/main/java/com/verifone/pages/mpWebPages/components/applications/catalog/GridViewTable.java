package com.verifone.pages.mpWebPages.components.applications.catalog;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$;

public class GridViewTable {

    private ElementsCollection appNames = $$("td[data-label*=Name]");
    private ElementsCollection appDescriptions = $$("td[data-label*=Desc]");
    private ElementsCollection appVersions = $$("td[data-label*=Ver]");

    @Step("Get app names")
    public ElementsCollection getAppNames() {
        return appNames;
    }

    @Step("Get app descriptions")
    public ElementsCollection getAppDescriptions() {
        return appDescriptions;
    }

    @Step("Get app versions")
    public ElementsCollection getAppVersions() {
        return appVersions;
    }
}
