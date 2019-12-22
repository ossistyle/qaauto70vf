package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBADashboardPage;
import com.verifone.pages.mpPages.CBAProductsPage;
import com.verifone.pages.mpPages.MPProductsPage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Assertions;
import com.verifone.utils.appUtils.MPUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.verifone.pages.BasePage.testLog;
import static com.verifone.tests.steps.mpPortal.Steps.loginMPPortalAsEOAdmin;

public class UploadAppsWithMissingDataTestUI extends BaseTest {

    private boolean TestPassFlag = true;

    @Test(enabled = true, priority = 0, testName = "Check App Presence in Production/Staging Catalog", description = "LogIn into CBA MarketPlace and Remove Product from the Catalog if it is present", groups = {"MPPhase2"})
    public void VerifyAppInProductionCatalogTestUI() throws Exception {
        loginMPPortalAsEOAdmin();

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
        if (cbaProducts.isProductAvailable()) {
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

    @Test(enabled = true, priority = 1, testName = "Apps with missing mandatory data", description = "Can't Upload Apps with missing mandatory data", groups = {"MPPhase2"})
    public void VerifyAppWithMissingMandatoryDataTestUI() throws Exception {
        loginMPPortalAsEOAdmin();

        WebDriver driver = new CBADashboardPage().getDriver();
        ArrayList<String> availableWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        CBADashboardPage cbaDashboard = PageFactory.getCBADashboard();
        CBAProductsPage cbaProducts = PageFactory.getCBAProducts();
        MPProductsPage mpProductsPage = PageFactory.getMPProductsPage();

        testLog.info("------------------------------- Navigate to MarketPlace -----------------------------------");
        cbaDashboard.clickOnManageMenu();
        cbaDashboard.clickOnMarketPlaceOption();

        availableWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        testLog.info("------------------------------- Navigate to Product Tab -----------------------------------");
        cbaDashboard.clickOnProductTab();

        testLog.info("------------------------------------- Navigate to Staging Catalog -------------------------------------");
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        cbaProducts.clickStagingProduct();

        testLog.info("------------------------------------- Create new Product -------------------------------------");
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        mpProductsPage.clickOnCreateProduct();

        testLog.info("------------------------------------- Add Revenue Model -------------------------------------");
        // cbaProducts.createStagingProduct();
        cbaProducts.selectRevenueModel("Recurring");

        testLog.info("------------------------------------- Listing info -------------------------------------");
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        mpProductsPage.clickOnListingInfo();
        mpProductsPage.setListingInfoText();
        mpProductsPage.clickOnSaveBtn();

        if (!Assertions.compareBoolean(true, mpProductsPage.compareErrorMessage(MPUtils.missingMandatoryErrorMsg), " Listing info - Error message is display :", testLog, driver)) {
            testLog.info("---------------------------------------- Error Message is not display as expected. ----------------------------------------------");
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);

        testLog.info("------------------------------------- Profile -------------------------------------");
        mpProductsPage.clickOnProfile();
        mpProductsPage.clickOnLeaveThisPage();

        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        mpProductsPage.setProfileProduct();
        mpProductsPage.clickOnSaveBtn();

        if (!Assertions.compareBoolean(true, mpProductsPage.profileErrorMessage(MPUtils.missingMandatoryErrorMsg), "Profile - Error message is display :", testLog, driver)) {
            testLog.info("---------------------------------------- Error Message is not display as expected. ----------------------------------------------");
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);

        testLog.info("------------------------------------- Edition -------------------------------------");
        mpProductsPage.clickEdition();
        mpProductsPage.clickOnLeaveThisPage();

        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        mpProductsPage.clickOnFreeEdition();
        mpProductsPage.removeEditionCode();
        mpProductsPage.clickOnSavePlanBtn();

        if (!Assertions.compareBoolean(true, mpProductsPage.editionErrorMessage(MPUtils.missingMandatoryErrorMsg), "Edition - Error message is display :", testLog, driver)) {
            testLog.info("---------------------------------------- Error Message is not display as expected. ----------------------------------------------");
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);

        testLog.info("------------------------------------- Edit Authentication -----------------------------------");
        mpProductsPage.clickOnEditAuthentication();
        mpProductsPage.clickOnLeaveThisPage();

        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        mpProductsPage.clickOnAuthenticationMethod();
        mpProductsPage.clickOnSaveAuthBtn();

        if (!Assertions.compareBoolean(true, mpProductsPage.authenticationErrorMessage("Required"), "Authentication - Error message is display :", testLog, driver)) {
            testLog.info("---------------------------------------- Error Message is not display as expected. ----------------------------------------------");
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);

        testLog.info("------------------------------------- Platform -----------------------------------");
        mpProductsPage.clickOnPlatforms();
        mpProductsPage.clickOnLeaveThisPage();
        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        mpProductsPage.clickOnAddPlatform();
        mpProductsPage.selectVerifoneDevice();
        mpProductsPage.clickOnSelectBtn();

        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        mpProductsPage.clickOnAddProductVersion();

        availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));
        mpProductsPage.setDetailsInPlatform();
        mpProductsPage.clickOnSavePlatformBtn();

        if (!Assertions.compareBoolean(true, mpProductsPage.productVersionErrorMessage("There were some errors in your product version details. Please correct them below"), "Product Version - Error message is display :", testLog, driver)) {
            testLog.info("---------------------------------------- Error Message is not display as expected. ----------------------------------------------");
            TestPassFlag = false;
        }
        Assert.assertTrue(TestPassFlag);
    }
}
