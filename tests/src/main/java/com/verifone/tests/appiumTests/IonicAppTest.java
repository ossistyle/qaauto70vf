package com.verifone.tests.appiumTests;

import com.verifone.pages.mpMobilePages.pages.HomePage;
import com.verifone.pages.mpMobilePages.pages.applications.ApplicationsPage;
import com.verifone.tests.BaseTestMobile;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IonicAppTest extends BaseTestMobile {

    private ApplicationsPage applicationsPage;
    private HomePage homePage;

    @BeforeClass
    public void initPages() {
        applicationsPage = new ApplicationsPage();
        homePage = new HomePage();
    }

    @Test
    public void ionic() throws InterruptedException {
        homePage.header.clickMenuButton();
        homePage.mainMenu.clickApplications();
        homePage.mainMenu.clickAppCatalog();
        applicationsPage.enterFilterApps("Test");

        Thread.sleep(5000);
    }
}
