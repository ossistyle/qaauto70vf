package com.verifone.tests.mpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.*;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.mpPortal.Steps;
import com.verifone.utils.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.verifone.pages.BasePage.testLog;
import static com.verifone.tests.steps.mpPortal.Steps.loginCBA;
import static com.verifone.tests.steps.mpPortal.Steps.navigateCBAHome;
//--------------------------------------------------------------------------

/**
 * Portal: Marketplace
 * This test set verify Reseller can Add/Update/Delete Group
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class ManageGroupsByResellerUI  extends BaseTest {

    public void navigator() throws Exception {
        User MPReseller = EntitiesFactory.getEntity("MPReseller");
//        String MPResellerMail = MPReseller.getUserName();
//        String MPResellerPwd = MPReseller.getPassword();
//        navigateCBAHome();
//        Steps.loginMPPortal(MPResellerMail, MPResellerPwd, "");
        Steps.loginCBA(MPReseller);

    }

    @Test(enabled = true, priority=1, testName = "Reseller Add Group in Merchant Org, Assign and Un-Assign Users", groups = { "MPPhase2" }, alwaysRun = true)
    public void ResellerAddGroupUI() throws Exception {
        String GroupName = "ATestGroup";

        navigator();
        Steps.ResellerSwitchMerchantCompany();
        Steps.ResellerNavigateUsersPage();
        Steps.CreateGroup(GroupName);
        Assert.assertTrue(Steps.AddRemoveUserToGroup(3000));

    }

    @Test(enabled = true, priority=2, testName = "Reseller Update Group in Merchant Org and Delete Group", groups = { "MPPhase2" }, alwaysRun = true)

    public void ResellerUpdateDeleteGroupUI() throws Exception {
        Boolean TestPassFlag = true;
        String GroupName = "ATestGroup";

        navigator();
        Steps.ResellerSwitchMerchantCompany();
        Steps.ResellerNavigateUsersPage();
        TestPassFlag = Steps.UpdateGroup(GroupName, GroupName + "Updated");
        Steps.DeleteGroup();

        Assert.assertTrue(TestPassFlag);
    }
}
