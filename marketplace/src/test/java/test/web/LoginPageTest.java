package test.web;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.merchant.pages.HomePage;
import web.LoginPage;

import static com.codeborne.selenide.AssertionMode.SOFT;
import static com.codeborne.selenide.Condition.*;

public class LoginPageTest extends BaseWebTest {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod (description = "Navigate to Login page")
    public void beforeTest() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        loginPage.navigate();
    }

    @Test (description = "User be able login with correct credentials", groups = {"ui", "regression"})
    @TmsLink ("")
    @Feature ("Login")
    @Severity (SeverityLevel.NORMAL)
    public void successfulLogin() {
        loginPage.doLogin(testData.getString("MerchantEmail"), testData.getString("MerchantPassword"));

        homePage.mainMenu.getLogo().shouldBe(visible.because("user should be successfully logged in"));
    }

    @Test (description = "User won't be able logged in with empty credentials", groups = {"ui", "regression"})
    @TmsLink ("10480100")
    @Feature ("Login")
    @Severity (SeverityLevel.NORMAL)
    public void unsuccessfulLoginWithEmptyCredentials() {
        loginPage.clickLogin();

        Configuration.assertionMode = SOFT;
        loginPage.getUsernameFieldErrorMessage()
                .shouldBe(visible.because("user be able to see an error message near email field"))
                .shouldHave(exactText("This field is required.").because("email error message text should be 'This field is required.'"));

        loginPage.getPasswordFieldErrorMessage()
                .shouldBe(visible.because("user be able to see an error message near password field"))
                .shouldHave(exactText("This field is required.").because("password error message text should be 'This field is required.'"));
    }

    @Test (description = "User be able to see 'This field is required.' error message after logging with empty username field", groups = {"ui", "regression"})
    @TmsLink ("10480100")
    @Feature ("Login")
    @Severity (SeverityLevel.NORMAL)
    public void unsuccessfulLoginWithEmptyUsernameField() {
        loginPage.enterPassword(testData.getString("MerchantPassword"));
        loginPage.clickLogin();

        loginPage.getUsernameFieldErrorMessage()
                .shouldBe(visible.because("user be able to see an error message near email field"))
                .shouldHave(exactText("This field is required.").because("email error message text should be 'This field is required.'"));
    }

    @Test (priority = 1, description = "User be able to see 'This field is required.' error message after logging with empty password field", groups = {"ui", "regression"})
    @TmsLink ("10480100")
    @Feature ("Login")
    @Severity (SeverityLevel.NORMAL)
    public void unsuccessfulLoginWithEmptyPasswordFieldUI() {
        loginPage.enterUsername(testData.getString("MerchantEmail"));
        loginPage.clickLogin();

        loginPage.getPasswordFieldErrorMessage()
                .shouldBe(visible.because("user be able to see an error message near password field"))
                .shouldHave(exactText("This field is required.").because("password error message text should be 'This field is required.'"));
    }

    @Test (description = "User be able to see 'Email has incorrect format. You can only use letters, numbers and symbols.' error message after logging with incorrect username format", groups = {"ui", "regression"})
    @TmsLink ("10480106")
    @Feature ("Login")
    @Severity (SeverityLevel.NORMAL)
    public void unsuccessfulLoginWithIncorrectUsernameFormat() {
        loginPage.enterUsername("merchant");
        loginPage.clickLogin();

        loginPage.getUsernameFieldErrorMessage()
                .shouldBe(visible.because("user be able to see an error message"))
                .shouldHave(exactText("Email has incorrect format. You can only use letters, numbers and symbols.")
                        .because("email error message text should be 'Email has incorrect format. You can only use letters, numbers and symbols.'"));
    }

    @Test (description = "User be able to see title, email field, password field, forgot password link and login button on Login page", groups = {"ui", "regression"})
    @TmsLink ("10490602")
    @Feature ("Login")
    @Severity (SeverityLevel.NORMAL)
    public void presenceOfElements() {
        Configuration.assertionMode = SOFT;
        loginPage.getLogoImage().shouldBe(visible.because("user be able to see Verifone logo"));

        loginPage.getLoginTitle()
                .shouldBe(visible.because("user be able to see page title"))
                .shouldHave(exactText("Login to your\nVerifone Account").because("title text should be Login to your Verifone Account"));

        loginPage.getUsernameField()
                .shouldBe(visible.because("user be able to see email input field")).parent()
                .shouldHave(exactText("Email Address").because("email input field text should be Email Address"));

        loginPage.getPasswordField()
                .shouldBe(visible.because("user be able to see password field")).parent().parent()
                .shouldHave(matchesText("Password").because("password input field text should be Password"));

        loginPage.getForgotPasswordLink()
                .shouldBe(visible.because("user be able to see password input field"))
                .shouldHave(exactText("Forgot Password?").because("password input field text should be Forgot Password?"));

        loginPage.getLoginButton()
                .shouldBe(visible.because("user be able to see LOG IN button"))
                .shouldHave(exactText("LOG IN").because("button text should be LOG IN"));
    }

    @Test (description = "User be able to Hide/show password", groups = {"ui", "regression"})
    @TmsLink ("10480113")
    @Feature ("Login")
    @Severity (SeverityLevel.NORMAL)
    public void hideShowPassword() {
        loginPage.getPasswordField().shouldBe(visible)
                .shouldHave(attribute("type", "password")
                        .because("user be able to see password as ******"));

        loginPage.clickHidePassword();

        loginPage.getPasswordField().shouldBe(visible)
                .shouldHave(attribute("type", "text")
                        .because("password should be visible for user"));
    }
}
