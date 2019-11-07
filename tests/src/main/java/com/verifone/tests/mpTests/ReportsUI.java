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

import static com.verifone.pages.BasePage.testLog;
import static com.verifone.tests.steps.mpPortal.Steps.mpEoNavigator;

public class ReportsUI extends BaseTest {

    String reportName1 = "Invoice Details";
    String reportName2 = "Payment Details";
    String reportName3 = "Transactions";

    @Test(enabled = true, priority = 1, testName = "Download reports", groups = {"MPRegression"}, alwaysRun = true)
    public void downloadReportsUI() throws Exception {
        String dir = BaseTest.envConfig.getDestinationFolder();
        File folder = new File(dir);
        String backupDir = dir+"\\old";
        mpEoNavigator();

        MPHomePage MPHomePage = (MPHomePage) PageFactory.getPage("MPHomePage");
        MPHomePage.clickHeaderManageMenu();
        MPHomePage.clickMarketplaceSubMenu();

        ManageMarketplacePage manageMarketplacePage = (ManageMarketplacePage) PageFactory.getPage("ManageMarketplacePage");
        manageMarketplacePage.clickTabReports();

        ReportsPage reportsPage = PageFactory.getReportsPage();

//download reports
        String response1 = reportsPage.downloadReport(1);
        testLog.info(response1);
        String response2 = reportsPage.downloadReport(2);
        testLog.info(response2);
        String response3 = reportsPage.downloadReport(3);
        testLog.info(response3);

//move to backup file
        boolean found = MPUtils.validateFile(folder, reportName1, backupDir,true);
        Assert.assertTrue(found, "Downloaded document is not found: " + reportName1);

        boolean found2 = MPUtils.validateFile(folder, reportName2, backupDir,true);
        Assert.assertTrue(found2, "Downloaded document is not found: " + reportName2);

        boolean found3 = MPUtils.validateFile(folder, reportName3, backupDir,true);
        Assert.assertTrue(found3, "Downloaded document is not found: " + reportName3);
    }
}
