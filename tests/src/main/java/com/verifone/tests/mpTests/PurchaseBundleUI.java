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
import static com.verifone.tests.steps.mpPortal.Steps.navigateCBAHome;

public class PurchaseBundleUI extends BaseTest {

    private static Integer TimeOut = 4000;
    private static String fBundle = "Autocreated1";
    private static String sBundle = "autocreated full checkout";
    public static By fBPath = By.xpath("//a[text()='Autocreated1']");




    public void navigator() throws Exception {
        User EOAdminSupport = EntitiesFactory.getEntity("EOAdminSupport");
        String EOAdminSupportMail = EOAdminSupport.getUserName();
        String EOAdminSupportPwd = EOAdminSupport.getPassword();
        String EOAdminSupportAnsw = EOAdminSupport.getSecurityAnswer();
        navigateCBAHome();
        Steps.loginMPPortal(EOAdminSupportMail, EOAdminSupportPwd, EOAdminSupportAnsw);
    }


    @Test(enabled = true, priority=1, testName = "Verify no bundle from old test exist in MP", groups = { "MPRegression" }, alwaysRun = true)
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
        productsTab.clickMenuProductionCatalog();
        prodCatalog.clickLastPage();
        int ActualRow = prodCatalog.getTblRowProduct(fBundle);
        System.out.println(ActualRow);
        if(ActualRow!=0)
        {
            prodCatalog.clickMenuEditProduct(ActualRow);
            prodCatalog.clickMenuRemoveProduct(ActualRow);
            productsTab.clickMenuBundle();
            int ActualBundleRow = productsTabBundlePage.getTblRowBundle(fBundle);
            productsTabBundlePage.clickMenuEditBundle(ActualBundleRow);
            productsTabBundlePage.clickMenuDeleteBundle(ActualBundleRow);

            testLog.info("------------------------------------------------- Verify Confirmation First bundle deleted --------------------------------------------------");
            if (!Assertions.compareBoolean(true, productsTab.msgConfirmationText1().contains("deleted " + fBundle), "Delete Bundle confirmation message displayed as expected: ", testLog, driver)) {
                TestPassFlag = false;
            }
            Assert.assertTrue(TestPassFlag);
        }
    }
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

//		Click Product tab
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

        productsTabBundlePage.addBundle();
        productsTabBundlePage.clickListingInformation();
        productsTabBundlePage.fillTheFields("bla","bla","bla");

    }

}
