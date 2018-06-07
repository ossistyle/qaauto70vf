package com.verifone.tests.cpTests;

import com.verifone.pages.cpPages.LoginPage;
import com.verifone.pages.PageFactory;
import com.verifone.tests.BaseTest;
import org.testng.annotations.*;

/**
 * The purpose of this testLog is to testLog CP Estatemanager portal
 */

public class CpDevPortal extends BaseTest {


    public CpDevPortal() {
        propFilePath = "logIn.properties";
    }

    @Test(groups = {"UI", "CP-portal"})
    public void cpLoginTestUI() throws Exception {
        starTestLog("CpDevPortal", "CP dev Portal log in test");
        LoginPage loginPage = (LoginPage) PageFactory.getPage("LoginPage");
        loginPage.clickOmLoginBtn();
        loginPage.loginPageCp(prop.getProperty("user_id"), prop.getProperty("password_id"));

    }

    @Test(groups = {"UI", "CP-portal2"})
    public void cpLoginTest2UI() throws Exception {
//        testLog = BasePage.testLog = logger.startTest("CpDevPortal2", "CP dev Portal log in test2");
        starTestLog("blabla", "blablalbalba ");
        LoginPage loginPage = (LoginPage) PageFactory.getPage("LoginPage");
        loginPage.clickOmLoginBtn();
        loginPage.loginPageCp(prop.getProperty("user_id"), prop.getProperty("password_id"));

    }
}