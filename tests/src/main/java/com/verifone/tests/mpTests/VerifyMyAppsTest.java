package com.verifone.tests.mpTests;

import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class VerifyMyAppsTest extends BaseTest {
    private static String getAppName;

    @Test(testName = "LogIn & verify MyApps", description = "log in to CBA MyApps and verify myApps list")
    public void CBAMyAppsTestUI() {

        //loginCBA(createMerchantUser());
        loginCBA(createAssignUser());

        getAppName = BaseTest.envConfig.getAppName();
        verifyMyAppsCBA(getAppName);

    }

}
