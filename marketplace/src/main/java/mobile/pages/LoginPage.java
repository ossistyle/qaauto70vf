package mobile.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.verifone.infra.User;
import io.qameta.allure.Step;
import mobile.MobilePage;

import static com.codeborne.selenide.Condition.exist;
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

    @Step("Get password field")
    public SelenideElement getPasswordField() {
        logger.info("Get password field");
        if (!passwordIFrame.exists())
            WebDriverRunner.getAndCheckWebDriver().switchTo().defaultContent();
        WebDriverRunner.getAndCheckWebDriver().switchTo().frame(passwordIFrame);
        return passwordField;
    }

    @Step("Get forgot password link")
    public SelenideElement getForgotPasswordLink() {
        logger.info("Get forgot password link");
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
        logger.info("Enter password: " + password);
        getPasswordField().sendKeys(password);
        WebDriverRunner.getWebDriver().switchTo().defaultContent();
    }

    @Step("Click 'Hide password' icon")
    public void clickHidePassword() {
        logger.info("Click 'Hide password' icon");
        hidePasswordIcon.should(exist).click();
    }

    @Step("Click 'Forgot password' icon")
    public void clickForgotPassword() {
        logger.info("Click 'Forgot password' icon");
        forgotPasswordLink.should(exist).click();
    }

    @Step("Click 'Log In' button")
    public void clickLogin() {
        logger.info("Click 'Log In' button");
        loginButton.should(exist).click();
    }

    public void doLogin(User user) {
        enterUsername(user.getUserName());
        enterPassword(user.getPassword());
        clickLogin();
    }

    @Step("Get Username field error message")
    public SelenideElement getUsernameFieldErrorMessage() {
        logger.info("Get Username field error message");
        return usernameFieldErrorMessage;
    }

    @Step("Get Password field error message")
    public SelenideElement getPasswordFieldErrorMessage() {
        logger.info("Get Password field error message");
        return passwordFieldErrorMessage;
    }
}
