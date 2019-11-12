package com.verifone.tests.steps.mpPortal;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cgPages.CGLoginPage;
import com.verifone.pages.cpPages.OktaLogin;
import com.verifone.pages.mpPages.*;
import com.verifone.pages.vhqPages.VHQLogin;
import com.verifone.utils.Assertions;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;

import static com.verifone.pages.BasePage.testLog;

public class Steps {

    public static void loginMPPortal(String Email, String Password, String SecAnswer, String Env) throws Exception {
        WebDriver driver = new MPHomePage().getDriver();
        String update = " updated";

//        String Env = "https://testverifone.appdirect.com/login";
        Boolean TestPassFlag = true;

        testLog.info("-------------------------------------------------Navigate to MP Portal-------------------------------------------------");

        driver.navigate().to(Env);
        LoginMPPortal LoginMPPortal = (LoginMPPortal) PageFactory.getPage("LoginMPPortal");
        //LoginMPPortal.clickLoginBtn();
        String Name;
        Name = Email.substring(0, Email.indexOf("@"));
        String Domain = Email.substring(Email.indexOf("@") + 1);

        if (Domain.contains("verifone")) {
            testLog.info("-------------------------------------------------Login as: " + Email + " " + Password + "-------------------------------------------------");
            Thread.sleep(2000);
            LoginMPPortal.loginInputEmail(Email);

            ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            OktaLogin OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
            Thread.sleep(2000);

            if (OktaLogin.loginOktaTitleExists()) {
                if (OktaLogin.loginOktaTitle().contains("Sign In")) {
                    OktaLogin.loginInputName(Name);
                    OktaLogin.loginInputPassword(Password);
                    OktaLogin.clickSignInBtn();
                }
                availableWindows = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(availableWindows.get(0));
                OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
                OktaLogin.loginInputAnswer(SecAnswer);
                testLog.info("Security answer: " + "");
                OktaLogin.clickVerifyBtn();

                Thread.sleep(2000);
            }
        } else {
            testLog.info("-------------------------------------------------Login as: " + Email + " " + Password + "-------------------------------------------------");
            Thread.sleep(2000);
            LoginMPPortal.loginInputEmail(Email);
            LoginMPPortal.loginInputPassword(Password);
            LoginMPPortal.clickLoginBtn();
        }
    }

    public static void loginMPPortal(String Email, String Password, String SecAnswer) throws Exception {

        WebDriver driver = new MPHomePage().getDriver();

        LoginMPPortal LoginMPPortal = PageFactory.getLoginMPPortal();

        String Name = Email.substring(0, Email.indexOf("@"));
        String Domain = Email.substring(Email.indexOf("@") + 1);

        if (Domain.contains("verifone")) {
            testLog.info("-------------------------------------------------Login as: " + Email + " " + Password + "-------------------------------------------------");
            Thread.sleep(2000);
            LoginMPPortal.loginInputEmail(Email);

            ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            OktaLogin OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
            Thread.sleep(2000);

            if (OktaLogin.loginOktaTitleExists()) {
                if (OktaLogin.loginOktaTitle().contains("Sign In")) {
                    OktaLogin.loginInputName(Name);
                    OktaLogin.loginInputPassword(Password);
                    OktaLogin.clickSignInBtn();
                }
                availableWindows = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(availableWindows.get(0));
                OktaLogin = (OktaLogin) PageFactory.getPage("OktaLogin");
                OktaLogin.loginInputAnswer(SecAnswer);
                testLog.info("Security answer: " + "");
                OktaLogin.clickVerifyBtn();

                Thread.sleep(2000);
            }
        } else {
            testLog.info("-------------------------------------------------Login as: " + Email + " " + Password + "-------------------------------------------------");
            Thread.sleep(2000);
            LoginMPPortal.loginInputEmail(Email);
            LoginMPPortal.loginInputPassword(Password);
            LoginMPPortal.clickLoginBtn();
        }
    }

    public static User createMerchantUser() {
        User merchant = EntitiesFactory.getEntity("MPMerchantAdmin");
        return merchant;
    }

    public static User createVHQTestUser() {
        User vhqTestAdmin = EntitiesFactory.getEntity("VHQTestUserAdmin");
        return vhqTestAdmin;
    }

    public static User createVHQMumbaiUser() {
        User vhqMumbaiAdmin = EntitiesFactory.getEntity("VHQMumbaiUserAdmin");
        return vhqMumbaiAdmin;
    }

    /**
     * @author : Prashant Lokhande
     * This method described to create new assign user.
     */
    public static User createAssignUser() {
        User assignUser = EntitiesFactory.getEntity("MPAssignUser");
        return assignUser;
    }

    public static User createAssignUser(String userName) {
        User assignUser = EntitiesFactory.getEntity(userName);
        return assignUser;
    }

    public static void loginCBA(User user) {
        navigateCBAHome();

        CBALoginPage loginPage = PageFactory.getCBALoginPage();
        loginPage.LogInToCBAAccount(user);
    }

    public static void navigateCBAHome() {
        CBAHomePage homePage = PageFactory.getCBAHomePage();
        homePage.clickOnLogInLink();
    }

    public static void verifyMyAppsCBA(String appName) {
        CBAMyApps myApps = PageFactory.getCBAMyApps();
        myApps.verifyAppSubcribed(appName);
    }

    public static void loginVHQ(User user) {
        VHQLogin vhqLogin = PageFactory.getVHQLogin();
        vhqLogin.LoginInVhq(user);

    }

    public static void loginCGPortal(User user) throws Exception {
        CGLoginPage loginPage = PageFactory.getCGLoginPage();
        loginPage.navigateToCGLogin();
        loginPage.doLogin(user);
        loginPage.getV1SignedPackage();
    }

   /* public static void loginCGPortal() throws Exception{
        CGLoginPage loginPage = PageFactory.getCGLoginPage();
        loginPage.getV1SignedPackage();
    }*/

    public static void loginDLMPortal() throws Exception {
        DLMLoginPage dlmLogin = PageFactory.getDLMLoginPage();
        dlmLogin.getV2SignedPackage();
    }

    public static User createCGUser() {
        User testCGUser = EntitiesFactory.getEntity("CGPortal");
        return testCGUser;
    }

    public static void loginMPPortalAsEOAdmin() throws Exception {
        User EOAdminSupport = EntitiesFactory.getEntity("EOAdminSupport");
        navigateCBAHome();
        loginMPPortal(EOAdminSupport.getUserName(), EOAdminSupport.getPassword(), EOAdminSupport.getSecurityAnswer());
    }


    public static void mpEoNavigator() throws Exception {
        User EOAdminSupport = EntitiesFactory.getEntity("EOAdminSupport");
        String EOAdminSupportMail = EOAdminSupport.getUserName();
        String EOAdminSupportPwd = EOAdminSupport.getPassword();
        String EOAdminSupportAnsw = EOAdminSupport.getSecurityAnswer();
        navigateCBAHome();
        loginMPPortal(EOAdminSupportMail, EOAdminSupportPwd, EOAdminSupportAnsw);
    }
    //--------------------------------------------------------------------------
    /**
     * Method: Reseller Switch Merchant Company.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public static void ResellerSwitchMerchantCompany() throws Exception {
        WebDriver driver = new MPHomePage().getDriver();
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        MPHomePage MPHomePage = (MPHomePage) PageFactory.getPage("MPHomePage");

        testLog.info("------------------------------------------------- Navigate to Merchant Company page, Account -------------------------------------------------");

        MPHomePage.clickMarketplaceMenuCompany();

        MPHomePage.clickSubMenuCompany();
    }
//--------------------------------------------------------------------------
    /**
     * Method: Reseller Navigate Users Page.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public static void ResellerNavigateUsersPage() throws Exception {
        WebDriver driver = new MPHomePage().getDriver();
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        MPHomePage MPHomePage = (MPHomePage) PageFactory.getPage("MPHomePage");

        MPHomePage.clickMenuAccount();

        testLog.info("------------------------------------------------- Account page -------------------------------------------------");

        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        MPAccountPage MPAccountPage = (MPAccountPage) PageFactory.getPage("MPAccountPage");

//		Click User tab
        MPAccountPage.clickTabUser();
    }
//--------------------------------------------------------------------------
    /**
     * Method: Create Group.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public static void CreateGroup(String GroupName) throws Exception {
        WebDriver driver = new MPUsersPage().getDriver();
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        testLog.info("------------------------------------------------- Groups tab -------------------------------------------------");

        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        MPUsersPage MPUsersPage = (MPUsersPage) PageFactory.getPage("MPUsersPage");
        MPUsersPage.clickSecGroups();

        testLog.info("----------------------------------------- click Create Group button -------------------------------------------------");

        Thread.sleep(3000);
        MPUsersPage.clickBtnCreateGroups();

        testLog.info("----------------------------------------- Create Group dialog window -------------------------------------------------");
        MPUsersPage.inputGroupName(GroupName);
        Thread.sleep(7000);

        MPUsersPage.clickBtnDlgCreateGroups();
    }
//--------------------------------------------------------------------------
    /**
     * Method: Update Group.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public static boolean UpdateGroup(String GroupName, String GroupNameUpd) throws Exception {
        Boolean TestPassFlag = true;
        WebDriver driver = new MPUsersPage().getDriver();
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        testLog.info("------------------------------------------------- Groups tab -------------------------------------------------");

        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        MPUsersPage MPUsersPage = (MPUsersPage) PageFactory.getPage("MPUsersPage");
        MPUsersPage.clickSecGroups();

        testLog.info("---------------------------------------------- click on Group -------------------------------------------------");

        MPUsersPage.clickGearGroupButton(GroupName);

        testLog.info("------------------------------------------------ Edit Group ---------------------------------------------------");

        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        MPGroupsPage MPGroupsPage = (MPGroupsPage) PageFactory.getPage("MPGroupsPage");

        MPGroupsPage.clickGearGroupButton();

        MPGroupsPage.clickMenuSettings();

        testLog.info("----------------------------------------------- Update Group ---------------------------------------------------");
        MPUsersPage.inputGroupName(GroupNameUpd);
        Thread.sleep(7000);

        MPUsersPage.clickBtnDlgCreateGroups();

        testLog.info("----------------------------------------- Verify Group Updated -------------------------------------------------");

        if (!Assertions.compareValue(MPGroupsPage.getGroupTitle(), GroupName + "Updated", "Verify Group updated", testLog, driver)) {
            TestPassFlag = false;
        }
        return TestPassFlag;
    }
//--------------------------------------------------------------------------

    /**
     * Method: Add/Remove Users to Group.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public static boolean AddRemoveUserToGroup(int Wait) throws Exception {
        Boolean TestPassFlag = true;
        WebDriver driver = new MPUsersPage().getDriver();
        testLog.info("----------------------------------------- Add User to Group and Submit -------------------------------------------------");
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        MPUsersPage MPUsersPage = (MPUsersPage) PageFactory.getPage("MPUsersPage");
        Thread.sleep(Wait);

        MPUsersPage.clickUserAddGroup();

        MPUsersPage.clickSubmitButton();

        testLog.info("----------------------------------------- Verify Success Message -------------------------------------------------");

        if (!Assertions.compareValue(MPUsersPage.msgGroupMembershipText(), "Group membership has been successfully updated.", "Verify Success Message", testLog, driver)) {
            TestPassFlag = false;
        }


        testLog.info("----------------------------------------- Remove User from Group and Submit -------------------------------------------------");

        Thread.sleep(Wait);

        MPUsersPage.clickUserRemoveGroup();

        MPUsersPage.clickSubmitButton();

        testLog.info("----------------------------------------- Verify Success Message -------------------------------------------------");

        if (!Assertions.compareValue(MPUsersPage.msgGroupMembershipText(), "Group membership has been successfully updated.", "Verify Success Message", testLog, driver)) {
            TestPassFlag = false;
        }
        return TestPassFlag;
    }
//--------------------------------------------------------------------------
    /**
     * Method: Delete Group from Group Details.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public static void DeleteGroup() throws Exception {
        Boolean TestPassFlag = true;
        WebDriver driver = new MPUsersPage().getDriver();
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        testLog.info("------------------------------------------------- Groups tab -------------------------------------------------");

        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        MPGroupsPage MPGroupsPage = (MPGroupsPage) PageFactory.getPage("MPGroupsPage");
        testLog.info("----------------------------------------------- Delete Group ---------------------------------------------------");

        MPGroupsPage.clickGearGroupButton();

        MPGroupsPage.clickMenuDelete();

        MPGroupsPage.clickSubmitButton();
    }
//--------------------------------------------------------------------------
}
