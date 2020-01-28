package test.mobile;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import mobile.pages.HomePage;
import mobile.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.AssertionMode.SOFT;
import static com.codeborne.selenide.Condition.*;

public class LoginPageTest extends BaseMobileTest {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void preconditions() {
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    @Test (description = "User be able login with correct credentials", groups = {"ui", "regression"})
    @TmsLink("")
    @Feature("Login")
    @Severity(SeverityLevel.NORMAL)
    public void successfulLogin() {
        loginPage.doLogin(testData.getString("MerchantEmail"), testData.getString("MerchantPassword"));

        homePage.header.getTitle().should(exist).shouldBe(visible).shouldHave(exactText("Home"));
    }

    @Test (description = "User won't be able logged in with empty credentials", groups = {"ui", "regression"})
    @Issue ("")
    @TmsLink ("")
    @Feature("Login")
    @Severity (SeverityLevel.NORMAL)
    public void unsuccessfulLoginWithEmptyCredentials() {
        loginPage.clickLogin();

        loginPage.getUsernameFieldErrorMessage().shouldBe(visible).shouldHave(exactText("This field is required."));
        loginPage.getPasswordFieldErrorMessage().shouldBe(visible).shouldHave(exactText("This field is required."));
    }

    @Test (description = "User be able to see 'This field is required.' error message after logging with empty username field", groups = {"ui", "regression"})
    @Issue ("")
    @TmsLink ("")
    @Epic ("")
    @Feature("Login")
    @Severity (SeverityLevel.NORMAL)
    public void unsuccessfulLoginWithEmptyUsernameField() {
        loginPage.enterPassword(testData.getString("MerchantPassword"));
        loginPage.clickLogin();

        Configuration.assertionMode = SOFT;
        loginPage.getUsernameFieldErrorMessage().shouldBe(visible).shouldHave(exactText("This field is required."));
        loginPage.getPasswordFieldErrorMessage().shouldNot(exist);
    }

    @Test (description = "User be able to see 'This field is required.' error message after logging with empty password field", groups = {"ui", "regression"})
    @Issue ("")
    @TmsLink ("")
    @Feature("Login")
    @Severity (SeverityLevel.NORMAL)
    public void unsuccessfulLoginWithEmptyPasswordField() {
        loginPage.enterUsername(testData.getString("MerchantEmail"));
        loginPage.clickLogin();

        Configuration.assertionMode = SOFT;
        loginPage.getUsernameFieldErrorMessage().shouldNot(exist);
        loginPage.getPasswordFieldErrorMessage().shouldBe(visible).shouldHave(exactText("This field is required."));
    }

    @Test (description = "User be able to see 'Email has incorrect format. You can only use letters, numbers and symbols.' error message after logging with incorrect username format", groups = {"ui", "regression"})
    @Issue ("")
    @TmsLink ("")
    @Feature("Login")
    @Severity (SeverityLevel.NORMAL)
    public void unsuccessfulLoginWithIncorrectUsernameFormat() {
        loginPage.enterUsername("merchant");
        loginPage.clickLogin();

        loginPage.getUsernameFieldErrorMessage().shouldBe(visible)
                .shouldHave(exactText("Email has incorrect format. You can only use letters, numbers and symbols."));
    }

    @Test (description = "User be able to see title, email field, password field, forgot password link and login button on Login page", groups = {"ui", "regression"})
    @Epic ("")
    @Issue ("")
    @TmsLink ("")
    @Feature ("Login")
    @Severity (SeverityLevel.NORMAL)
    public void presenceOfElements() {
        Configuration.assertionMode = SOFT;
        loginPage.getLogoImage().shouldBe(visible);
        loginPage.getLoginTitle().shouldBe(visible).shouldHave(exactText("Login to your\nVerifone Account"));
        loginPage.getUsernameField().parent().shouldBe(visible).shouldHave(exactText("Email Address"));
        loginPage.getPasswordField().parent().parent().shouldBe(visible).shouldHave(matchesText("Password"));
        loginPage.getForgotPasswordLink().shouldBe(visible).shouldHave(exactText("Forgot Password?"));
        loginPage.getLoginButton().shouldBe(visible).shouldHave(exactText("LOG IN"));
    }

    @Test (description = "User be able to Hide/show password", groups = {"ui", "regression"}, testName = "Hide/show password")
    @Issue ("")
    @TmsLink ("")
    @Feature ("Login")
    @Severity (SeverityLevel.NORMAL)
    public void hideShowPassword() {
        loginPage.getPasswordField().should(exist).shouldHave(attribute("type", "password"));
        loginPage.clickHidePassword();
        loginPage.getPasswordField().should(exist).shouldHave(attribute("type", "text"));
    }
}

