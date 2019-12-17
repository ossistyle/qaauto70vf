package com.verifone.pages.selenide.pages;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends Page {

    private SelenideElement loginField = $(byId("username"));
    private SelenideElement passwordField = $(byId("ipassword"));
    private SelenideElement passwordIFrame = $(byId("veriPassFrame"));
    private SelenideElement loginButton = $(byId("btnPrimaryLogin"));

    public void enterLogin(String login) {
        loginField.should(exist).sendKeys(login);
    }

    public void enterPassword(String password) {
        WebDriverRunner.getWebDriver().switchTo().frame(passwordIFrame);
        passwordField.should(exist).sendKeys(password);
        WebDriverRunner.getWebDriver().switchTo().defaultContent();
    }

    public void clickLogin() {
        loginButton.should(exist).click();
    }

}
