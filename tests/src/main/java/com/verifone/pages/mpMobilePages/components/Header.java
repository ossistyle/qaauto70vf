package com.verifone.pages.mpMobilePages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Header {

    private SelenideElement title = $(byCssSelector("ion-title"));
    private SelenideElement menuButton = $(byCssSelector("ion-menu-toggle"));

    @Step("Get title")
    public SelenideElement getTitle() {
        return title;
    }

    @Step("Click 'Menu' button")
    public void clickMenuButton() {
        menuButton.should(exist).click();
    }
}
