package com.verifone.tests.mpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.MPHomePage;
import com.verifone.pages.mpPages.ManageMarketplacePage;
import com.verifone.pages.mpPages.ProductsTab;
import com.verifone.pages.mpPages.ProductsTabProductCatalogPage;
import com.verifone.tests.BaseTest;
import com.verifone.tests.steps.mpPortal.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import java.util.ArrayList;
import static com.verifone.pages.BasePage.testLog;
import static com.verifone.tests.steps.mpPortal.Steps.navigateCBAHome;

public class PurchaseBundleUI extends BaseTest {

    private static Integer TimeOut = 4000;
    private static String fBundle = "QA test 27-10";
    private static String sBundle = "autocreated full checkout";
    private By fBPath = By.xpath("//a[text()='QA test 27-10']");




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
        ProductsTab ProductsTab = (ProductsTab) PageFactory.getPage("ProductsTab");
        ProductsTab.clickMenuProductionCatalog();
        ProductsTabProductCatalogPage prodCatalog = (ProductsTabProductCatalogPage) PageFactory.getPage("ProductsTabProductCatalogPage");
        prodCatalog.inputSearchProduct(fBundle);
        Thread.sleep(TimeOut);
        prodCatalog.clickBtnSearchProduct();
        if(prodCatalog.verifyProductExist(fBPath))
        {
            ProductsTab.clickMenuTriggerSegmentGroup(0);
            int ActualRow = ProductsTab.getTblRowSegmentGroups(fBundle);
            System.out.println(ActualRow);
        }

    }

}
