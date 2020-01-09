package com.verifone.tests.mpAngularTests;

import com.verifone.infra.User;
import com.verifone.pages.mpAngularPages.pages.HomePage;
import com.verifone.pages.mpAngularPages.pages.LoginPage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    @Test(priority=1, testName = "Successful Login with correct credentials", groups = {"ui", "regression"})
    public void successfulLoginUI() {
        loginPage.doLogin(merchant);

        assertTrue(homePage.mainMenu.getLogo().isDisplayed(), "User logged in and Verifone logo displayed");
    }
}
