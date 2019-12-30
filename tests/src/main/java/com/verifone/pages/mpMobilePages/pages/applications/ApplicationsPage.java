package com.verifone.pages.mpMobilePages.pages.applications;

import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.mpMobilePages.MobilePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ApplicationsPage extends MobilePage {

    private SelenideElement filterAppsField = $(byCssSelector("input[placeholder*=Filter]"));

    /**
     * Enter text into 'Filter Apps' field
     * @param text Text
     */
    public void enterFilterApps(String text) {
        testLog.info(String.format("Enter '%s' into 'Filter Apps' input field", text));
        filterAppsField.should(exist).sendKeys(text);
    }

    public ApplicationsPage() {
        super();
    }
}
