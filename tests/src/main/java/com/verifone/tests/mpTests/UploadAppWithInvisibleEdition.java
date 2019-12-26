package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBADashboardPage;
import com.verifone.pages.mpPages.CBAMarketplacePage;
import com.verifone.pages.mpPages.CBAProductsPage;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.mpPortal.Steps;
import com.verifone.utils.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.ArrayList;

import static com.verifone.pages.BasePage.testLog;
import static com.verifone.tests.steps.mpPortal.Steps.*;

public class UploadAppWithInvisibleEdition extends BaseTest {
    private String productName;
    private WebDriver driver;
    private boolean TestPassFlag = true;
    private String saveFeedbackInfo = "Your changes have been saved.";

    @Test(enabled = true, priority = 1, testName = "Application visible on marketplace - false", description = "As a EO Admin, logIn into CBA MarketPlace and Mark Visible On MarketPlace option false")
    public void VisibilityOfAppInMarketPlaceFalseTestUI() throws Exception {
        loginMPPortalAsEOAdmin();

        CBADashboardPage cbaDashboard = PageFactory.getCBADashboard();
        CBAProductsPage cbaProducts = PageFactory.getCBAProducts();

        //get the invisible product name from the properties file
        productName = BaseTest.envConfig.getGroupInfo("MPInvisibleEdition");

        driver = cbaProducts.getDriver();

        //Navigate to manage >> Marketplace >> Product
        cbaDashboard.clickOnManageMenu();
        cbaDashboard.clickOnMarketPlaceOption();
        cbaDashboard.clickOnProductTab();

        //cbaProducts.isProductAvailable(productName);
        //Check availability of the product inside the production catalog.
        if (!cbaProducts.isProductAvailable(productName)) {
            testLog.info("------------------ Product is not available to use -----------------");
            throw new SkipException("Product is not available to use");
        }

        testLog.info("------------------ Navigate to Edit MarketPlacing Settings -----------------");
        //Edit MarketPlace settings >> Uncheck checkbox - Visibility on MarketPlace
        cbaProducts.editMarketPlaceSettings();

        //Check the state of checkbox - Visible on MarketPlace
        //If element is checked then uncheck it otherwise keep as it is.
        if (cbaProducts.checkVisibilityOfAppOnMarketPlace()) {
            testLog.info("------------------ Checkbox - Check Visibility on MarketPlace option  -----------------");
            cbaProducts.clickVisibilityOfAppOnMarketPlace();
        }

        cbaProducts.clickGeneralTabSaveBtn();

        testLog.info("------------------ Compare validation message -----------------");
        if (!Assertions.compareBoolean(true, cbaProducts.checkEditMarketPlaceSettingsInfo(saveFeedbackInfo), "Success message display : ", testLog, driver)) {
            TestPassFlag = false;
        }

        Assert.assertTrue(TestPassFlag);
    }

    @Test(enabled = true, priority = 2, testName = "Check Application Visibility On MarketPlace", description = "As a merchant, logIn into CBA MarketPlace. Application should not be display in marketplace.", dependsOnMethods = "VisibilityOfAppInMarketPlaceFalseTestUI")
    public void CheckAppVisibilityOnMarketPlaceFalseTestUI() throws Exception {
        /* Login in to CBA Marketplace*/
        loginCBA(createAssignUser());

        //Navigate to marketplace and search app
        CBAMarketplacePage marketplacePage = PageFactory.getCBAMarketplace();
        marketplacePage.searchForApp(productName);

        testLog.info("------------------ Check Application Existence In MarketPlace-----------------");
        //Check the existence of product in the market place
        if (!Assertions.compareBoolean(false, marketplacePage.isAppExistInMarketPlace(), "Application exist in marketplace : ", testLog, driver)) {
            testLog.info("------------------ Actual Result : Application display in marketplace -----------------");
            TestPassFlag = false;
        }

        testLog.info("------------------ Expected Result : Application should not be display in marketplace -----------------");
        Assert.assertTrue(TestPassFlag);
    }

    @Test(enabled = true, priority = 3, testName = "Application visible on marketplace - true", description = "As a EO Admin, logIn into CBA MarketPlace and Mark Visible On MarketPlace option true", dependsOnMethods = "VisibilityOfAppInMarketPlaceFalseTestUI")
    public void VisibilityOfAppInMarketPlaceTrueTestUI() throws Exception {
        loginMPPortalAsEOAdmin();

        CBADashboardPage cbaDashboard = PageFactory.getCBADashboard();
        CBAProductsPage cbaProducts = PageFactory.getCBAProducts();

        //Navigate to manage >> Marketplace >> Product
        cbaDashboard.clickOnManageMenu();
        cbaDashboard.clickOnMarketPlaceOption();
        cbaDashboard.clickOnProductTab();

        //cbaProducts.isProductAvailable(productName);
        //Check availability of the product inside the production catalog.
        if (!cbaProducts.isProductAvailable(productName)) {
            testLog.info("------------------ Product is not available to use -----------------");
            throw new SkipException("Product is not available to use");
        }

        testLog.info("------------------ Navigate to Edit MarketPlacing Settings -----------------");
        //Edit MarketPlace settings >> Uncheck checkbox - Visibility on MarketPlace
        cbaProducts.editMarketPlaceSettings();

        //Check the state of checkbox - Visible on MarketPlace
        //If element is unchecked then check it otherwise keep as it is.
        if (!cbaProducts.checkVisibilityOfAppOnMarketPlace()) {
            testLog.info("------------------ Checkbox - Uncheck Visibility on MarketPlace option -----------------");
            cbaProducts.clickVisibilityOfAppOnMarketPlace();
        }

        cbaProducts.clickGeneralTabSaveBtn();

        testLog.info("------------------ Compare validation message -----------------");
        if (!Assertions.compareBoolean(true, cbaProducts.checkEditMarketPlaceSettingsInfo(saveFeedbackInfo), "Success message display : ", testLog, driver)) {
            TestPassFlag = false;
        }

        Assert.assertTrue(TestPassFlag);
    }

    @Test(enabled = true, priority = 4, testName = "Check Application Visibility On MarketPlace", description = "As a merchant, logIn into CBA MarketPlace. Application should be display in marketplace.", dependsOnMethods = "VisibilityOfAppInMarketPlaceFalseTestUI")
    public void CheckAppVisibilityOnMarketPlaceTrueTestUI() throws Exception {
        /* Login in to CBA Marketplace*/
        loginCBA(createAssignUser());

        //Navigate to marketplace and search app
        CBAMarketplacePage marketplacePage = PageFactory.getCBAMarketplace();
        marketplacePage.searchForApp(productName);

        testLog.info("------------------ Check Application Existence In MarketPlace-----------------");
        //Check the existence of product in the market place
        if (!Assertions.compareBoolean(true, marketplacePage.isAppExistInMarketPlace(), "Application exist in marketplace : ", testLog, driver)) {
            testLog.info("------------------ Actual Result : Application is not display in marketplace -----------------");
            TestPassFlag = false;
        }

        testLog.info("------------------ Expected Result : Application should be display in marketplace -----------------");
        Assert.assertTrue(TestPassFlag);
    }
}
