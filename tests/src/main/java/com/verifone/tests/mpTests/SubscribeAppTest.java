package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAssignPage;
import com.verifone.pages.mpPages.CBAMarketplacePage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class SubscribeAppTest extends BaseTest {

    private static String getAppName;
    private static String deviceUserName;
    public static String freeEditionApp;

    @Test(testName = "LogIn & subscribe an app", description = "log in to CBA marketPlace and purchase an application")
    public void CBASubscribeAppTestUI() throws Exception {

        //loginCBA(createMerchantUser());

        loginCBA(createAssignUser());

        freeEditionApp = BaseTest.envConfig.getFreeEditionApp();
        getAppName = BaseTest.envConfig.getAppName();

        CBAMarketplacePage market = PageFactory.getCBAMarketplace();
        market.searchForApp(getAppName);
        market.veryfyListingApps();
        market.isAppPurchased(freeEditionApp);
    }

    @Test(priority = 2, testName = "Login & Assign App to user", description = "Login in to CBA MarketPlace and assign application to the user")
    public void CBAAssignToUserAppUI() throws Exception {

        /* Login to CBAMarket Place */
        loginCBA(createAssignUser());

        getAppName = BaseTest.envConfig.getAppName();
        deviceUserName = BaseTest.envConfig.getDeviceUserName();

        System.out.println("get App name :" + getAppName);

        CBAMarketplacePage market = PageFactory.getCBAMarketplace();
        market.searchForApp(getAppName);
        market.veryfyListingApps();
        market.buyFreeApp();

        /* Move to Assign Apps to User */
        CBAAssignPage assignApp = PageFactory.getAssignAppPage();
        assignApp.moveToAssignApps();
        assignApp.btnSelectAssignAppsToUser();
        assignApp.searchAppToAssign(getAppName);
        assignApp.searchUserToAssign(deviceUserName);
        assignApp.userAssignment();
        assignApp.isAssignUpdated();
    }
}
