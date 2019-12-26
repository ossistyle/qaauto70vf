package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBADashboardPage;
import com.verifone.pages.mpPages.CBAMarketplacePage;
import com.verifone.pages.mpPages.CBAProductsPage;
import com.verifone.pages.mpPages.MPProductsPage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.verifone.pages.BasePage.testLog;
import static com.verifone.tests.steps.mpPortal.Steps.*;

public class UploadAppsWithAgreementTestUI extends BaseTest {

    private boolean TestPassFlag = true;
    private String productName;

    @Test(enabled = true, priority = 1, testName = "Check App Presence in Production Catalog", description = "LogIn into CBA MarketPlace and Remove Product from the Catalog if it is present", groups = {"MPPhase2"})
    public void VerifyAppInProductionCatalogTestUI() throws Exception {
        loginMPPortalAsEOAdmin();

        //get the product name from the properties files
        productName = BaseTest.envConfig.getCbaProductName();

        WebDriver driver = new CBADashboardPage().getDriver();
        ArrayList<String> availableWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        CBADashboardPage cbaDashboard = PageFactory.getCBADashboard();

        testLog.info("------------------------------- Navigate to MarketPlace -----------------------------------");
        cbaDashboard.clickOnManageMenu();
        cbaDashboard.clickOnMarketPlaceOption();

        availableWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        testLog.info("------------------------------- Navigate to Product Tab -----------------------------------");
        cbaDashboard.clickOnProductTab();

        testLog.info("------------------------------- Navigate to Production Catalog -----------------------------------");

        CBAProductsPage cbaProducts = PageFactory.getCBAProducts();

        //Remove product from the production catalog only if it is present
        if (cbaProducts.isProductAvailable(productName)) {
            testLog.info("------------------Remove Product Details From Production Catalog -----------------");
            cbaProducts.removeProduct();
            cbaProducts.unPublishProduct();
            cbaProducts.deleteSatgingProduct();
        } else {

            //Remove product from the staging catalog only if it is present.
            testLog.info("------------------------------------- Navigate to Staging Catalog -------------------------------------");
            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            cbaProducts.clickStagingProduct();

            testLog.info("-------------------- Remove Product Details From Staging Catalog ---------------");
            cbaProducts.deleteSatgingProduct();
        }
    }

    @Test(enabled = true, priority = 2, testName = "Add Agreement Details in Listing info ", description = "I agree to the Terms of Service, Privacy and Refund policies message should be display", groups = {"MPPhase2"})
    public void CBAMarketPlaceAddAgreementTestUI() throws Exception {
        loginMPPortalAsEOAdmin();

        //Move to Product section, delete product if exists, create new product
        CBADashboardPage cbaDashboard = PageFactory.getCBADashboard();
        CBAProductsPage cbaProducts = PageFactory.getCBAProducts();
        MPProductsPage mpProductsPage = PageFactory.getMPProductsPage();

        WebDriver driver = new CBADashboardPage().getDriver();
        ArrayList<String> availableWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        testLog.info("------------------------------- Navigate to MarketPlace -----------------------------------");
        cbaDashboard.clickOnManageMenu();
        cbaDashboard.clickOnMarketPlaceOption();

        testLog.info("------------------------------- Navigate to Production Catalog -----------------------------------");
        availableWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        cbaDashboard.clickOnProductTab();

        testLog.info("------------------------------------- Navigate to Staging Catalog -------------------------------------");
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        cbaProducts.clickStagingProduct();

        testLog.info("------------------------------------- Create new Product -------------------------------------");
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        mpProductsPage.clickOnCreateProduct();

        driver = new CBADashboardPage().getDriver();
        availableWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        testLog.info("------------------------------------- Add Product Details -------------------------------------");
        // cbaProducts.createStagingProduct();
        cbaProducts.selectRevenueModel("One time");
        cbaProducts.clickOnListingInfo();
        cbaProducts.listingInfoAgreementDetails();

        availableWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        CBAMarketplacePage marketplace = PageFactory.getCBAMarketplace();
        marketplace.clickOnBuyProductBtn();
        marketplace.clickOnContinueBtn();

        //Validation
        if (!Assertions.compareBoolean(true, marketplace.checkAgreementInfoVisible(), "Legal option is display : ", testLog, driver)) {
            TestPassFlag = false;
        }

        Assert.assertTrue(TestPassFlag);
    }

    @Test(enabled = true, priority = 3, testName = "Remove Agreement Details from Listing info ", description = "I agree to the Terms of Service, Privacy and Refund policies message should not be display", groups = {"MPPhase2"})
    public void CBAMarketPlaceRemoveAgreementTestUI() throws Exception {
        loginMPPortalAsEOAdmin();

        //Move to Product section, delete product if exists, create new product
        CBADashboardPage cbaDashboard = PageFactory.getCBADashboard();
        CBAProductsPage cbaProducts = PageFactory.getCBAProducts();

        WebDriver driver = new CBADashboardPage().getDriver();
        ArrayList<String> availableWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        testLog.info("------------------------------- Navigate to MarketPlace -----------------------------------");
        cbaDashboard.clickOnManageMenu();
        cbaDashboard.clickOnMarketPlaceOption();

        testLog.info("------------------------------- Navigate to Production Catalog -----------------------------------");
        availableWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        cbaDashboard.clickOnProductTab();

        testLog.info("------------------------------------- Navigate to Staging Catalog -------------------------------------");
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        cbaProducts.clickStagingProduct();

        testLog.info("------------------------------------- Search Product and Click on Edit -------------------------------------");
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        //Validation
        if (!Assertions.compareBoolean(true, cbaProducts.searchAppInCatalog(), "App is available in the catalog : ", testLog, driver)) {
            testLog.info("--------------------------- App is not available in the catalog ----------------------------");
            TestPassFlag = false;
        }
        cbaProducts.clickOnEditButton();

        availableWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        cbaProducts.clickOnListingInfo();

        testLog.info("------------------------------------- Remove Legal Details -------------------------------------");
        availableWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        cbaProducts.removeLegalDetails();

        CBAMarketplacePage marketplace = PageFactory.getCBAMarketplace();
        marketplace.clickOnBuyProductBtn();
        marketplace.clickOnContinueBtn();

        //Validation
        if (!Assertions.compareBoolean(true, marketplace.checkAgreementInfoInvisible(), "Legal option is not display : ", testLog, driver)) {
            TestPassFlag = false;
        }

        Assert.assertTrue(TestPassFlag);
    }
}



