package com.verifone.tests.appiumTests;

import com.verifone.pages.mpMobilePages.pages.HomePage;
import com.verifone.pages.mpMobilePages.pages.applications.ApplicationsPage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CM5_IonicAppTest extends BaseTest {

    private ApplicationsPage applicationsPage;
    private HomePage homePage;

    @BeforeMethod
    public void initPages() {
        applicationsPage = new ApplicationsPage();
        homePage = new HomePage();
    }

    @Test(testName = "Ionic test")
    public void ionicMobile() {
        homePage.header.clickMenuButton();
        homePage.mainMenu.clickApplications();
        homePage.mainMenu.clickAppCatalog();
        applicationsPage.enterFilterApps("Test");
    }
}
