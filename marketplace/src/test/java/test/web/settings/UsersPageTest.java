package test.web.settings;

import com.verifone.infra.User;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.pages.HomePage;
import web.pages.LoginPage;
import web.pages.settings.UsersPage;
import test.web.BaseWebTest;
import utils.mock.UserMock;

import static com.codeborne.selenide.Condition.*;

public class UsersPageTest extends BaseWebTest {

    private User merchant;
    private UserMock userMock;
    private UsersPage usersPage;
    private int testUserIndex = 0;

    @BeforeClass (description = "Get test data")
    public void getTestData() {
        merchant = envConfig.getCredentials().getVFMPMer();
        userMock = new UserMock();
    }

    @BeforeMethod (description = "Login as merchant > Open users page")
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

    @Test (description = "User be able to see app user avatars, names, emails and roles on the Users page", groups = {"ui", "regression"}, testName = "User information")
    @Feature("")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    @Description("User be able to see app user avatars, names, emails and roles on the Users page")
    public void userInformationUI() {
        usersPage.getUserAvatars().get(testUserIndex).shouldBe(visible);
        usersPage.getUserNames().get(testUserIndex).should(exist).shouldHave(textCaseSensitive(userMock.getUsername()));
        usersPage.getUserEmails().get(testUserIndex).should(exist).shouldHave(textCaseSensitive(userMock.getEmail()));
        usersPage.getUserRoles().get(testUserIndex).should(exist).shouldHave(textCaseSensitive(userMock.getRole()));
    }
}
