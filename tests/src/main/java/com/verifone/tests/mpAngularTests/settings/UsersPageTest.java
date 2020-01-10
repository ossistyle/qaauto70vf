package com.verifone.tests.mpAngularTests.settings;

import com.verifone.infra.User;
import com.verifone.pages.mpAngularPages.pages.HomePage;
import com.verifone.pages.mpAngularPages.pages.LoginPage;
import com.verifone.pages.mpAngularPages.pages.settings.UsersPage;
import com.verifone.tests.BaseTest;
import mock.UserMock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Condition.*;

public class UsersPageTest extends BaseTest {

    private User merchant;
    private UserMock userMock;
    private UsersPage usersPage;
    private int testUserIndex = 0;

    @BeforeClass
    public void getTestData() {
        merchant = envConfig.getCredentials().getVFMPMer();
        userMock = new UserMock();
    }

    @BeforeMethod
    public void beforeTest() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();
        usersPage = new UsersPage();

        loginPage.navigate();
        loginPage.doLogin(merchant);
        // TODO Uncomment after adding route
//        usersPage.navigate();
        homePage.mainMenu.clickSettings();
        homePage.mainMenu.clickUsers();
    }

    @Test(priority = 1, testName = "User information", groups = {"ui", "regression"})
    public void userInformationUI() {
        SoftAssert softAssert = new SoftAssert();
        usersPage.getUserAvatar(testUserIndex).shouldBe(visible);
        softAssert.assertEquals(usersPage.getUserName(testUserIndex).text(), userMock.getUsername(), "User name");
        softAssert.assertEquals(usersPage.getUserEmail(testUserIndex).text(), userMock.getEmail(), "User email");
        softAssert.assertEquals(usersPage.getUserRole(testUserIndex).text(), userMock.getRole(), "User role");
        softAssert.assertAll();
    }
}
