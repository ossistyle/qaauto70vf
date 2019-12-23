package com.verifone.pages.mpAngularPages.pages;

import com.codeborne.selenide.*;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

    private final static String URL = "";
    private final static String TITLE = "Login with Verifone Identity Server";

    private SelenideElement loginField = $(byId("username"));
    private SelenideElement passwordField = $(byId("ipassword"));
    private SelenideElement passwordIFrame = $(byId("veriPassFrame"));
    private SelenideElement loginButton = $(byId("btnPrimaryLogin"));

    /**
     * Enter text into 'Email address' field
     * @param login Email address
     */
    public void enterLogin(String login) {
        loginField.should(exist).sendKeys(login);
    }

    /**
     * Enter text into 'Password' field
     * @param password Password
     */
    public void enterPassword(String password) {
        WebDriverRunner.getWebDriver().switchTo().frame(passwordIFrame);
        passwordField.should(exist).sendKeys(password);
        WebDriverRunner.getWebDriver().switchTo().defaultContent();
    }

    /**
     * Click 'Log In' button
     */
    public void clickLogin() {
        loginButton.should(exist).click();
    }

    /**
     * Perform log in into app
     * @param user User
     */
    public void doLogin(User user) {
        enterLogin(user.getUserName());
        enterPassword(user.getPassword());
        clickLogin();
    }

    public LoginPage() {
        super(URL, TITLE);
    }

}
