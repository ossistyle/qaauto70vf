package com.verifone.pages.mpMobilePages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Header {

    private SelenideElement title = $(byCssSelector("ion-title"));
    private SelenideElement menuButton = $(byCssSelector("ion-menu-toggle"));

    /**
     * Get title
     * @return Title text
     */
    public String getTitle() {
        return title.getText();
    }

    /**
     * Click 'Menu' button
     */
    public void clickMenuButton() {
        menuButton.should(exist).click();
    }
}
