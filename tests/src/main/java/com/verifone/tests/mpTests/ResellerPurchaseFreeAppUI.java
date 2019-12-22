package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.*;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class ResellerPurchaseFreeAppUI extends BaseTest {

    /**
     * This test case described the free pay app purchase on market place
     *
     * @author : Prashant Lokhande
     */

    public static String freeEditionApp;

    @Test(priority = 1, testName = "LogIn & Search Free App -> Reseller", description = "Login in to CBA Marketplace and search Free Edition App")
    public void CBASearchEditionFreeAppTestUI() throws Exception {

        // Login in to CBA Marketplace
        loginCBA(createVHQMumbaiUser());

        freeEditionApp = BaseTest.envConfig.getFreeEditionApp();

        // Move to the Marketplace and Purchase (subscribe) an app - edition One Time Pay app
        CBAMarketplacePage market = PageFactory.getCBAMarketplace();
        CBAResellerPage resellerPage = PageFactory.getCBAResellerPage();
        resellerPage.selectMerchantFromTheList();
        market.searchForApp(freeEditionApp);
        market.veryfyListingApps();
        market.isAppPurchased(freeEditionApp);
    }

    @Test(priority = 2, testName = "LogIn & Purchase (subscribe) an app -> Reseller", description = "Log in to CBA Marketplace and Purchase (subscribe) Free Edition App")
    public void CBAPurchaseOneTimePayAppUI() throws Exception {
        loginCBA(createVHQMumbaiUser());

        // Search & Purchase app from the marketplace
        CBAMarketplacePage market = PageFactory.getCBAMarketplace();
        CBAResellerPage resellerPage = PageFactory.getCBAResellerPage();
        resellerPage.selectMerchantFromTheList();
        market.searchForApp(freeEditionApp);
        market.buyFreeApp();

        //Assign purchased app to user
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.moveToAssignApps();
        assignApp.btnSelectAssignAppsToUser();
        assignApp.searchAppToAssign(freeEditionApp);
        assignApp.searchUserToAssign(createVHQMumbaiUser().getUserName());
        assignApp.userAssignment();
        assignApp.isAssignUpdated();
    }

    @Test(priority = 3, testName = "LogIn & Verify purchased (subscribed) app -> Reseller", description = "Log in to CBA Marketplace and verify purchased free app in the My Apps")
    public void CBAVerifyOneTimePayAppTestUI() throws Exception {
        loginCBA(createVHQMumbaiUser());

        //Select merchant from the dropdown list of main menu
        CBAResellerPage resellerPage = PageFactory.getCBAResellerPage();
        resellerPage.selectMerchantFromTheList();
        resellerPage.selectMyAppsOption();

        //Verify the purchased app is present in the My App
        CBAMyAppsPage myApps = PageFactory.getCBAMyApps();
        myApps.verifyPurchasedApp(freeEditionApp);
    }

    @Test(priority = 4, testName = "LogIn & Unsubscribe an App -> Reseller", description = "Log in to CBA account and Cancel Subscription of app from the apps list")
    public void CBAUnsubscribeAppTestUI() throws Exception {

        loginCBA(createVHQMumbaiUser());

        //select user from the list of users
        CBAResellerPage resellerPage = PageFactory.getCBAResellerPage();
        resellerPage.selectMerchantFromTheList();

        //Remove purchased app
        CBAAccountPage account = PageFactory.getCBAAccount();
        account.cancelSubscribsion(freeEditionApp);
    }
}
