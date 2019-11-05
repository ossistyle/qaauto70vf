package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAResellerPage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.createVHQMumbaiUser;
import static com.verifone.tests.steps.mpPortal.Steps.loginCBA;

public class ResellerManageCompanies extends BaseTest {

    @Test(priority = 1, testName = "LogIn & Join or leave the Company -> Reseller", description = "LogIn into CBA Marketplace and Join or Leave the company depending on the status.")
    public void CBAResellerJoinTheCompanyTestUI() {

        // Login in to CBA Marketplace
        loginCBA(createVHQMumbaiUser());

        CBAResellerPage resellerPage = PageFactory.getCBAResellerPage();
        resellerPage.selectCompanies();
        resellerPage.searchCompaniesToManage();
        resellerPage.manageCompanyOperations();
    }

    @Test(priority = 2, testName = "LogIn & Leave or Join the Company -> Reseller", description = "LogIn into CBA Marketplace and Leave or Join the company depending on the status.")
    public void CBAResellerLeaveTheCompanyTestUI() {

        // Login in to CBA Marketplace
        loginCBA(createVHQMumbaiUser());

        CBAResellerPage resellerPage = PageFactory.getCBAResellerPage();
        resellerPage.selectCompanies();
        resellerPage.searchCompaniesToManage();
        resellerPage.manageCompanyOperations();
    }

}
