package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAccountPage;
import com.verifone.pages.mpPages.CBAAssignPage;
import com.verifone.pages.mpPages.CBAMarketplacePage;
import com.verifone.pages.mpPages.CBAMyAppsPage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class PurchaseYearlyRecurringAppUI extends BaseTest {

    /**
     * This test case described the yearly recurring free app purchase on market place
     *
     * @author : Prashant Lokhande
     */

    private static String getYearlyRecurringApp;

    @Test(priority = 1, testName = "LogIn & Search Yearly Recurring App", description = "Login in to CBA Marketplace & search Yearly Recurring App.")
    public void CBASearchYearlyRecurringAppTestUI() throws Exception {

        /*Login in to CBA Marketplace*/
        loginCBA(createAssignUser());

        getYearlyRecurringApp = BaseTest.envConfig.getYearlyRecurringApp();

        /*Move to the Marketplace and Purchase (subscribe) an app - edition Yearly Recurring Pay Free Trial app*/
        CBAMarketplacePage market = PageFactory.getCBAMarketplace();
        market.searchForApp(getYearlyRecurringApp);
        market.veryfyListingApps();
        market.isAppPurchased(getYearlyRecurringApp);
    }

    @Test(priority = 2, testName = "LogIn & Purchase (subscribe) an app", description = "Log in to CBA Marketplace and Purchase (subscribe) Merchant Yearly Recurring Pay App.")
    public void CBAPurchaseYearlyRecurringAppTestUI() throws Exception {
        loginCBA(createAssignUser());

        /* Search & Purchase app from the marketplace*/
        CBAMarketplacePage market = PageFactory.getCBAMarketplace();
        market.searchForApp(getYearlyRecurringApp);
        market.buyOneTimeApp();

        /*Assign purchased app to user*/
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.searchUserToAssign(createAssignUser().getUserName());
        assignApp.userAssignment();
        assignApp.isAssignUpdated();
    }

    @Test(priority = 3, testName = "LogIn & Verify purchased (subscribed) app", description = "Log in to CBA Marketplace and verify purchased Yearly Recurring Pay App in the My Apps")
    public void CBAVerifyYearlyRecurringAppTestUI() {
        loginCBA(createAssignUser());

        /*Verify the purchased app is present in the My App*/
        CBAMyAppsPage myApps = PageFactory.getCBAMyApps();
        myApps.verifyPurchasedApp(getYearlyRecurringApp);
    }

    @Test(priority = 4, testName = "LogIn & Unsubscribe an App", description = "Log in to CBA account and remove Yearly Recurring Pay app from apps list")
    public void CBAUnsubscribeYearlyRecurringAppTestUI() throws InterruptedException {

        loginCBA(createAssignUser());

        /*Cancel Subscription of purchased app*/
        CBAAccountPage account = PageFactory.getCBAAccount();
        account.cancelSubscribsion(getYearlyRecurringApp);
    }

}
