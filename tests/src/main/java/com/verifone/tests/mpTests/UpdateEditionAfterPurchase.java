package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.*;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import static com.verifone.pages.BasePage.testLog;
import static com.verifone.tests.steps.mpPortal.Steps.*;
import static com.verifone.tests.steps.mpPortal.Steps.createAssignUser;

public class UpdateEditionAfterPurchase extends BaseTest {
    private String updateEditionApp;
    private boolean TestPassFlag = true;
    private WebDriver driver;
    private String editionName = "One Time Pay - Custom";
    private String editionCode = "One time";
    private String editionSuccessInfo = "Your pricing edition has been saved.";
    private String deleteEditionInfo = "Your edition has been deleted";

    @Test(priority = 1, testName = "LogIn & Purchase (subscribe) an app", description = "Login into CBA Marketplace and Purchase (subscribe) App if it is not present in My Apps list.")
    public void CBAPurchaseOneTimePayAppUI() throws Exception {
        loginCBA(createAssignUser());

        //get the update product name from the properties file
        updateEditionApp = BaseTest.envConfig.getGroupInfo("MPUpdateEdition");

        CBAMarketplacePage market = PageFactory.getCBAMarketplace();
        CBAMyAppsPage myApps = PageFactory.getCBAMyApps();

        testLog.info("------------------------------ Check Application Existence In My Apps List ------------------------------");

        //Check the existence of application in My Apps list
        //If application is not exist then purchase it.
        if (!myApps.isAppExistsInMyAppsList(updateEditionApp)) {
            testLog.info("------------ " + updateEditionApp + " App not exist in My Apps list. Please purchase it -----------");
            /*Search & Purchase app from the marketplace*/
            market.searchForApp(updateEditionApp);
            market.buyOneTimeApp();
        }
    }

    @Test(enabled = true, priority = 2, testName = "Delete Edition If Exist", description = "As a MP Manager, login into CBA MarketPlace, delete edition if it already exit.")
    public void DeleteEditionIfAlreadyExitTestUI() throws Exception {

        loginMPPortalAsEOAdmin();

        //get the update product name from the properties file
        updateEditionApp = BaseTest.envConfig.getGroupInfo("MPUpdateEdition");

        CBADashboardPage cbaDashboard = PageFactory.getCBADashboard();
        CBAProductsPage cbaProducts = PageFactory.getCBAProducts();
        MPProductsPage productsPage = PageFactory.getMPProductsPage();

        testLog.info("------------------ Navigate to manage >> Marketplace >> Product -----------------");
        //Navigate to manage >> Marketplace >> Product
        cbaDashboard.clickOnManageMenu();
        cbaDashboard.clickOnMarketPlaceOption();
        cbaDashboard.clickOnProductTab();

        //Check availability of the product inside the production catalog.
        if (!cbaProducts.isProductAvailable(updateEditionApp)) {
            testLog.info("------------------ Product is not available to use -----------------");
            throw new SkipException("Product is not available to use");
        }

        testLog.info("------------------ Navigate to Product Dashboard Page -----------------");
        //Click on Edit Product
        productsPage.clickOnSettingIcon();
        productsPage.clickOnEditProduct();
        productsPage.loadProductDashboardPage();

        testLog.info("------------------ Navigate to Edition Page & Save Plan -----------------");
        //Click on Edition
        productsPage.clickEdition();


        testLog.info("------------------ Check Edition Exist -----------------");
        //Check One time Pay edition exist
        //Delete if is already exist and add new edition
        if (!productsPage.checkEditionExit()) {
            testLog.info("------------------ Edition doesn't exit to delete !! -----------------");
        } else {
            testLog.info("------------------ Delete Edition -----------------");
            productsPage.clickOnDeleteEditionBtn();

            //verify delete message
            if (!Assertions.compareBoolean(true, productsPage.editionErrorMessage(deleteEditionInfo), "Delete message is display : ", testLog, driver)) {
                testLog.info("---------------------------- Edition plan is not deleted successfully !! ------------------------------");
                TestPassFlag = false;
            }

            Assert.assertTrue(TestPassFlag);
        }
    }

    @Test(enabled = true, priority = 3, testName = "Add One Time Pay edition in the published app", description = "As a MP Manager, login into CBA MarketPlace, check availability of published app and add new edition as a one time.", dependsOnMethods = "DeleteEditionIfAlreadyExitTestUI")
    public void AddNewEditionInPublishedAppTestUI() throws Exception {
        loginMPPortalAsEOAdmin();

        CBADashboardPage cbaDashboard = PageFactory.getCBADashboard();
        CBAProductsPage cbaProducts = PageFactory.getCBAProducts();
        MPProductsPage productsPage = PageFactory.getMPProductsPage();

        driver = cbaProducts.getDriver();

        testLog.info("------------------ Navigate to manage >> Marketplace >> Product -----------------");
        //Navigate to manage >> Marketplace >> Product
        cbaDashboard.clickOnManageMenu();
        cbaDashboard.clickOnMarketPlaceOption();
        cbaDashboard.clickOnProductTab();

        testLog.info("------------------ Navigate to Product Dashboard Page -----------------");

        //Click on Edit Product
        cbaProducts.isProductAvailable(updateEditionApp);
        productsPage.clickOnSettingIcon();
        productsPage.clickOnEditProduct();
        productsPage.loadProductDashboardPage();

        testLog.info("------------------ Navigate to Edition Page & Save Plan -----------------");
        //Click on Edition and add new edition - one time pay
        productsPage.clickEdition();
        productsPage.clickOnAddNewEdition();
        productsPage.setEditionInfo(editionName, editionCode);
        productsPage.selectShowAsPrimaryPlan();
        productsPage.selectRevenueModel(editionCode);

        testLog.info("------------------ Save Edition Plan -----------------");
        productsPage.clickOnSaveEditionBtn();

        //verify success message
        if (!Assertions.compareBoolean(true, productsPage.editionErrorMessage(editionSuccessInfo), "Success message is display : ", testLog, driver)) {
            testLog.info("------------------ Edition plan does not saved successfully !! -----------------");
            TestPassFlag = false;
        }

        Assert.assertTrue(TestPassFlag);
        cbaProducts.publishProduct();
    }

    @Test(priority = 4, testName = "Verify Added Edition In the Purchased App", description = "As a Merchant, Login into CBA Marketplace and check newly added edition in the purchased app.", dependsOnMethods = "DeleteEditionIfAlreadyExitTestUI")
    public void CheckAddedEditionInPurchasedAppTestUI() throws Exception {
        loginCBA(createAssignUser());

        //get the update product name from the properties file
        updateEditionApp = BaseTest.envConfig.getGroupInfo("MPUpdateEdition");

        CBAAccountPage accountPage = PageFactory.getCBAAccount();

        accountPage.clickOnAccountOption();
        accountPage.clickOnManageApps();
        accountPage.searchAppInManageApps(updateEditionApp);
        accountPage.clickOnUpdateSubscriptionBtn();

        testLog.info("------------------------------ Check Added Edition In the App ------------------------------");

        //Check the existence of application in My Apps list
        //If application is not exist then purchase it.
        if (!Assertions.compareBoolean(true, accountPage.checkAddedEditionInManageApps(), "Edition is display : ", testLog, driver)) {
            testLog.info("------------------------------ Edition not added in the app ------------------------------");
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);
        testLog.info("------------------------------ Edition is display as expected !! ------------------------------");
    }

    @Test(enabled = true, priority = 5, testName = "Delete Edition & Published App", description = "As a MP Manager, login into CBA MarketPlace and delete edition.", dependsOnMethods = "DeleteEditionIfAlreadyExitTestUI")
    public void DeleteEditionTestUI() throws Exception {

        loginMPPortalAsEOAdmin();

        //get the update product name from the properties file
        updateEditionApp = BaseTest.envConfig.getGroupInfo("MPUpdateEdition");

        CBADashboardPage cbaDashboard = PageFactory.getCBADashboard();
        CBAProductsPage cbaProducts = PageFactory.getCBAProducts();
        MPProductsPage productsPage = PageFactory.getMPProductsPage();

        testLog.info("------------------ Navigate to manage >> Marketplace >> Product -----------------");
        //Navigate to manage >> Marketplace >> Product
        cbaDashboard.clickOnManageMenu();
        cbaDashboard.clickOnMarketPlaceOption();
        cbaDashboard.clickOnProductTab();

        //Check availability of the product inside the production catalog.
        if (!cbaProducts.isProductAvailable(updateEditionApp)) {
            testLog.info("------------------ Product is not available to use -----------------");
            throw new SkipException("Product is not available to use");
        }

        testLog.info("------------------ Navigate to Product Dashboard Page -----------------");
        //Click on Edit Product
        productsPage.clickOnSettingIcon();
        productsPage.clickOnEditProduct();
        productsPage.loadProductDashboardPage();

        testLog.info("------------------ Navigate to Edition Page & Save Plan -----------------");
        //Click on Edition
        productsPage.clickEdition();


        testLog.info("------------------ Check Edition Exist -----------------");
        //Check One time Pay edition exist
        //Delete if is already exist and add new edition
        if (!productsPage.checkEditionExit()) {
            testLog.info("------------------ Edition doesn't exit to delete !! -----------------");
        } else {
            testLog.info("------------------ Delete Edition -----------------");
            productsPage.clickOnDeleteEditionBtn();

            //verify delete message
            if (!Assertions.compareBoolean(true, productsPage.editionErrorMessage(deleteEditionInfo), "Delete message is display : ", testLog, driver)) {
                testLog.info("---------------------------- Edition plan is not deleted successfully !! ------------------------------");
                TestPassFlag = false;
            }
            Assert.assertTrue(TestPassFlag);
            testLog.info("---------------------------- Edition plan deleted successfully !! ------------------------------");
            cbaProducts.publishProduct();
            testLog.info("---------------------------- Published App in the Marketplace ------------------------------");
        }
    }

}
