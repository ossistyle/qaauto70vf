package com.verifone.tests.mpTests;

import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.MPHomePage;
import com.verifone.pages.mpPages.ManageMarketplacePage;
import com.verifone.pages.mpPages.ReportsPage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.appUtils.MPUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.*;
import static com.verifone.tests.steps.mpPortal.Steps.mpEoNavigator;

public class ReportsUI extends BaseTest {

    File folder = new File("C:\\qaauto1\\feqaautomation\\tests\\src\\test\\resources\\reports");
    String destFolder = "C:\\qaauto1\\feqaautomation\\tests\\src\\test\\resources\\reports\\old";
    String reportName = "Invoice Details";

    @Test(enabled = true, priority = 1, testName = "Download reports", groups = {"MPRegression"}, alwaysRun = true)
    public void downloadReportsUI() throws Exception {
        mpEoNavigator();

        MPHomePage MPHomePage = (MPHomePage) PageFactory.getPage("MPHomePage");
        MPHomePage.clickHeaderManageMenu();
        MPHomePage.clickMarketplaceSubMenu();

        ManageMarketplacePage manageMarketplacePage = (ManageMarketplacePage) PageFactory.getPage("ManageMarketplacePage");
        manageMarketplacePage.clickTabReports();

        ReportsPage reportsPage = PageFactory.getReportsPage();
        reportsPage.downloadReport(1);

        boolean found = MPUtils.validateFile(folder, reportName, destFolder,true);
        Assert.assertTrue(found, "Downloaded document is not found");
    }
}
