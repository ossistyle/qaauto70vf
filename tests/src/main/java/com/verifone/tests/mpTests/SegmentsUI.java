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
import static com.verifone.tests.steps.mpPortal.Steps.mpEoNavigator;
import static com.verifone.tests.steps.mpPortal.Steps.navigateCBAHome;
//--------------------------------------------------------------------------

/**
 * Portal: Marketplace
 * This test set verify Marketplace Manager can login to Marketplace
 * View, Search, Edit and Delete Segment Groups
 * Add Segments to Segment Group
 * Associate Companies to Segment
 * Associate Products to Segment
 * Verify Merchant can see only Segment available Products
 * Delete all created entities after test.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class SegmentsUI extends BaseTest {

	private static Integer TimeOut = 2000;
	private static String Env = "https://verifoneausandbox.byappdirect.com/login";
	private static String MerchantEmail1 = "merchantforautomation01@getnada.com";
	private static String MerchantPassw1 = "Veri1234";
	private static String MerchantEmail2 = "merchant020719@getnada.com";
	private static String MerchantPassw2 = "Veri1234";
	private static String MerchantCompany1 = "merchantAutomation";
	private static String MerchantCompany2 = "merchant020719";
	private static String SegmentGroupName = "Segment Group 1";
	private static String SegmentName1 = "Seg1";
	private static String SegmentName2 = "Seg2";
	private static String SegmentCode1 = "code1";
	private static String SegmentCode2 = "code2";
	private static String Product1 = "test product seba";
	private static String Product2 = "NewBroccoli";


	// In case segment from old test exist in MP need to delete it before test start
    @Test(enabled = true, priority=1, testName = "Delete segment before test", groups = { "MPRegression" }, alwaysRun = true)
    public void DeleteSegmentIfExistUI() throws Exception {
        WebDriver driver = new MPHomePage().getDriver();
        Boolean TestPassFlag = true;

        mpEoNavigator();
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
        ProductsTab.clickMenuSegmentGroups();

        Thread.sleep(TimeOut + 1000);
        int ActualRow = ProductsTab.getTblRowSegmentGroups(SegmentGroupName);

        if(ActualRow>0){
        while(ActualRow>0) {
            ProductsTab.clickMenuTriggerSegmentGroup(ActualRow);
            Thread.sleep(TimeOut);
            ProductsTab.clickMenuContextDeleteSegment(String.valueOf(ActualRow));

            testLog.info("------------------------------------------------- Verify Delete dialog visible before test --------------------------------------------------");
            if (!Assertions.compareBoolean(true, ProductsTab.existsDlgDeleteSegmentGroup(), "Delete Segment Group dialog displayed as expected: ", testLog, driver)) {
                TestPassFlag = false;
            }
            ProductsTab.clickDlgDeleteSegmentGroupBtnYes();

            testLog.info("------------------------------------------------- Verify Confirmation message before test --------------------------------------------------");
            if (!Assertions.compareBoolean(true, ProductsTab.msgConfirmationText().contains("deleted"), "Delete Segment Group confirmation message displayed as expected: ", testLog, driver)) {
                TestPassFlag = false;
            }
            ActualRow = ProductsTab.getTblRowSegmentGroups(SegmentGroupName);
            Assert.assertTrue(TestPassFlag);
        }}
        else{
            testLog.info("------------------------------------------------- Check before test didn't find existing segment --------------------------------------------------");
            Assert.assertTrue(TestPassFlag);
        }
    }

		@Test(enabled = true, priority=2, testName = "Add Segment Group", groups = { "MPRegression" }, alwaysRun = true)
        public void AddSegmentGroupUI() throws Exception {
            WebDriver driver = new MPHomePage().getDriver();
            Boolean TestPassFlag = true;

            mpEoNavigator();
            ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            MPHomePage MPHomePage = (MPHomePage) PageFactory.getPage("MPHomePage");

            testLog.info("------------------------------------------------- Navigate to My Profile page -------------------------------------------------");

            MPHomePage.clickHeaderManageMenu();
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
            ProductsTab.clickMenuSegmentGroups();

    //		Validation
            if (!Assertions.compareBoolean(true, ProductsTab.existsTitleSegmentGroups(), "Segment Groups title displayed as expected: ", testLog, driver)){
                TestPassFlag = false;
            }

            if (!Assertions.compareBoolean(true, ProductsTab.existsBtnCreateSegmentGroups(), "Create Segment Group button displayed as expected: ", testLog, driver)){
                TestPassFlag = false;
            }

            testLog.info("-----------------------------Create new Segment Folder(Group) -------------------------------------------------");

            ProductsTab.clickBtnCreateSegmentGroup();

            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            CreateSegmentGroupPage CreateSegmentGroupPage = (CreateSegmentGroupPage) PageFactory.getPage("CreateSegmentGroupPage");

            if (!Assertions.compareBoolean(true, CreateSegmentGroupPage.existsTitleCreateSegmentGroup(), "Create Segment Group title displayed as expected: ", testLog, driver)){
                TestPassFlag = false;
            }

            CreateSegmentGroupPage.inputSegmentGroupName(SegmentGroupName);
            CreateSegmentGroupPage.clickBtnSave();


            testLog.info("------------------------------------------------- Verify Confirmation message -------------------------------------------------");
            Thread.sleep(TimeOut );
            if (!Assertions.compareValue("You created the "+SegmentGroupName+" segment folder. To add a segment to this folder, click Create Segment.", CreateSegmentGroupPage.msgConfirmationText(), "Confirmation message displayed as expected: ", testLog, driver)) {
                TestPassFlag = false;
            }

            testLog.info("------------------------------------------------- Verify Create segment buttons -------------------------------------------------");
            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            EditSegmentGroupPage EditSegmentGroupPage = (EditSegmentGroupPage) PageFactory.getPage("EditSegmentGroupPage");
            if (!Assertions.compareBoolean(true, EditSegmentGroupPage.existsBtnCreateSegment(), "Create Segment button found: ", testLog, driver)){
                TestPassFlag = false;
            }

            testLog.info("------------------------------------------------- Verify Segment title -------------------------------------------------");

            if (!Assertions.compareBoolean(true, EditSegmentGroupPage.existsTitleSegment(), "Title Segment displayed as expected", testLog, driver)){
                TestPassFlag = false;
            }

            testLog.info("------------------------------------------------- Verify Segment folder name -------------------------------------------------");

            if (!Assertions.compareBoolean(true, EditSegmentGroupPage.existsTitleCreateSegmentGroup(), "Name of Segment folder displayed as expected", testLog, driver)){
                TestPassFlag = false;
            }

            testLog.info("------------------------------------------------- Verify Segment Filter build -------------------------------------------------");

            if (!Assertions.compareBoolean(true, EditSegmentGroupPage.existsFilterBuild(), "Title Segments filter build displayed as expected", testLog, driver)){
                TestPassFlag = false;
            }

            testLog.info("------------------------------------------------- Verify Segment Manual build -------------------------------------------------");

            if (!Assertions.compareBoolean(true, EditSegmentGroupPage.existsManualBuild(), "Title Segments manual build displayed as expected", testLog, driver)){
                TestPassFlag = false;
            }

            Assert.assertTrue(TestPassFlag);
        }


        @Test(enabled = true, priority=3, testName = "Edit Segment Group, Add Segments", groups = { "MPRegression" }, alwaysRun = true)

        public void EditSegmentGroupAddSegmentsUI() throws Exception {
            WebDriver driver = new MPHomePage().getDriver();
            Boolean TestPassFlag = true;


            mpEoNavigator();
            ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            MPHomePage MPHomePage = (MPHomePage) PageFactory.getPage("MPHomePage");

            testLog.info("------------------------------------------------- Navigate to My Profile page -------------------------------------------------");

            MPHomePage.clickHeaderManageMenu();
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
            ProductsTab.clickMenuSegmentGroups();

            Thread.sleep(TimeOut + 1000);
            int ActualRow = ProductsTab.getTblRowSegmentGroups(SegmentGroupName);

            ProductsTab.clickMenuTriggerSegmentGroup(ActualRow);
            Thread.sleep(TimeOut);

            testLog.info("------------------------------------------------- Edit Segmant Group -------------------------------------------------");

            ProductsTab.clickMenuContextEditSegment(String.valueOf(ActualRow));

            testLog.info("------------------------------------------------- Verify Create segment button -------------------------------------------------");
            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            EditSegmentGroupPage EditSegmentGroupPage = (EditSegmentGroupPage) PageFactory.getPage("EditSegmentGroupPage");
            if (!Assertions.compareBoolean(true, EditSegmentGroupPage.existsBtnCreateSegment(), "Create Segment button displayed as expected", testLog, driver)){
                TestPassFlag = false;
            }

            testLog.info("------------------------------------------------- Verify Segment title -------------------------------------------------");

            if (!Assertions.compareBoolean(true, EditSegmentGroupPage.existsTitleSegment(), "Title Segment displayed as expected", testLog, driver)){
                TestPassFlag = false;
            }

            testLog.info("------------------------------------------------- Verify Segment Filter build -------------------------------------------------");

            if (!Assertions.compareBoolean(true, EditSegmentGroupPage.existsFilterBuild(), "Title Segments filter build displayed as expected", testLog, driver)){
                TestPassFlag = false;
            }

            testLog.info("------------------------------------------------- Verify Segment Manual build -------------------------------------------------");

            if (!Assertions.compareBoolean(true, EditSegmentGroupPage.existsManualBuild(), "Title Segments manual build displayed as expected", testLog, driver)){
                TestPassFlag = false;
            }

            testLog.info("------------------------------------------------- Update Segment Group Name -------------------------------------------------");
            String SegGroupName = EditSegmentGroupPage.GetSegmentGroupName();
            EditSegmentGroupPage.UpdateSegmentGroupName(SegGroupName + " Updated");
            EditSegmentGroupPage.createSegmentManual();

            testLog.info("------------------------------------------------- Create Segment Page -------------------------------------------------");
            testLog.info("------------------------------------------------- Create Segment 1 ----------------------------------------------------");
            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            CreateSegmentPage CreateSegmentPage = (CreateSegmentPage) PageFactory.getPage("CreateSegmentPage");

            CreateSegmentPage.inputSegmentName(SegmentName1);
            CreateSegmentPage.inputSegmentCode(SegmentCode1);
            CreateSegmentPage.clickBtnSave();

            Thread.sleep(TimeOut + 1000);

            EditSegmentGroupPage.clickBtnCreateSegment();
            EditSegmentGroupPage.clickOnManualSegment();



            testLog.info("------------------------------------------------- Create Segment 2 ----------------------------------------------------");
            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            CreateSegmentPage = (CreateSegmentPage) PageFactory.getPage("CreateSegmentPage");

            CreateSegmentPage.inputSegmentName(SegmentName2);
            CreateSegmentPage.inputSegmentCode(SegmentCode2);
            CreateSegmentPage.clickBtnSave();

            Thread.sleep(TimeOut + 1000);

            testLog.info("------------------------------------------------- Verify Segments created -------------------------------------------------");

            if (!Assertions.compareBoolean(true, EditSegmentGroupPage.getTblSegments().contains(SegmentName1), SegmentName1+" created", testLog, driver)){
                TestPassFlag = false;
            }
            if (!Assertions.compareBoolean(true, EditSegmentGroupPage.getTblSegments().contains(SegmentName2), SegmentName2+" created", testLog, driver)){
                TestPassFlag = false;
            }
            if (!Assertions.compareBoolean(true, EditSegmentGroupPage.getTblSegments().contains(SegmentCode1), SegmentCode1+" created", testLog, driver)){
                TestPassFlag = false;
            }
            if (!Assertions.compareBoolean(true, EditSegmentGroupPage.getTblSegments().contains(SegmentCode2), SegmentCode2+" created", testLog, driver)){
                TestPassFlag = false;
            }
            Assert.assertTrue(TestPassFlag);
        }
        @Test(enabled = true, priority=4, testName = "Edit Segment, Add Company", groups = { "MPRegression" }, alwaysRun = true)

        public void EditSegmentAddCompanyUI() throws Exception {
            WebDriver driver = new MPHomePage().getDriver();
            Boolean TestPassFlag = true;

            mpEoNavigator();
            ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            MPHomePage MPHomePage = (MPHomePage) PageFactory.getPage("MPHomePage");

            testLog.info("------------------------------------------------- Navigate to My Profile page -------------------------------------------------");

            MPHomePage.clickHeaderManageMenu();
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
            ProductsTab.clickMenuSegmentGroups();

            Thread.sleep(TimeOut + 1000);
            int ActualRow = ProductsTab.getTblRowSegmentGroups(SegmentGroupName);

            ProductsTab.clickMenuTriggerSegmentGroup(ActualRow);
            Thread.sleep(TimeOut);

            testLog.info("------------------------------------------------- Edit Segmant Group -------------------------------------------------");

            ProductsTab.clickMenuContextEditSegment(String.valueOf(ActualRow));

            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            EditSegmentGroupPage EditSegmentGroupPage = (EditSegmentGroupPage) PageFactory.getPage("EditSegmentGroupPage");

            ActualRow = EditSegmentGroupPage.getTblRowSegments(SegmentName1);

            testLog.info("------------------------------------------------- Edit Segment --------------------------------------------------------");

            EditSegmentGroupPage.clickMenuTriggerSegment(ActualRow);
            EditSegmentGroupPage.clickMenuContextEditSegment(String.valueOf(ActualRow));

            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            EditSegmentPage EditSegmentPage = (EditSegmentPage) PageFactory.getPage("EditSegmentPage");

            testLog.info("------------------------------------------------- Verify Edit segment title -------------------------------------------------");
            if (!Assertions.compareBoolean(true, EditSegmentPage.existsTitleEditSegment(), "Edit segment title displayed as expected: ", testLog, driver)) {
                TestPassFlag = false;
            }
            testLog.info("------------------------------------------------- Verify Companies Table-------------------------------------------------");
            if (!Assertions.compareBoolean(true, EditSegmentPage.existsTblCompanies(), "Companies table displayed as expected: ", testLog, driver)) {
                TestPassFlag = false;
            }

            testLog.info("------------------------------------------------- Search for Company ----------------------------------------------------");
            EditSegmentPage.inputSearchCompany(MerchantCompany1);
            Thread.sleep(TimeOut);
            EditSegmentPage.clickBtnSearchCompany();

            testLog.info("----------------------------------------------------- Select Company ----------------------------------------------------");
            Thread.sleep(TimeOut + 1000);
            EditSegmentPage.clickChkBoxCompany();

            testLog.info("------------------------------------------------- Update Segmant Name----------------------------------------------------");
            String n = EditSegmentPage.GetSegmentName();
            Thread.sleep(TimeOut);
            EditSegmentPage.inputSegmentName(n+" Updated");
            Thread.sleep(TimeOut);

            testLog.info("------------------------------------------------- Update Segmant Code----------------------------------------------------");
            String c = EditSegmentPage.GetSegmentCode();
            EditSegmentPage.inputSegmentCode(c+"_updated");

            testLog.info("--------------------------------------------------------- Save ----------------------------------------------------------");
            EditSegmentPage.clickBtnSave();

            testLog.info("------------------------------------------------- Verify Confirmation Dialog-------------------------------------------------");
            if (!Assertions.compareBoolean(true, EditSegmentPage.existsBtnConfirm(), "Confirmation dialog displayed: ", testLog, driver)) {
                TestPassFlag = false;
            }
            EditSegmentPage.clickBtnConfirm();
            Thread.sleep(TimeOut + 4000);

            testLog.info("------------------------------------------------- Update second segment-------------------------------------------------");
            testLog.info("------------------------------------------------- Edit Segmant Group -------------------------------------------------");

            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            EditSegmentGroupPage = (EditSegmentGroupPage) PageFactory.getPage("EditSegmentGroupPage");

            ActualRow = EditSegmentGroupPage.getTblRowSegments(SegmentName2);

            testLog.info("------------------------------------------------- Edit Segment --------------------------------------------------------");

            EditSegmentGroupPage.clickMenuTriggerSegment(ActualRow);
            EditSegmentGroupPage.clickMenuContextEditSegment(String.valueOf(ActualRow));

            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            EditSegmentPage = (EditSegmentPage) PageFactory.getPage("EditSegmentPage");

            testLog.info("------------------------------------------------- Search for Company ----------------------------------------------------");
            EditSegmentPage.inputSearchCompany(MerchantCompany2);
            Thread.sleep(TimeOut);
            EditSegmentPage.clickBtnSearchCompany();

            testLog.info("----------------------------------------------------- Select Company ----------------------------------------------------");
            Thread.sleep(TimeOut + 1000);
            EditSegmentPage.clickChkBoxCompany();

            testLog.info("--------------------------------------------------------- Save ----------------------------------------------------------");
            EditSegmentPage.clickBtnSave();

            testLog.info("------------------------------------------------- Verify Confirmation Dialog-------------------------------------------------");
            if (!Assertions.compareBoolean(true, EditSegmentPage.existsBtnConfirm(), "Confirmation dialog displayed as expected: ", testLog, driver)) {
                TestPassFlag = false;
            }
            EditSegmentPage.clickBtnConfirm();
            Thread.sleep(TimeOut + 4000);
            Assert.assertTrue(TestPassFlag);
        }
        @Test(enabled = true, priority=5, testName = "Associate Product to Segment", groups = { "MPRegression" }, alwaysRun = true)

        public void AssociateProductToSegmentUI() throws Exception {
            WebDriver driver = new MPHomePage().getDriver();
            Boolean TestPassFlag = true;

            mpEoNavigator();
            ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            MPHomePage MPHomePage = (MPHomePage) PageFactory.getPage("MPHomePage");

            testLog.info("------------------------------------------------- Navigate to My Profile page -------------------------------------------------");

            MPHomePage.clickHeaderManageMenu();
            MPHomePage.clickMarketplaceSubMenu();

            testLog.info("------------------------------------------------- Manage Marketplace page -------------------------------------------------");

            Thread.sleep(TimeOut + 1000);
            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            ManageMarketplacePage ManageMarketplacePage = (ManageMarketplacePage) PageFactory.getPage("ManageMarketplacePage");

    //		Click Product tab
            ManageMarketplacePage.clickTabProduct();

            testLog.info("------------------------------------------------- Search for Product----- -------------------------------------------------");
            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            ProductsTabProductCatalogPage ProductsTabProductCatalogPage = (ProductsTabProductCatalogPage) PageFactory.getPage("ProductsTabProductCatalogPage");

            ProductsTabProductCatalogPage.inputSearchProduct(Product1);
            ProductsTabProductCatalogPage.clickBtnSearchProduct();

            testLog.info("----------------------------------------------- Edit Marketplace Settings ----------------------------------------------------");
            Thread.sleep(TimeOut + 1000);
            ProductsTabProductCatalogPage.clickBtnConfigProduct();
            ProductsTabProductCatalogPage.clickMenuEditMarketplaceSettings();
            Thread.sleep(TimeOut+ 4000);

            testLog.info("------------------------------------------------- Open Product tab ------------------------------------------------------------");
            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            ProductSettingsProductsTab ProductSettingsProductsTab = (ProductSettingsProductsTab) PageFactory.getPage("ProductSettingsProductsTab");

            Thread.sleep(TimeOut);
            ProductSettingsProductsTab.clickMenuProducts();

            testLog.info("------------------------------------------------- Select Segment from Segments table--------------------------------------------");
            Thread.sleep(TimeOut);
            ProductSettingsProductsTab.getTblGroupSegmentsRowByText(SegmentGroupName +" Updated");
            ProductSettingsProductsTab.getTblSegmentsRowByText("Select All");
            ProductSettingsProductsTab.getTblSegmentsRowByText("Deselect All");
            ProductSettingsProductsTab.getTblSegmentsRowByText(SegmentName1+" Updated");
            ProductSettingsProductsTab.clickBtnSave();
            Thread.sleep(TimeOut+ 4000);

            testLog.info("------------------------------------------------- Verify Confirmation message ------- -------------------------------------------");
            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            ProductsTab ProductsTab = (ProductsTab) PageFactory.getPage("ProductsTab");
            if (!Assertions.compareBoolean(true, ProductsTab.msgConfirmationText1().contains("Your changes have been saved"), "Confirmation message displayed as expected: ", testLog, driver)) {
                TestPassFlag = false;
            }
            testLog.info("------------------------------------------------- Configure second Product -------------------------------------------------");

            Thread.sleep(TimeOut + 1000);
            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            ManageMarketplacePage = (ManageMarketplacePage) PageFactory.getPage("ManageMarketplacePage");

    //		Click Product tab
            ManageMarketplacePage.clickTabProduct();

            testLog.info("------------------------------------------------- Search for Product----- -------------------------------------------------");
            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            ProductsTabProductCatalogPage = (ProductsTabProductCatalogPage) PageFactory.getPage("ProductsTabProductCatalogPage");

            ProductsTabProductCatalogPage.inputSearchProduct(Product2);
            ProductsTabProductCatalogPage.clickBtnSearchProduct();

            testLog.info("----------------------------------------------- Edit Marketplace Settings ----------------------------------------------------");
            Thread.sleep(TimeOut + 1000);
            ProductsTabProductCatalogPage.clickBtnConfigProduct();
            ProductsTabProductCatalogPage.clickMenuEditMarketplaceSettings();
            Thread.sleep(TimeOut+ 4000);

            testLog.info("------------------------------------------------- Open Product tab ------------------------------------------------------------");
            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            ProductSettingsProductsTab = (ProductSettingsProductsTab) PageFactory.getPage("ProductSettingsProductsTab");

            Thread.sleep(TimeOut);
            ProductSettingsProductsTab.clickMenuProducts();

            testLog.info("------------------------------------------------- Select Segment from Segments table--------------------------------------------");
            Thread.sleep(TimeOut);
            ProductSettingsProductsTab.getTblGroupSegmentsRowByText(SegmentGroupName+" Updated");
            ProductSettingsProductsTab.getTblSegmentsRowByText("Select All");
            ProductSettingsProductsTab.getTblSegmentsRowByText("Deselect All");
            ProductSettingsProductsTab.getTblSegmentsRowByText(SegmentName2);
            ProductSettingsProductsTab.clickBtnSave();
            Thread.sleep(TimeOut+ 4000);

            testLog.info("------------------------------------------------- Verify Confirmation message ------- -------------------------------------------");
            availableWindows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(availableWindows.get(0));
            ProductsTab = (ProductsTab) PageFactory.getPage("ProductsTab");
            if (!Assertions.compareBoolean(true, ProductsTab.msgConfirmationText1().contains("Your changes have been saved"), "Confirmation message displayed as expected: ", testLog, driver)) {
                TestPassFlag = false;
            }
            Assert.assertTrue(TestPassFlag);
        }
	@Test(enabled = true, priority=6, testName = "Merchant login and Search Products of his and other Segments", groups = { "MPRegression" }, alwaysRun = true)

	public void MerchantSearchProductUI() throws Exception {
		//navigation for merchant one
		WebDriver driver = new MPHomePage().getDriver();
		Boolean TestPassFlag = true;
		Steps.loginMPPortal(MerchantEmail1, MerchantPassw1, "", Env);


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MPHomePage MPHomePage = (MPHomePage) PageFactory.getPage("MPHomePage");

		testLog.info("------------------------------------------------- Click on Marketplace menu --------------------------------------------------");
		MPHomePage.clickMarketplaceMenu();
		Thread.sleep(TimeOut+ 4000);

		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MarketplacePage MarketplacePage = (MarketplacePage) PageFactory.getPage("MarketplacePage");

		testLog.info("------------------------------------------------- Search for Product from his Segment --------------------------------------------------");
		MarketplacePage.inputSearchProduct(Product1);
		MarketplacePage.clickBtnSearchProduct();
		Thread.sleep(TimeOut+ 4000);

		testLog.info("------------------------------------------------- Verify Product visible --------------------------------------------------");
		if (!Assertions.compareBoolean(true, MarketplacePage.existsProductByName(Product1), "Product "+Product1+" found as expected: ", testLog, driver)) {
            int k =0;
            while(k<6){
                driver.navigate().refresh();
                MarketplacePage.inputSearchProduct(Product1);
                Thread.sleep(TimeOut);
                MarketplacePage.clickBtnSearchProduct();
                if(MarketplacePage.existsProductByName(Product1)){
                    break;
                }
                else{TestPassFlag = false;}
                k++;
                Thread.sleep(30000);
            }
		}
		testLog.info("------------------------------------------------- Search for Product from other Segment --------------------------------------------------");
		MarketplacePage.inputSearchProduct(Product2);
		MarketplacePage.clickBtnSearchProduct();
		Thread.sleep(TimeOut+ 4000);

		testLog.info("------------------------------------------------- Verify Product NOT visible --------------------------------------------------");
		if (!Assertions.compareBoolean(false, MarketplacePage.existsProductByName(Product2), "Not available Product "+Product2+" visibility: ", testLog, driver)) {
			TestPassFlag = false;
		}
		Assert.assertTrue(TestPassFlag);
	}

	@Test(enabled = true, priority=7, testName = "Second Merchant login and Search Products of his and other Segments", groups = { "MPRegression" }, alwaysRun = true)

	public void MerchantSearchProduct2UI() throws Exception {
		WebDriver driver = new MPHomePage().getDriver();
		Boolean TestPassFlag = true;
		Steps.loginMPPortal(MerchantEmail2, MerchantPassw2, "", Env);


		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MPHomePage MPHomePage = (MPHomePage) PageFactory.getPage("MPHomePage");
		testLog.info("------------------------------------------------- Click on Marketplace menu --------------------------------------------------");
		MPHomePage.clickMarketplaceMenu();
		Thread.sleep(TimeOut+ 4000);

		availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(0));
		MarketplacePage MarketplacePage = (MarketplacePage) PageFactory.getPage("MarketplacePage");

		testLog.info("------------------------------------------------- Search for Product from his Segment --------------------------------------------------");
		MarketplacePage.inputSearchProduct(Product2);
		MarketplacePage.clickBtnSearchProduct();
		Thread.sleep(TimeOut+ 4000);

		testLog.info("------------------------------------------------- Verify Product visible --------------------------------------------------");
		if (!Assertions.compareBoolean(true, MarketplacePage.existsProductByName(Product2), "Product "+Product2+" found as expected: ", testLog, driver)) {
		    int k =0;
		    while(k<6){
                driver.navigate().refresh();
                //MarketplacePage.inputSearchProduct(Product2);
                Thread.sleep(TimeOut);
                MarketplacePage.clickBtnSearchProduct();
                if(MarketplacePage.existsProductByName(Product2)){
                    break;
                }
                else{TestPassFlag = false;}
                k++;
                Thread.sleep(30000);
            }
		}
		testLog.info("------------------------------------------------- Search for Product from other Segment --------------------------------------------------");
		MarketplacePage.inputSearchProduct(Product1);
		MarketplacePage.clickBtnSearchProduct();
		Thread.sleep(TimeOut+ 4000);

		testLog.info("------------------------------------------------- Verify Product NOT visible --------------------------------------------------");
		if (!Assertions.compareBoolean(false, MarketplacePage.existsProductByName(Product1), "Not available Product "+Product1+" visibility: ", testLog, driver)) {
			TestPassFlag = false;
		}
		Assert.assertTrue(TestPassFlag);
	}
	@Test(enabled = true, priority=8, testName = "Delete Segment Group", groups = { "MPRegression" }, alwaysRun = true)

	public void DeleteSegmentGroupUI() throws Exception {
		WebDriver driver = new MPHomePage().getDriver();
		Boolean TestPassFlag = true;

        mpEoNavigator();
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
		ProductsTab.clickMenuSegmentGroups();

		Thread.sleep(TimeOut + 1000);
		int ActualRow = ProductsTab.getTblRowSegmentGroups(SegmentGroupName);
		System.out.println(ActualRow);

		ProductsTab.clickMenuTriggerSegmentGroup(ActualRow);
		Thread.sleep(TimeOut);
		ProductsTab.clickMenuContextDeleteSegment(String.valueOf(ActualRow));

		testLog.info("------------------------------------------------- Verify Delete dialog visible --------------------------------------------------");
		if (!Assertions.compareBoolean(true, ProductsTab.existsDlgDeleteSegmentGroup(), "Delete Segment Group dialog displayed as expected: ", testLog, driver)) {
			TestPassFlag = false;
		}
		ProductsTab.clickDlgDeleteSegmentGroupBtnYes();

		testLog.info("------------------------------------------------- Verify Confirmation message --------------------------------------------------");
		if (!Assertions.compareBoolean(true, ProductsTab.msgConfirmationText().contains("deleted"), "Delete Segment Group confirmation message displayed as expected: ", testLog, driver)) {
			TestPassFlag = false;
		}
		Assert.assertTrue(TestPassFlag);
	}
}