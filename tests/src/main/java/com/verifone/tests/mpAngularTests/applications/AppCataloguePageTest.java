package com.verifone.tests.mpAngularTests.applications;

import com.verifone.infra.User;
import com.verifone.pages.mpAngularPages.pages.HomePage;
import com.verifone.pages.mpAngularPages.pages.LoginPage;
import com.verifone.pages.mpAngularPages.pages.applications.AppCatalogPage;
import com.verifone.tests.BaseTest;
import mock.AppMock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AppCataloguePageTest extends BaseTest {

    // TODO Replace mock data to data from API
    private AppMock app;
    private User merchant;
    private int testAppIndex = 0;

    private AppCatalogPage appCatalogPage;

    @BeforeClass
    public void getTestData() {
        merchant = envConfig.getCredentials().getVFMPMer();
        app = new AppMock();
    }

    @BeforeMethod
    public void beforeTest() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();
        appCatalogPage = new AppCatalogPage();

        loginPage.navigate();
        loginPage.doLogin(merchant);
        // TODO Uncomment after adding route
//        appCatalogPage.navigate();
        homePage.mainMenu.clickApplications();
        homePage.mainMenu.clickAppCatalogue();
    }

    @Test(priority=1, testName = "App Catalog > Card view elements", groups = {"ui", "regression"})
    public void cardsViewAppsListElementsUI() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(appCatalogPage.cardsView.getAppName(testAppIndex).text(), app.getName(), "App name");
        softAssert.assertTrue(appCatalogPage.cardsView.getCardImage(testAppIndex).isDisplayed(), "App image");
        softAssert.assertEquals(appCatalogPage.cardsView.getAppDescription(testAppIndex).text(), app.getDescription(), "App description");
        softAssert.assertAll();
    }

    @Test(priority=1, testName = "App Catalog > Grid view elements", groups = {"ui", "regression"})
    public void gridViewAppsListElementsUI() {
        appCatalogPage.filterPanel.clickGridView();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(appCatalogPage.gridView.getAppName(testAppIndex).text(), app.getName(), "App name");
        softAssert.assertEquals(appCatalogPage.gridView.getAppDescription(testAppIndex).text(), app.getDescription(), "App description");
        softAssert.assertEquals(appCatalogPage.gridView.getAppVersion(testAppIndex).text(), app.getVersion(), "App version");
        softAssert.assertAll();
    }

    @Test(priority=1, testName = "App Catalog > Quick app view elements", groups = {"ui", "regression"})
    public void quickViewBlockElementsUI() {
        appCatalogPage.cardsView.clickQuickViewButton(testAppIndex);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(appCatalogPage.quickView.getAppName().text(), app.getName(), "App name");
        softAssert.assertTrue(appCatalogPage.quickView.getAppImage().isDisplayed(), "App image");
        softAssert.assertEquals(appCatalogPage.quickView.getAppId().text(), app.getId(), "App id");
        softAssert.assertEquals(appCatalogPage.quickView.getAppVersionLabel().text(), "Version", "App version label");
        softAssert.assertEquals(appCatalogPage.quickView.getAppVersionValue().text(), app.getVersion(), "App version value");
        softAssert.assertEquals(appCatalogPage.quickView.getAppDescriptionLabel().text(), "App Desc", "App Desc label");
        softAssert.assertEquals(appCatalogPage.quickView.getAppDescriptionValue().text(), app.getDescription(), "App Desc value");
        softAssert.assertEquals(appCatalogPage.quickView.getAppPublishDateLabel().text(), "Publish date", "App Publish date label");
        softAssert.assertEquals(appCatalogPage.quickView.getAppPublishDateValue().text(), app.getPublishDate(), "App Publish date value");
        softAssert.assertEquals(appCatalogPage.quickView.getAppUpdateDateLabel().text(), "Update date", "App Update date label");
        softAssert.assertEquals(appCatalogPage.quickView.getAppUpdateDateValue().text(), app.getUpdateDate(), "Update date date value");
        softAssert.assertAll();
    }
}
