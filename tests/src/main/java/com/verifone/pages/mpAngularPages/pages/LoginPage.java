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

    private SelenideElement logoImage = $(byCssSelector("div[class*=logo]"));
    private SelenideElement loginTitle = $(byCssSelector("h1[class*=verifone]"));
    private SelenideElement usernameField = $(byId("username"));
    private SelenideElement usernameFieldErrorMessage = $(byXpath("//*[@id='username']/following-sibling::div[@class='help-block']"));
    private SelenideElement passwordField = $(byId("ipassword"));
    private SelenideElement passwordIFrame = $(byId("veriPassFrame"));
    private SelenideElement passwordFieldErrorMessage = $(byCssSelector("#password_id .help-block"));
    private SelenideElement hidePasswordIcon = $(byXpath("(//i[contains(text(), 'visibility')])[last()]"));
    private SelenideElement forgotPasswordLink = $(byCssSelector("a[href*=Recover]"));
    private SelenideElement loginButton = $(byId("btnPrimaryLogin"));

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

    public LoginPage() {
        super(URL, TITLE);
    }
}
