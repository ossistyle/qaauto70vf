package com.verifone.pages.mpMobilePages.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.verifone.infra.User;
import com.verifone.pages.mpMobilePages.MobilePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends MobilePage {

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

    public LoginPage() {
        super();
    }

    public SelenideElement getLogoImage() {
        testLog.info("Get logo image");
        return logoImage.should(exist);
    }

    public SelenideElement getLoginTitle() {
        testLog.info("Get login title");
        return loginTitle.should(exist);
    }

    public SelenideElement getUsernameField() {
        testLog.info("Get username field");
        return usernameField.should(exist);
    }

    public SelenideElement getPasswordField() {
        testLog.info("Get password field");
        if (!passwordIFrame.exists())
            WebDriverRunner.getAndCheckWebDriver().switchTo().defaultContent();
        WebDriverRunner.getAndCheckWebDriver().switchTo().frame(passwordIFrame);
        return passwordField.should(exist);
    }

    public SelenideElement getForgotPasswordLink() {
        testLog.info("Get forgot password link");
        WebDriverRunner.getAndCheckWebDriver().switchTo().defaultContent();
        return forgotPasswordLink.should(exist);
    }

    public SelenideElement getLoginButton() {
        testLog.info("Get login button");
        return loginButton.should(exist);
    }

    public void enterUsername(String username) {
        testLog.info(String.format("Enter %s into 'Email address' field", username));
        usernameField.should(exist).sendKeys(username);
    }

    public void enterPassword(String password) {
        testLog.info(String.format("Enter %s into 'Password' field", password));
        getPasswordField().sendKeys(password);
        WebDriverRunner.getWebDriver().switchTo().defaultContent();
    }

    public void clickHidePassword() {
        testLog.info("Click 'Hide password' icon");
        hidePasswordIcon.should(exist).click();
    }

    public void clickForgotPassword() {
        testLog.info("Click 'Forgot password' icon");
        forgotPasswordLink.should(exist).click();
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
        return usernameFieldErrorMessage.shouldBe(visible);
    }

    public SelenideElement getPasswordFieldErrorMessage() {
        testLog.info("Get Password field error message");
        return passwordFieldErrorMessage.shouldBe(visible);
    }
}
