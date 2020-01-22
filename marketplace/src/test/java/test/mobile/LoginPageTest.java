package test.mobile;

import com.verifone.infra.User;
import io.qameta.allure.*;
import mobile.pages.HomePage;
import mobile.pages.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;

public class LoginPageTest extends BaseMobileTest {

    private User merchant;

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeClass
    public void getTestData() {
        merchant = envConfig.getCredentials().getVFMPMer();
    }

    @BeforeMethod
    public void beforeTest() {
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    @Test (description = "User be able login with correct credentials", groups = {"ui", "regression"})
    @Issue("SGI-1179")
    @TmsLink("8907133")
    @Epic("")
    @Feature("")
    @Severity(SeverityLevel.NORMAL)
    public void successfulLoginMobile() {
        loginPage.doLogin(merchant);

        homePage.header.getTitle().should(exist).shouldBe(visible).shouldHave(textCaseSensitive("Home"));
    }

    @Test (description = "User won't be able logged in with empty credentials", groups = {"ui", "regression"})
    @Issue ("SGI-1179")
    @TmsLink ("8907133")
    @Epic ("")
    @Feature ("")
    @Severity (SeverityLevel.NORMAL)
    public void unsuccessfulLoginWithEmptyCredentialsMobile() {
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
    public void unsuccessfulLoginWithEmptyUsernameFieldMobile() {
        loginPage.enterPassword(merchant.getPassword());
        loginPage.clickLogin();

        loginPage.getUsernameFieldErrorMessage().shouldBe(visible).shouldHave(textCaseSensitive("This field is required."));
        loginPage.getPasswordFieldErrorMessage().shouldNot(exist);
    }

    @Test (description = "User be able to see 'This field is required.' error message after logging with empty password field", groups = {"ui", "regression"})
    @Issue ("SGI-1179")
    @TmsLink ("8907133")
    @Epic ("")
    @Feature ("")
    @Severity (SeverityLevel.NORMAL)
    public void unsuccessfulLoginWithEmptyPasswordFieldMobile() {
        loginPage.enterUsername(merchant.getUserName());
        loginPage.clickLogin();

        loginPage.getUsernameFieldErrorMessage().shouldNot(exist);
        loginPage.getPasswordFieldErrorMessage().shouldBe(visible).shouldHave(textCaseSensitive("This field is required."));
    }

    @Test (description = "User be able to see 'Email has incorrect format. You can only use letters, numbers and symbols.' error message after logging with incorrect username format", groups = {"ui", "regression"})
    @Issue ("SGI-1179")
    @TmsLink ("8907133")
    @Epic ("")
    @Feature ("")
    @Severity (SeverityLevel.NORMAL)
    public void unsuccessfulLoginWithIncorrectUsernameFormatMobile() {
        loginPage.enterUsername("merchant");
        loginPage.clickLogin();

        loginPage.getUsernameFieldErrorMessage().shouldBe(visible)
                .shouldHave(textCaseSensitive("Email has incorrect format. You can only use letters, numbers and symbols."));
    }

    @Test (description = "User be able to see title, email field, password field, forgot password link and login button on Login page", groups = {"ui", "regression"})
    @Epic ("")
    @Issue ("SGI-1179")
    @TmsLink ("8907133")
    @Feature ("")
    @Severity (SeverityLevel.NORMAL)
    public void presenceOfElementsMobile() {
        loginPage.getLogoImage().shouldBe(visible);
        loginPage.getLoginTitle().shouldBe(visible).shouldHave(textCaseSensitive("Login to your\nVerifone Account"));
        loginPage.getUsernameField().parent().shouldBe(visible).shouldHave(textCaseSensitive("Email Address"));
        loginPage.getPasswordField().parent().parent().shouldBe(visible).shouldHave(matchesText("Password"));
        loginPage.getForgotPasswordLink().shouldBe(visible).shouldHave(textCaseSensitive("Forgot Password?"));
        loginPage.getLoginButton().shouldBe(visible).shouldHave(textCaseSensitive("LOG IN"));
    }

    @Test (description = "User be able to Hide/show password", groups = {"ui", "regression"}, testName = "Hide/show password")
    @Issue ("SGI-1179")
    @TmsLink ("8907133")
    @Epic ("")
    @Feature ("")
    @Severity (SeverityLevel.NORMAL)
    public void hideShowPasswordMobile() {
        loginPage.getPasswordField().should(exist).shouldHave(attribute("type", "password"));
        loginPage.clickHidePassword();
        loginPage.getPasswordField().should(exist).shouldHave(attribute("type", "text"));
    }
}

