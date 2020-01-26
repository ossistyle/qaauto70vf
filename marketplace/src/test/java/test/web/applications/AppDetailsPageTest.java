package test.web.applications;

import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.merchant.pages.HomePage;
import web.LoginPage;
import web.merchant.pages.applications.AppCatalogPage;
import web.merchant.pages.applications.AppDetailsPage;
import test.web.BaseWebTest;
import utils.mock.AppMock;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.*;

public class AppDetailsPageTest extends BaseWebTest {

    // TODO Replace mock data to data from API
    private AppMock app;

    private AppDetailsPage appDetailsPage;

    @BeforeClass (description = "Get test data")
    public void getTestData() {
        app = new AppMock();
    }

    @BeforeMethod (description = "Login as merchant > Open app catalog > Click app page button")
    public void beforeTest() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();
        AppCatalogPage appCatalogPage = new AppCatalogPage();
        appDetailsPage = new AppDetailsPage();

        loginPage.navigate();
        loginPage.doLogin(testData.getString("MerchantEmail"), testData.getString("MerchantPassword"));
        // TODO Uncomment after adding route
//        appCatalogPage.navigate();
        homePage.mainMenu.clickApplications();
        homePage.mainMenu.clickAppCatalogue();
        appCatalogPage.cardsViewTable.clickAppPageButton(0);
    }

    @Test(description = "User be able to see app details: name, id, description on the App details page", groups = {"ui", "regression"})
    @Feature ("")
    @Link ("")
    @Severity (SeverityLevel.NORMAL)
    public void applicationDetails() {
        appDetailsPage.getAppName().should(exist).shouldHave(exactText(app.getName()));
        appDetailsPage.getAppId().should(exist).shouldHave(exactText(app.getMarketplaceAppId()));
        appDetailsPage.getAppDescription().should(exist).shouldHave(exactText(app.getDescription()));
    }
}
