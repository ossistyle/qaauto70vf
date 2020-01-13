package com.verifone.tests.mpAngularTests.applications;

import com.verifone.infra.User;
import com.verifone.pages.mpAngularPages.pages.HomePage;
import com.verifone.pages.mpAngularPages.pages.LoginPage;
import com.verifone.pages.mpAngularPages.pages.applications.AppCatalogPage;
import com.verifone.pages.mpAngularPages.pages.applications.AppDetailsPage;
import com.verifone.tests.BaseTest;
import mock.AppMock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AppDetailsPageTest extends BaseTest {

    // TODO Replace mock data to data from API
    private AppMock app;
    private User merchant;

    private AppDetailsPage appDetailsPage;

    @BeforeClass
    public void getTestData() {
        merchant = envConfig.getCredentials().getVFMPMer();
        app = new AppMock();
    }

    @BeforeMethod
    public void beforeTest() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();
        AppCatalogPage appCatalogPage = new AppCatalogPage();
        appDetailsPage = new AppDetailsPage();

        loginPage.navigate();
        loginPage.doLogin(merchant);
        // TODO Uncomment after adding route
//        appCatalogPage.navigate();
        homePage.mainMenu.clickApplications();
        homePage.mainMenu.clickAppCatalogue();
        appCatalogPage.cardsViewTable.clickAppPageButton(0);
    }

    @Test(priority = 1, testName = "Displaying of Application details", groups = {"ui", "regression"})
    public void applicationDetailsUI() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(appDetailsPage.getAppName().text(), app.getName(), "App name");
        softAssert.assertEquals(appDetailsPage.getAppId().text(), app.getMarketplaceAppId(), "Marketplace app id");
        softAssert.assertEquals(appDetailsPage.getAppDescription().text(), app.getDescription(), "App description");
        softAssert.assertAll();
    }
}
