package com.verifone.tests.selenide.utils;

import com.codeborne.selenide.SelenideElement;

public abstract class Element {

    /**
     * Perform click to element
     */
    public static void click(SelenideElement el) {
        el.click();
    }

}
