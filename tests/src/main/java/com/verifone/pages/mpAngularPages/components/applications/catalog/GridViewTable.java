package com.verifone.pages.mpAngularPages.components.applications.catalog;

import com.aventstack.extentreports.ExtentTest;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class GridViewTable {

    private ExtentTest testLog;

    private ElementsCollection appNames = $$("td[data-label*=Name]");
    private ElementsCollection appDescriptions = $$("td[data-label*=Desc]");
    private ElementsCollection appVersions = $$("td[data-label*=Ver]");

    public SelenideElement getAppName(int index) {
        testLog.info(String.format("Get app name for app with %d index", index));
        return appNames.get(index).should(exist);
    }

    public SelenideElement getAppDescription(int index) {
        testLog.info(String.format("Get app description for app with %d index", index));
        return appDescriptions.get(index).should(exist);
    }

    public SelenideElement getAppVersion(int index) {
        testLog.info(String.format("Get app version for app with %d index", index));
        return appVersions.get(index).should(exist);
    }

    public GridViewTable() {
        this.testLog = BasePage.testLog;
    }
}
