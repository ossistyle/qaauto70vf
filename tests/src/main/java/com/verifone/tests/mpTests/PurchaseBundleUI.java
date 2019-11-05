package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.*;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;
import java.util.ArrayList;
import static com.verifone.pages.BasePage.testLog;
import static com.verifone.tests.steps.mpPortal.Steps.*;


public class PurchaseBundleUI extends BaseTest {

    private String description = "This is autocreated description";
    private ArrayList<String> bundleName ;
    private ArrayList<String> appName;


    @Test(enabled = true, priority=1, testName = "Verify no bundle from old test exist in MP", groups = { "MPRegression" }, alwaysRun = true)
    public void bundleVerificationUI() throws Exception {
        bundleName = BaseTest.envConfig.getListOfBundle();
        mpEoNavigator();

        MPHomePage MPHomePage = (MPHomePage) PageFactory.getPage("MPHomePage");
        MPHomePage.clickHeaderManageMenu();
        MPHomePage.clickMarketplaceSubMenu();

        ManageMarketplacePage ManageMarketplacePage = (ManageMarketplacePage) PageFactory.getPage("ManageMarketplacePage");
        ManageMarketplacePage.clickTabProduct();

        ProductsTab productsTab = (ProductsTab) PageFactory.getPage("ProductsTab");
        ProductsTabProductCatalogPage prodCatalog = (ProductsTabProductCatalogPage) PageFactory.getPage("ProductsTabProductCatalogPage");
        ProductsTabBundlePage productsTabBundlePage = (ProductsTabBundlePage) PageFactory.getPage("ProductsTabBundlePage");
        productsTab.clickMenuProductionCatalog();
        prodCatalog.clickLastPage();

        //Unpublish bundle before deleting if exist
        prodCatalog.unpublishBundleIfExist(bundleName.get(0));
        prodCatalog.unpublishBundleIfExist(bundleName.get(1));

        productsTab.clickMenuBundle();

        //Delete Bundle if exist
        productsTabBundlePage.deleteBundle(bundleName.get(0));
        productsTabBundlePage.deleteBundle(bundleName.get(1));
    }


    @Test(enabled = true, priority=2, testName = "Verify no apps from old test exist in MP", groups = { "MPRegression" }, alwaysRun = true)
    public void appVerificationUI() throws Exception {
        appName =  BaseTest.envConfig.getListOfAppforBundle();
        loginCBA(createMerchantUser());

        CBAAccount account = PageFactory.getCBAAccount();
        try{
        account.cancelSubscribsion(appName.get(0));}
        catch(Exception e){
            testLog.info(appName.get(0) +" wasn't exist in app list before test");
        }
        try{
        account.cancelSubscribsion(appName.get(1));}
        catch(Exception e){
            testLog.info(appName.get(1) +" wasn't exist in app list before test");
        }
        try{
        account.cancelBundleSubsucribsion(appName.get(2));}
        catch(Exception e){
            testLog.info(appName.get(2) +" wasn't exist in app list before test");
        }
        try{
            account.cancelBundleSubsucribsion(appName.get(3));}
        catch(Exception e){
            testLog.info(appName.get(3) +" wasn't exist in app list before test");
        }

    }


    @Test(enabled = true, priority=3, testName = "Create Bundle with Checkout for partial bundle", groups = { "MPRegression" }, alwaysRun = true)
    public void newBundlePartialCheckoutUI() throws Exception {
        bundleName = BaseTest.envConfig.getListOfBundle();
        appName =  BaseTest.envConfig.getListOfAppforBundle();

        mpEoNavigator();
        MPHomePage MPHomePage = (MPHomePage) PageFactory.getPage("MPHomePage");
        MPHomePage.clickHeaderManageMenu();
        MPHomePage.clickMarketplaceSubMenu();

        ManageMarketplacePage ManageMarketplacePage = (ManageMarketplacePage) PageFactory.getPage("ManageMarketplacePage");
        ManageMarketplacePage.clickTabProduct();

        ProductsTab productsTab = (ProductsTab) PageFactory.getPage("ProductsTab");
        ProductsTabBundlePage productsTabBundlePage = (ProductsTabBundlePage) PageFactory.getPage("ProductsTabBundlePage");
        productsTab.clickMenuBundle();

        //Fill Listing Information fields
        productsTabBundlePage.addBundle();
        productsTabBundlePage.clickListingInformation();
        productsTabBundlePage.fieldsListingInfo(bundleName.get(0),description,description);

        //Choose Applications
        productsTabBundlePage.clickApplications();
        productsTabBundlePage.addApp(appName.get(0));
        productsTabBundlePage.addApp(appName.get(1));

        //Choose Cancellation Restriction
        productsTabBundlePage.clickCancelRestrictionBtn();
        productsTabBundlePage.chooseRestrictionType("partial");

        productsTabBundlePage.clickPublish();

    }


    @Test(enabled = true, priority=4, testName = "Create Bundle with full checkout  bundle", groups = { "MPRegression" }, alwaysRun = true)
    public void newBundleFullCheckoutUI() throws Exception {
        bundleName = BaseTest.envConfig.getListOfBundle();
        appName = BaseTest.envConfig.getListOfAppforBundle();

        mpEoNavigator();

        MPHomePage MPHomePage = (MPHomePage) PageFactory.getPage("MPHomePage");
        MPHomePage.clickHeaderManageMenu();
        MPHomePage.clickMarketplaceSubMenu();

        ManageMarketplacePage ManageMarketplacePage = (ManageMarketplacePage) PageFactory.getPage("ManageMarketplacePage");
        ManageMarketplacePage.clickTabProduct();

        ProductsTab productsTab = (ProductsTab) PageFactory.getPage("ProductsTab");
        ProductsTabBundlePage productsTabBundlePage = (ProductsTabBundlePage) PageFactory.getPage("ProductsTabBundlePage");
        productsTab.clickMenuBundle();

        //Fill Listing Information fields
        productsTabBundlePage.addBundle();
        productsTabBundlePage.clickListingInformation();
        productsTabBundlePage.fieldsListingInfo(bundleName.get(1),description,description);

        //Choose Applications
        productsTabBundlePage.clickApplications();
        productsTabBundlePage.clickNextApp();
        productsTabBundlePage.addApp(appName.get(2));
        productsTabBundlePage.addApp(appName.get(3));

        //Choose Cancellation Restriction
        productsTabBundlePage.clickCancelRestrictionBtn();
        productsTabBundlePage.chooseRestrictionType("full");

        productsTabBundlePage.clickPublish();
    }


    @Test(enabled = true, priority=5, testName = "Purchase Bundles ", groups = { "MPRegression" }, alwaysRun = true)
    public void MerchantPurchaseProductUI() throws Exception {

        bundleName = BaseTest.envConfig.getListOfBundle();
        loginCBA(createMerchantUser());
        CBAMarketplace market = PageFactory.getCBAMarketplace();
        market.buyBundle(bundleName.get(0));
        market.clickMarketplaceBtn();
        market.buyBundle(bundleName.get(1));
    }


     @Test(enabled = true, priority=6, testName = "Cancel subscription to app", groups = { "MPRegression" }, alwaysRun = true)
     public void CancelSubscriptionToAppUI() throws Exception {
        appName = BaseTest.envConfig.getListOfAppforBundle();
        loginCBA(createMerchantUser());
        CBAAccount account = PageFactory.getCBAAccount();
        account.cancelSubscribsion(appName.get(0));
        account.cancelSubscribsion(appName.get(1));
        account.cancelBundleSubsucribsion(appName.get(2));

     }
}
