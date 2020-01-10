package com.verifone.tests.mpAngularTests;

import com.verifone.infra.User;
import com.verifone.pages.mpAngularPages.pages.HomePage;
import com.verifone.pages.mpAngularPages.pages.LoginPage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(loginPage.getUsernameFieldErrorMessage().text(), "This field is required.", "Username field validation message");
        softAssert.assertFalse(loginPage.getPasswordFieldErrorMessage().exists(), "Password field validation message is exists");
        softAssert.assertAll();
    }

    @Test(priority = 1, testName = "Unsuccessful login with empty Password field", groups = {"ui", "regression"})
    public void unsuccessfulLoginWithEmptyPasswordFieldUI() {
        loginPage.enterUsername(merchant.getUserName());
        loginPage.clickLogin();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(loginPage.getUsernameFieldErrorMessage().exists(), "Username field validation message is exists");
        softAssert.assertEquals(loginPage.getPasswordFieldErrorMessage().text(), "This field is required.", "Password field validation message");
        softAssert.assertAll();
    }

    @Test(priority = 1, testName = "Unsuccessful login with incorrect Username format", groups = {"ui", "regression"})
    public void unsuccessfulLoginWithIncorrectUsernameFormatUI() {
        loginPage.enterUsername("merchant");
        loginPage.clickLogin();

        assertEquals(loginPage.getUsernameFieldErrorMessage().text(),
                "Email has incorrect format. You can only use letters, numbers and symbols.", "Username field validation message");
    }
}
