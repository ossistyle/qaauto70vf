package test.web.merchant.settings;

import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.merchant.pages.HomePage;
import web.LoginPage;
import web.merchant.pages.settings.UsersPage;
import test.web.BaseWebTest;
import utils.mock.UserMock;

import static com.codeborne.selenide.Condition.*;

public class UsersPageTest extends BaseWebTest {

    private UserMock userMock;
    private UsersPage usersPage;
    private int testUserIndex = 0;

    @BeforeClass (description = "Get test data")
    public void getTestData() {
        userMock = new UserMock();
    }

    @BeforeMethod (description = "Login as merchant > Open users page")
    public void beforeTest() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();
        usersPage = new UsersPage();

        loginPage.navigate();
        loginPage.doLogin(testData.getString("MerchantEmail"), testData.getString("MerchantPassword"));
        // TODO Uncomment after adding route
//        usersPage.navigate();
        homePage.mainMenu.clickSettings();
        homePage.mainMenu.clickUsers();
    }

    @Test (description = "User be able to see app user avatars, names, emails and roles on the Users page", groups = {"ui", "regression"})
    @Feature("")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    public void userInformation() {
        usersPage.getUserAvatars().get(testUserIndex).shouldBe(visible);
        usersPage.getUserNames().get(testUserIndex).should(exist).shouldHave(exactText(userMock.getUsername()));
        usersPage.getUserEmails().get(testUserIndex).should(exist).shouldHave(exactText(userMock.getEmail()));
        usersPage.getUserRoles().get(testUserIndex).should(exist).shouldHave(exactText(userMock.getRole()));
    }
}
