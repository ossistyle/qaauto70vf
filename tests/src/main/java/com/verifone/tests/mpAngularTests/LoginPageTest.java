package com.verifone.tests.mpAngularTests;

import com.verifone.infra.User;
import com.verifone.pages.mpAngularPages.pages.HomePage;
import com.verifone.pages.mpAngularPages.pages.LoginPage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Condition.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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

    @Test(priority = 1, testName = "Successful Login with correct credentials", groups = {"ui", "regression"})
    public void successfulLoginUI() {
        loginPage.doLogin(merchant);

        assertTrue(homePage.mainMenu.getLogo().isDisplayed(), "User logged in and Verifone logo displayed");
    }

    @Test(priority = 1, testName = "Unsuccessful login with empty credentials", groups = {"ui", "regression"})
    public void unsuccessfulLoginWithEmptyCredentialsUI() {
        loginPage.clickLogin();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(loginPage.getUsernameFieldErrorMessage().text(), "This field is required.", "Username field validation message");
        softAssert.assertEquals(loginPage.getPasswordFieldErrorMessage().text(), "This field is required.", "Password field validation message");
        softAssert.assertAll();
    }

    @Test(priority = 1, testName = "Unsuccessful login with empty Username field", groups = {"ui", "regression"})
    public void unsuccessfulLoginWithEmptyUsernameFieldUI() {
        loginPage.enterPassword(merchant.getPassword());
        loginPage.clickLogin();

        assertEquals(loginPage.getUsernameFieldErrorMessage().text(), "This field is required.", "Username field validation message");
    }

    @Test(priority = 1, testName = "Unsuccessful login with empty Password field", groups = {"ui", "regression"})
    public void unsuccessfulLoginWithEmptyPasswordFieldUI() {
        loginPage.enterUsername(merchant.getUserName());
        loginPage.clickLogin();

        assertEquals(loginPage.getPasswordFieldErrorMessage().text(), "This field is required.", "Password field validation message");
    }

    @Test(priority = 1, testName = "Unsuccessful login with incorrect Username format", groups = {"ui", "regression"})
    public void unsuccessfulLoginWithIncorrectUsernameFormatUI() {
        loginPage.enterUsername("merchant");
        loginPage.clickLogin();

        assertEquals(loginPage.getUsernameFieldErrorMessage().text(),
                "Email has incorrect format. You can only use letters, numbers and symbols.", "Username field validation message");
    }

    @Test(priority = 1, testName = "Presence of title, email field, password field, forgot password link and login button", groups = {"ui", "regression"})
    public void presenceOfElementsUI() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.getLogoImage().shouldBe(visible);
        softAssert.assertEquals(loginPage.getLoginTitle().text(), "Login to your\nVerifone Account", "Login title");
        softAssert.assertEquals(loginPage.getUsernameField().parent().text(), "Email Address", "Email field");
        softAssert.assertTrue(loginPage.getPasswordField().parent().parent().text().contains("Password"), "Password field text is 'Password'");
        softAssert.assertEquals(loginPage.getForgotPasswordLink().text(), "Forgot Password?", "Forgot password link");
        softAssert.assertEquals(loginPage.getLoginButton().text(), "LOG IN", "Login button");
        softAssert.assertAll();
    }

    @Test(priority = 1, testName = "Hide/show password", groups = {"ui", "regression"})
    public void hideShowPasswordUI() {
        assertEquals(loginPage.getPasswordField().attr("type"), "password", "Password field type");
        loginPage.clickHidePassword();
        assertEquals(loginPage.getPasswordField().attr("type"), "text", "Password field type");
    }
}
