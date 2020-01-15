package com.verifone.tests.mpAngularTests;

import com.verifone.infra.User;
import com.verifone.pages.mpAngularPages.pages.HomePage;
import com.verifone.pages.mpAngularPages.pages.LoginPage;
import com.verifone.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;

public class LoginPageTest extends BaseTest {

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
        loginPage.navigate();
    }

    @Test(priority = 1, testName = "User be able login with correct credentials", groups = {"ui", "regression"})
    @Epic("")
    @Feature("")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    @Description("User be able login with correct credentials")
    public void successfulLoginUI() {
        loginPage.doLogin(merchant);

        homePage.mainMenu.getLogo().shouldBe(visible);
    }

    @Test(priority = 1, testName = "User won't be able logged in with empty credentials", groups = {"ui", "regression"})
    @Epic("")
    @Feature("")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    @Description("User won't be able logged in with empty credentials")
    public void unsuccessfulLoginWithEmptyCredentialsUI() {
        loginPage.clickLogin();

        loginPage.getUsernameFieldErrorMessage().shouldBe(visible).shouldHave(text("This field is required."));
        loginPage.getPasswordFieldErrorMessage().shouldBe(visible).shouldHave(text("This field is required."));
    }

    @Test(priority = 1, testName = "User be able to see 'This field is required.' error message after logging with empty username field", groups = {"ui", "regression"})
    @Epic("")
    @Feature("")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    @Description("User be able to see 'This field is required.' error message after logging with empty username field")
    public void unsuccessfulLoginWithEmptyUsernameFieldUI() {
        loginPage.enterPassword(merchant.getPassword());
        loginPage.clickLogin();

        loginPage.getUsernameFieldErrorMessage().shouldBe(visible).shouldHave(text("This field is required."));
    }

    @Test(priority = 1, testName = "User be able to see 'This field is required.' error message after logging with empty password field", groups = {"ui", "regression"})
    @Epic("")
    @Feature("")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    @Description("User be able to see 'This field is required.' error message after logging with empty password field")
    public void unsuccessfulLoginWithEmptyPasswordFieldUI() {
        loginPage.enterUsername(merchant.getUserName());
        loginPage.clickLogin();

        loginPage.getPasswordFieldErrorMessage().shouldBe(visible).shouldHave(text("This field is required."));
    }

    @Test(priority = 1, testName = "User be able to see 'Email has incorrect format. You can only use letters, numbers and symbols.' error message after logging with incorrect username format", groups = {"ui", "regression"})
    @Epic("")
    @Feature("")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    @Description("User be able to see 'Email has incorrect format. You can only use letters, numbers and symbols.' " +
            "error message after logging with incorrect username format")
    public void unsuccessfulLoginWithIncorrectUsernameFormatUI() {
        loginPage.enterUsername("merchant");
        loginPage.clickLogin();

        loginPage.getUsernameFieldErrorMessage().shouldBe(visible).shouldHave(text("Email has incorrect format. You can only use letters, numbers and symbols."));
    }

    @Test(priority = 1, testName = "User be able to see title, email field, password field, forgot password link and login button on Login page", groups = {"ui", "regression"})    @Epic("")
    @Feature("")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    @Description("User be able to see title, email field, password field, forgot password link and login button on Login page")
    public void presenceOfElementsUI() {
        loginPage.getLogoImage().shouldBe(visible);
        loginPage.getLoginTitle().shouldBe(visible).shouldHave(text("Login to your\nVerifone Account"));
        loginPage.getUsernameField().shouldBe(visible).parent().shouldHave(text("Email Address"));
        loginPage.getPasswordField().shouldBe(visible).parent().parent().shouldHave(matchesText("Password"));
        loginPage.getForgotPasswordLink().shouldBe(visible).shouldHave(text("Forgot Password?"));
        loginPage.getLoginButton().shouldBe(visible).shouldHave(text("LOG IN"));
    }

    @Test(priority = 1, testName = "User be able to Hide/show password", groups = {"ui", "regression"})
    @Epic("")
    @Feature("")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    @Description("User be able to Hide/show password")
    public void hideShowPasswordUI() {
        loginPage.getPasswordField().shouldBe(visible).shouldHave(attribute("type", "password"));
        loginPage.clickHidePassword();
        loginPage.getPasswordField().shouldBe(visible).shouldHave(attribute("type", "text"));
    }
}
