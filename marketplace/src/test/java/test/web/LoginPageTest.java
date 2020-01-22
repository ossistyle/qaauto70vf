package test.web;

import com.verifone.infra.User;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.pages.HomePage;
import web.pages.LoginPage;

import static com.codeborne.selenide.Condition.*;

public class LoginPageTest extends BaseWebTest {

    private User merchant;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeClass (description = "Get test data")
    public void getTestData() {
        merchant = envConfig.getCredentials().getVFMPMer();
    }

    @BeforeMethod (description = "Navigate to Login page")
    public void beforeTest() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        loginPage.navigate();
    }

    @Test (description = "User be able login with correct credentials", groups = {"ui", "regression"})
    @Issue ("SGI-1179")
    @TmsLink ("8907133")
    @Epic ("")
    @Feature ("")
    @Severity (SeverityLevel.NORMAL)
    public void successfulLogin() {
        loginPage.doLogin(merchant);

        homePage.mainMenu.getLogo().shouldBe(visible);
    }

    @Test (description = "User won't be able logged in with empty credentials", groups = {"ui", "regression"})
    @Issue ("SGI-1179")
    @TmsLink ("8907133")
    @Epic ("")
    @Feature ("")
    @Severity (SeverityLevel.NORMAL)
    public void unsuccessfulLoginWithEmptyCredentials() {
        loginPage.clickLogin();

        loginPage.getUsernameFieldErrorMessage().shouldBe(visible).shouldHave(textCaseSensitive("This field is required."));
        loginPage.getPasswordFieldErrorMessage().shouldBe(visible).shouldHave(textCaseSensitive("This field is required."));
    }

    @Test (description = "User be able to see 'This field is required.' error message after logging with empty username field", groups = {"ui", "regression"})
    @Issue ("SGI-1179")
    @TmsLink ("8907133")
    @Epic ("")
    @Feature ("")
    @Severity (SeverityLevel.NORMAL)
    public void unsuccessfulLoginWithEmptyUsernameField() {
        loginPage.enterPassword(merchant.getPassword());
        loginPage.clickLogin();

        loginPage.getUsernameFieldErrorMessage().shouldBe(visible).shouldHave(textCaseSensitive("This field is required."));
    }

    @Test (description = "User be able to see 'This field is required.' error message after logging with empty password field", groups = {"ui", "regression"})
    @Issue ("SGI-1179")
    @TmsLink ("8907133")
    @Epic ("")
    @Feature ("")
    @Severity (SeverityLevel.NORMAL)
    public void unsuccessfulLoginWithEmptyPasswordFieldUI() {
        loginPage.enterUsername(merchant.getUserName());
        loginPage.clickLogin();

        loginPage.getPasswordFieldErrorMessage().shouldBe(visible).shouldHave(textCaseSensitive("This field is required."));
    }

    @Test (description = "User be able to see 'Email has incorrect format. You can only use letters, numbers and symbols.' error message after logging with incorrect username format", groups = {"ui", "regression"})
    @Issue ("SGI-1179")
    @TmsLink ("8907133")
    @Epic ("")
    @Feature ("")
    @Severity (SeverityLevel.NORMAL)
    public void unsuccessfulLoginWithIncorrectUsernameFormat() {
        loginPage.enterUsername("merchant");
        loginPage.clickLogin();

        loginPage.getUsernameFieldErrorMessage()
                .shouldBe(visible)
                .shouldHave(textCaseSensitive("Email has incorrect format. You can only use letters, numbers and symbols."));
    }

    @Test (description = "User be able to see title, email field, password field, forgot password link and login button on Login page", groups = {"ui", "regression"})
    @Epic ("")
    @Issue ("SGI-1179")
    @TmsLink ("8907133")
    @Feature ("")
    @Severity (SeverityLevel.NORMAL)
    public void presenceOfElementsUI() {
        loginPage.getLogoImage().shouldBe(visible);
        loginPage.getLoginTitle().shouldBe(visible).shouldHave(textCaseSensitive("Login to your\nVerifone Account"));
        loginPage.getUsernameField().shouldBe(visible).parent().shouldHave(textCaseSensitive("Email Address"));
        loginPage.getPasswordField().shouldBe(visible).parent().parent().shouldHave(matchesText("Password"));
        loginPage.getForgotPasswordLink().shouldBe(visible).shouldHave(textCaseSensitive("Forgot Password?"));
        loginPage.getLoginButton().shouldBe(visible).shouldHave(textCaseSensitive("LOG IN"));
    }

    @Test (description = "User be able to Hide/show password", groups = {"ui", "regression"})
    @Issue ("SGI-1179")
    @TmsLink ("8907133")
    @Epic ("")
    @Feature ("")
    @Severity (SeverityLevel.NORMAL)
    public void hideShowPassword() {
        loginPage.getPasswordField().shouldBe(visible).shouldHave(attribute("type", "password"));
        loginPage.clickHidePassword();
        loginPage.getPasswordField().shouldBe(visible).shouldHave(attribute("type", "text"));
    }
}
