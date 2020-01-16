package com.verifone.tests.mpWebTests.applications;

import com.verifone.infra.User;
import com.verifone.pages.mpWebPages.pages.HomePage;
import com.verifone.pages.mpWebPages.pages.LoginPage;
import com.verifone.pages.mpWebPages.pages.applications.AppCatalogPage;
import com.verifone.pages.mpWebPages.pages.applications.AppDetailsPage;
import com.verifone.tests.BaseTest;
import io.qameta.allure.*;
import mock.AppMock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.textCaseSensitive;

public class AppDetailsPageTest extends BaseTest {

    // TODO Replace mock data to data from API
    private AppMock app;
    private User merchant;

    private AppDetailsPage appDetailsPage;

    @BeforeClass (description = "Get test data")
    public void getTestData() {
        merchant = envConfig.getCredentials().getVFMPMer();
        app = new AppMock();
    }

    @BeforeMethod (description = "Login as merchant > Open app catalog > Click app page button")
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

    @Test(description = "User be able to see app details: name, id, description on the App details page", groups = {"ui", "regression"}, testName = "App details test")
    @Feature ("")
    @Link ("")
    @Severity (SeverityLevel.NORMAL)
    @Description ("User be able to see app details: name, id, description on the App details page")
    public void applicationDetailsUI() {
        appDetailsPage.getAppName().should(exist).shouldHave(textCaseSensitive(app.getName()));
        appDetailsPage.getAppId().should(exist).shouldHave(textCaseSensitive(app.getMarketplaceAppId()));
        appDetailsPage.getAppDescription().should(exist).shouldHave(textCaseSensitive(app.getDescription()));
    }
}
