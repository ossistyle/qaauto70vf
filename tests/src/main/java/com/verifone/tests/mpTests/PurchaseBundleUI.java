package com.verifone.tests.mpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.*;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.mpPortal.Steps;
import com.verifone.utils.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import static com.verifone.pages.BasePage.testLog;
import static com.verifone.pages.mpPages.ProductsTabBundlePage.bundleTbl;
import static com.verifone.tests.steps.mpPortal.Steps.createMerchantUser;
import static com.verifone.tests.steps.mpPortal.Steps.loginCBA;
import static com.verifone.tests.steps.mpPortal.Steps.navigateCBAHome;

public class PurchaseBundleUI extends BaseTest {

    private static Integer TimeOut = 4000;
    private static String fBundle = "Autocreated1";
    private static String sBundle = "Autocreated2";
    private String fBundleDescription = "First autocreated";
    private String sBundleDescription = "Second autocreated";
    private String fLongBundleDescription = "First with partial checkout";
    private String sLongBundleDescription = "Second with full checkout for application from bundle";
    private String app1 = "Trm - Free - TerminalA - Prod";
    private String app2 = "ADQA Conflict Package";
    private String app3 = "NewBroccoli";
    private String app4 = "New Spinach";
    private static String Env = "https://verifoneausandbox.byappdirect.com/login";
    private static String MerchantEmail1 = "merchantforautomation01@getnada.com";
    private static String MerchantPassw1 = "Veri1234";




    public void navigator() throws Exception {
        User EOAdminSupport = EntitiesFactory.getEntity("EOAdminSupport");
        String EOAdminSupportMail = EOAdminSupport.getUserName();
        String EOAdminSupportPwd = EOAdminSupport.getPassword();
        String EOAdminSupportAnsw = EOAdminSupport.getSecurityAnswer();
        navigateCBAHome();
        Steps.loginMPPortal(EOAdminSupportMail, EOAdminSupportPwd, EOAdminSupportAnsw);
    }

   /* @Test(enabled = true, priority=1, testName = "Verify no bundle from old test exist in MP", groups = { "MPRegression" }, alwaysRun = true)
    public void bundleVerificationUI() throws Exception {

        WebDriver driver = new MPHomePage().getDriver();
        Boolean TestPassFlag = true;
        navigator();
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        MPHomePage MPHomePage = (MPHomePage) PageFactory.getPage("MPHomePage");

        testLog.info("------------------------------------------------- Navigate to My Profile page -------------------------------------------------");

        MPHomePage.clickHeaderManageMenu();
        Thread.sleep(TimeOut);
        MPHomePage.clickMarketplaceSubMenu();

        testLog.info("------------------------------------------------- Manage Marketplace page -------------------------------------------------");

        Thread.sleep(TimeOut + 1000);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        ManageMarketplacePage ManageMarketplacePage = (ManageMarketplacePage) PageFactory.getPage("ManageMarketplacePage");

//		Click Product tab
        ManageMarketplacePage.clickTabProduct();

        testLog.info("------------------------------------------------- Products tab -------------------------------------------------");

        Thread.sleep(TimeOut);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        ProductsTab productsTab = (ProductsTab) PageFactory.getPage("ProductsTab");
        ProductsTabProductCatalogPage prodCatalog = (ProductsTabProductCatalogPage) PageFactory.getPage("ProductsTabProductCatalogPage");
        ProductsTabBundlePage productsTabBundlePage = (ProductsTabBundlePage) PageFactory.getPage("ProductsTabBundlePage");

        //Checking first product if exist
        productsTab.clickMenuProductionCatalog();
        prodCatalog.clickLastPage();
        int ActualRow = prodCatalog.getTblRowProduct(fBundle);
        if(ActualRow!=0)
        {
            prodCatalog.clickMenuEditProduct(ActualRow);
            prodCatalog.clickMenuRemoveProduct(ActualRow);
            productsTab.clickMenuBundle();
            int ActualBundleRow = productsTabBundlePage.getTblRowBundle(fBundle,bundleTbl);
            productsTabBundlePage.clickMenuEditBundle(ActualBundleRow);
            productsTabBundlePage.clickMenuDeleteBundle(ActualBundleRow);

            testLog.info("------------------------------------------------- Verify Confirmation First bundle deleted --------------------------------------------------");
            if (!Assertions.compareBoolean(true, productsTab.msgConfirmationText1().contains("deleted " + fBundle), "Delete Bundle confirmation message displayed as expected: ", testLog, driver)) {
                TestPassFlag = false;
            }

            productsTab.clickMenuProductionCatalog();
            prodCatalog.clickLastPage();
        }

        //Checking second product if exist
        int ActualRow2 = prodCatalog.getTblRowProduct(sBundle);

        if(ActualRow2!=0)
        {
            prodCatalog.clickLastPage();
            prodCatalog.clickMenuEditProduct(ActualRow);
            prodCatalog.clickMenuRemoveProduct(ActualRow);
            productsTab.clickMenuBundle();
            int ActualBundleRow = productsTabBundlePage.getTblRowBundle(sBundle,bundleTbl);
            productsTabBundlePage.clickMenuEditBundle(ActualBundleRow);
            productsTabBundlePage.clickMenuDeleteBundle(ActualBundleRow);

            testLog.info("------------------------------------------------- Verify Confirmation Second bundle deleted --------------------------------------------------");
            if (!Assertions.compareBoolean(true, productsTab.msgConfirmationText1().contains("deleted " + sBundle), "Delete Bundle confirmation message displayed as expected: ", testLog, driver)) {
                TestPassFlag = false;
            }
        }
        Assert.assertTrue(TestPassFlag);
    }*/
/*
    @Test(enabled = true, priority=2, testName = "Create Bundle with Checkout for partial bundle", groups = { "MPRegression" }, alwaysRun = true)
    public void newBundlePartialCheckoutUI() throws Exception {

        WebDriver driver = new MPHomePage().getDriver();
        Boolean TestPassFlag = true;
        navigator();
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        MPHomePage MPHomePage = (MPHomePage) PageFactory.getPage("MPHomePage");

        testLog.info("------------------------------------------------- Navigate to My Profile page -------------------------------------------------");

        MPHomePage.clickHeaderManageMenu();
        Thread.sleep(TimeOut);
        MPHomePage.clickMarketplaceSubMenu();

        testLog.info("------------------------------------------------- Manage Marketplace page -------------------------------------------------");

        Thread.sleep(TimeOut + 1000);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        ManageMarketplacePage ManageMarketplacePage = (ManageMarketplacePage) PageFactory.getPage("ManageMarketplacePage");
        ManageMarketplacePage.clickTabProduct();

        testLog.info("------------------------------------------------- Products tab -------------------------------------------------");

        Thread.sleep(TimeOut);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        ProductsTab productsTab = (ProductsTab) PageFactory.getPage("ProductsTab");
        ProductsTabProductCatalogPage prodCatalog = (ProductsTabProductCatalogPage) PageFactory.getPage("ProductsTabProductCatalogPage");
        ProductsTabBundlePage productsTabBundlePage = (ProductsTabBundlePage) PageFactory.getPage("ProductsTabBundlePage");
        productsTab.clickMenuBundle();

        testLog.info("------------------------------------------------- New Bundle \"Autocreated1\" -------------------------------------------------");

        //Fill Listing Information fields
        productsTabBundlePage.addBundle();
        productsTabBundlePage.clickListingInformation();
        productsTabBundlePage.fieldsListingInfo(fBundle,fBundleDescription,fLongBundleDescription);

        //Choose Applications
        productsTabBundlePage.clickApplications();
        productsTabBundlePage.addApp(app1);
        productsTabBundlePage.addApp(app2);

        //Choose Cancellation Restriction
        productsTabBundlePage.clickCancelRestrictionBtn();
        productsTabBundlePage.chooseRestrictionType("partial");

        productsTabBundlePage.clickPublish();

        testLog.info("------------------------------------------------- Verify Confirmation First bundle was added --------------------------------------------------");
        if (!Assertions.compareBoolean(true, productsTab.msgConfirmationText1().contains("added " + fBundle), "Add first Bundle confirmation message displayed as expected: ", testLog, driver)) {
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);
    }

    @Test(enabled = true, priority=3, testName = "Create Bundle with full checkout  bundle", groups = { "MPRegression" }, alwaysRun = true)
    public void newBundleFullCheckoutUI() throws Exception {

        WebDriver driver = new MPHomePage().getDriver();
        Boolean TestPassFlag = true;
        navigator();
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        MPHomePage MPHomePage = (MPHomePage) PageFactory.getPage("MPHomePage");

        testLog.info("------------------------------------------------- Navigate to My Profile page -------------------------------------------------");

        MPHomePage.clickHeaderManageMenu();
        Thread.sleep(TimeOut);
        MPHomePage.clickMarketplaceSubMenu();

        testLog.info("------------------------------------------------- Manage Marketplace page -------------------------------------------------");

        Thread.sleep(TimeOut + 1000);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        ManageMarketplacePage ManageMarketplacePage = (ManageMarketplacePage) PageFactory.getPage("ManageMarketplacePage");
        ManageMarketplacePage.clickTabProduct();

        testLog.info("------------------------------------------------- Products tab -------------------------------------------------");

        Thread.sleep(TimeOut);
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        ProductsTab productsTab = (ProductsTab) PageFactory.getPage("ProductsTab");
        ProductsTabProductCatalogPage prodCatalog = (ProductsTabProductCatalogPage) PageFactory.getPage("ProductsTabProductCatalogPage");
        ProductsTabBundlePage productsTabBundlePage = (ProductsTabBundlePage) PageFactory.getPage("ProductsTabBundlePage");
        productsTab.clickMenuBundle();

        testLog.info("------------------------------------------------- New Bundle \"Autocreated2\" -------------------------------------------------");

        //Fill Listing Information fields
        productsTabBundlePage.addBundle();
        productsTabBundlePage.clickListingInformation();
        productsTabBundlePage.fieldsListingInfo(sBundle,sBundleDescription,sLongBundleDescription);

        //Choose Applications
        productsTabBundlePage.clickApplications();
        productsTabBundlePage.clickNextApp();
        productsTabBundlePage.addApp(app3);
        productsTabBundlePage.addApp(app4);

        //Choose Cancellation Restriction
        productsTabBundlePage.clickCancelRestrictionBtn();
        productsTabBundlePage.chooseRestrictionType("full");

        productsTabBundlePage.clickPublish();

        testLog.info("------------------------------------------------- Verify Confirmation Second bundle was added --------------------------------------------------");
        if (!Assertions.compareBoolean(true, productsTab.msgConfirmationText1().contains("added " + sBundle), "Add second Bundle confirmation message displayed as expected: ", testLog, driver)) {
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);
    }
*/

    @Test(enabled = true, priority=4, testName = "Purchase Bundles ", groups = { "MPRegression" }, alwaysRun = true)
    public void MerchantPurchaseProductUI() throws Exception {
        loginCBA(createMerchantUser());
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.buyBundle(fBundle);
        market.clickMarketplaceBtn();
        market.buyBundle(sBundle);


        CBAAccount account = PageFactory.getCBAAccount();
        account.cancelSubscribsion(app1);
        account.cancelSubscribsion(app2);
        account.cancelBundleSubsucribsion(app3);


        /*testLog.info("------------------------------------------------- Verify No Apps  --------------------------------------------------");
        if (!Assertions.compareBoolean(true, productsTab.msgConfirmationText1().contains("added " + sBundle), "Add second Bundle confirmation message displayed as expected: ", testLog, driver)) {
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);*/
//p[text()='Visit the marketplace to get started.']




    }
}
