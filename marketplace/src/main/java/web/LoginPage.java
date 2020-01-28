package web;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BaseWebPage {

    private String url = "";

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
        logger.info("Get logo image");
        return logoImage;
    }

    @Step("Get login title")
    public SelenideElement getLoginTitle() {
        logger.info("Get login title");
        return loginTitle;
    }

    @Step("Get username field")
    public SelenideElement getUsernameField() {
        logger.info("Get username field");
        return usernameField;
    }

    @Step("Switch to password field iframe")
    public SelenideElement getPasswordField() {
        logger.info("Switch to password field iframe");
        if (!passwordIFrame.exists()) WebDriverRunner.getAndCheckWebDriver().switchTo().defaultContent();
        WebDriverRunner.getAndCheckWebDriver().switchTo().frame(passwordIFrame);
        return passwordField;
    }

    @Step("Get password link")
    public SelenideElement getForgotPasswordLink() {
        logger.info("Get password link");
        WebDriverRunner.getAndCheckWebDriver().switchTo().defaultContent();
        return forgotPasswordLink;
    }

    @Step("Get login button")
    public SelenideElement getLoginButton() {
        logger.info("Get login button");
        return loginButton;
    }

    @Step("Enter username")
    public void enterUsername(String username) {
        logger.info("Enter username: " + username);
        usernameField.should(exist).sendKeys(username);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        getPasswordField().should(exist).sendKeys(password);
        logger.info("Enter password: " + password);
        WebDriverRunner.getWebDriver().switchTo().defaultContent();
    }

    @Step("Click 'Hide password' button")
    public void clickHidePassword() {
        logger.info("Click 'Hide password' button");
        hidePasswordIcon.should(exist).click();
    }

    @Step("Click 'Forgot password' link")
    public void clickForgotPassword() {
        logger.info("Click 'Forgot password' link");
        forgotPasswordLink.should(exist).click();
    }

    @Step("Click 'Login' button")
    public void clickLogin() {
        logger.info("Click 'Login' button");
        loginButton.should(exist).click();
    }

    public void doLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    @Step("Get username field error message")
    public SelenideElement getUsernameFieldErrorMessage() {
        logger.info("Get username field error message");
        return usernameFieldErrorMessage;
    }

    @Step("Get password field error message")
    public SelenideElement getPasswordFieldErrorMessage() {
        logger.info("Get password field error message");
        return passwordFieldErrorMessage;
    }

    public LoginPage() {
        super();
        super.url = this.url;
    }
}
