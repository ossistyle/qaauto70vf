package com.verifone.tests.selenide;

import com.verifone.infra.User;
import com.verifone.pages.selenide.pages.AppCatalogPage;
import com.verifone.pages.selenide.pages.HomePage;
import com.verifone.pages.selenide.pages.LoginPage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class AngularAppTest extends BaseTest {

    private AppCatalogPage appCatalogPage;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeClass
    public void initPages() {
        appCatalogPage = new AppCatalogPage();
        homePage = new HomePage();
        loginPage = new LoginPage();
    }

    @Test(priority=1, testName = "Angular8 Test")
    public void testAngularUI() throws InterruptedException {
        open("");

        User merchant = envConfig.getCredentials().getVFMPMer();

        loginPage.enterLogin(merchant.getUserName());
        loginPage.enterPassword(merchant.getPassword());
        loginPage.clickLogin();

        homePage.mainMenu.clickApplications();
        homePage.mainMenu.clickAppCatalogue();

        appCatalogPage.enterFilterApps("Carbon");
        appCatalogPage.clickSearch();
        appCatalogPage.clickQuickViewButton(1);
        appCatalogPage.clickAppPageButton(1);
        appCatalogPage.mainMenu.clickSettings();
        appCatalogPage.mainMenu.clickUsers();

        Thread.sleep(10000);
    }
}
