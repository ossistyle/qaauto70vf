package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAccount;
import com.verifone.pages.mpPages.CBAMarketplace;
import com.verifone.pages.mpPages.CBAMyApps;
import com.verifone.pages.vhqPages.VHQDownloadLibrary;
import com.verifone.pages.vhqPages.VHQHomePage;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.mpPortal.Steps;
import org.testng.annotations.Test;

public class PurchaseAppUI extends BaseTest
{
    private static String appName = "TestDevport4839";

    @Test(priority=1, testName = "LogIn & verify MyApps", description = "log in to CBA MyApps and verify myApps list")
    public void CBAMyAppsTestUI()
    {
        Steps.loginCBA();
        Steps.verifyMyAppsCBA(appName);
    }

    @Test(priority=2, testName = "LogIn & subscribe an app", description = "log in to CBA marketPlace and purchase an application")
    public void CBASubscribeAppTestUI() throws InterruptedException {

        Steps.loginCBA();

        CBAMarketplace market = (CBAMarketplace) PageFactory.getPage("CBAMarketplace");
        market.searchForApp(appName);
        market.veryfyListingApps();
        market.buyApp();

        CBAMyApps myApps = (CBAMyApps) PageFactory.getPage("CBAMyApps");
        myApps.verifyMessage();

    }

    @Test(priority=3, testName = "VHQ LogIn & Package Download", description = "log in to VHQ EndPoint and verify a package is downloaded to Library")

    public void VHQ_PackageIsDownloadedUI() throws InterruptedException {

        Steps.loginVHQ();

        VHQHomePage vhq = (VHQHomePage) PageFactory.getPage("VHQHomePage");
        vhq.verifyCustomer();
        vhq.verifyDownloadLibrary();

        VHQDownloadLibrary downLab = (VHQDownloadLibrary) PageFactory.getPage("VHQDownloadLibrary");
        downLab.verifyPackageExist(appName);
    }

    @Test(priority=4, testName = "LogIn & unsubscribe an app", description = "log in to CBA account and remove app from apps list")

    public void CBAUnsubscribeAppTestUI() throws InterruptedException {

        Steps.loginCBA();

        CBAAccount account = (CBAAccount) PageFactory.getPage("CBAAccount");
        account.manageApps (appName);
        account.unsubscribeApp(appName);
    }

}
