package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBAAccountPage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class UnsubscribeAppTest extends BaseTest {

    private static String getAppName;
    @Test(testName = "LogIn & unsubscribe an app", description = "log in to CBA account and remove app from apps list")

    public void CBAUnsubscribeAppTestUI() throws InterruptedException {

        //loginCBA(createMerchantUser());
        loginCBA(createAssignUser());
        getAppName = BaseTest.envConfig.getAppName();
        CBAAccountPage account = PageFactory.getCBAAccount();
        account.cancelSubscribsion(getAppName);

    }
}
