package com.verifone.pages.mpWebPages.pages;

import com.codeborne.selenide.*;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {

    private final static String URL = "";
    private final static String TITLE = "Login with Verifone Identity Server";

    private SelenideElement logoImage = $("div[class*=logo]");
    private SelenideElement loginTitle = $("h1[class*=verifone]");
    private SelenideElement usernameField = $("#username");
    private SelenideElement usernameFieldErrorMessage = $x("//*[@id='username']/following-sibling::div[@class='help-block']");
    private SelenideElement passwordField = $("#ipassword");
    private SelenideElement passwordIFrame = $("#veriPassFrame");
    private SelenideElement passwordFieldErrorMessage = $("#password_id .help-block");
    private SelenideElement hidePasswordIcon = $x("(//i[contains(text(), 'visibility')])[last()]");
    private SelenideElement forgotPasswordLink = $("a[href*=Recover]");
    private SelenideElement loginButton = $("#btnPrimaryLogin");

    @Step("Get logo image")
    public SelenideElement getLogoImage() {
        return logoImage;
    }

    @Step("Get login title")
    public SelenideElement getLoginTitle() {
        return loginTitle;
    }

    @Step("Get username field")
    public SelenideElement getUsernameField() {
        return usernameField;
    }

    @Step("Switch to password field iframe")
    public SelenideElement getPasswordField() {
        if (!passwordIFrame.exists()) WebDriverRunner.getAndCheckWebDriver().switchTo().defaultContent();
        WebDriverRunner.getAndCheckWebDriver().switchTo().frame(passwordIFrame);
        return passwordField;
    }

    @Step("Get password link")
    public SelenideElement getForgotPasswordLink() {
        WebDriverRunner.getAndCheckWebDriver().switchTo().defaultContent();
        return forgotPasswordLink;
    }

    @Step("Get login button")
    public SelenideElement getLoginButton() {
        return loginButton;
    }

    @Step("Enter username")
    public void enterUsername(String username) {
        usernameField.should(exist).sendKeys(username);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        getPasswordField().should(exist).sendKeys(password);
        WebDriverRunner.getWebDriver().switchTo().defaultContent();
    }

    @Step("Click 'Hide password' button")
    public void clickHidePassword() {
        hidePasswordIcon.should(exist).click();
    }

    @Step("Click 'Forgot password' link")
    public void clickForgotPassword() {
        forgotPasswordLink.should(exist).click();
    }

    @Step("Click 'Login' button")
    public void clickLogin() {
        loginButton.should(exist).click();
    }

    public void doLogin(User user) {
        enterUsername(user.getUserName());
        enterPassword(user.getPassword());
        clickLogin();
    }

    @Step("Get username field error message")
    public SelenideElement getUsernameFieldErrorMessage() {
        return usernameFieldErrorMessage;
    }

    @Step("Get password field error message")
    public SelenideElement getPasswordFieldErrorMessage() {
        return passwordFieldErrorMessage;
    }

    public LoginPage() {
        super(URL, TITLE);
    }
}
