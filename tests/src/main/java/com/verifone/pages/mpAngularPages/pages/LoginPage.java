package com.verifone.pages.mpAngularPages.pages;

import com.codeborne.selenide.*;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;

import javax.validation.constraints.NotNull;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

    private final static String URL = "";
    private final static String TITLE = "Login with Verifone Identity Server";

    private SelenideElement usernameField = $(byId("username"));
    private SelenideElement usernameFieldErrorMessage = $(byXpath("//*[@id='username']/following-sibling::div[@class='help-block']"));
    private SelenideElement passwordField = $(byId("ipassword"));
    private SelenideElement passwordIFrame = $(byId("veriPassFrame"));
    private SelenideElement passwordFieldErrorMessage = $(byCssSelector("#password_id .help-block"));
    private SelenideElement loginButton = $(byId("btnPrimaryLogin"));

    public void enterUsername(String username) {
        testLog.info(String.format("Enter %s into 'Email address' field", username));
        usernameField.should(exist).sendKeys(username);
    }

    public void enterPassword(String password) {
        testLog.info(String.format("Enter %s into 'Password' field", password));
        WebDriverRunner.getWebDriver().switchTo().frame(passwordIFrame);
        passwordField.should(exist).sendKeys(password);
        WebDriverRunner.getWebDriver().switchTo().defaultContent();
    }

    public void clickLogin() {
        testLog.info("Click 'Log In' button");
        loginButton.should(exist).click();
    }

    public void doLogin(User user) {
        enterUsername(user.getUserName());
        enterPassword(user.getPassword());
        clickLogin();
    }

    public SelenideElement getUsernameFieldErrorMessage() {
        testLog.info("Get Username field error message");
        return usernameFieldErrorMessage;
    }

    public SelenideElement getPasswordFieldErrorMessage() {
        testLog.info("Get Password field error message");
        return passwordFieldErrorMessage;
    }

    public LoginPage() {
        super(URL, TITLE);
    }
}
