package com.verifone.pages.vhqPages;

import com.verifone.pages.BasePage;
import com.verifone.utils.appUtils.MPUtils;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class VHQDeviceSearch extends BasePage {
    private final static String url = "";
    private final static String title = "";
    // private DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy ");
    // private String currentDate = dateFormat.format(new Date());

    public VHQDeviceSearch() {
        super(url, title);
    }

    //private By serialNum = By.linkText("401-686-709");
    private By linkSerialNumber = By.xpath("//*[@id='row0Devicejqxgrid']/child::div[2]//div[1]");
    private By download = By.id("Download_HistroytabLi");
    private By divFirstRow = By.id("row0jqxgridDownloadJobProfil");
    private By divSecondRow = By.id("row1jqxgridDownloadJobProfil");
    private By deviceMode = By.id("btncomputedStatus");
    private By btnRefresh = By.id("btnRefresh");
    private By setDeviceStateActive = By.xpath("//*[text()='Active']");
    private By btnConfirmState = By.id("btnChangStatusYes");
    private By btnInfo = By.id("infoBtnOk");
    private By btnScroll = By.xpath("//*[@id = 'jqxScrollBtnDownverticalScrollBarjqxgridDownloadJobProfil']");

    private static String getRowDetails = "";
    //private static boolean TestPassFlag = false;
    DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm");
    //private static String TestFlagRow = "true";

    public void deviceProfile() {
        hoverAndClickOnElement(linkSerialNumber);
        testLog.info(driver.getCurrentUrl());
        waitForLoader(download);
        click(download);

        waitForLoader(deviceMode);
        System.out.println("text of the button :" + getText(deviceMode));
        if (getText(deviceMode).equals("Active")) {
            click(btnRefresh);
        } else {
            click(deviceMode);
            click(setDeviceStateActive);
            waitForLoader(btnInfo);
            click(btnConfirmState);
            waitForLoader(btnInfo);
            click(btnInfo);
            click(btnRefresh);
        }
        waitForLoader(deviceMode);
    }

    private int scrollCounter = 1;

    public void validateJobInstall(String packageName, String deviceStatus, String jobCreatedOnSubscription) throws Exception {
        //set the execuation timeout value
        String timeoutVal = updateMinutesInCurrentTime(jobCreatedOnSubscription, dateFormat, 5);
        System.out.println("timeoutVal : " + timeoutVal);
        testLog.info("-------- TimeOut Value : " + timeoutVal + " -------");

        //Initially decrement the minute of current time to 1
        jobCreatedOnSubscription = updateMinutesInCurrentTime(jobCreatedOnSubscription, dateFormat, -1);
        System.out.println("jobCreatedOnSubscription : " + jobCreatedOnSubscription);
        testLog.info("-------- Search Time Start : " + jobCreatedOnSubscription + " -------");

        // scroll down the web page at the bottom of the page.
        scrollToHeight();
        testLog.info("-------- Start Time : " + MPUtils.getDownloadScheduleTime() + " -------");

        // following loop used to get the row value, verify against the date, job name, package name & scroll
        int i = 0;
        int scrollCounter = 1;
        String TestFlagRow = "true";
        boolean TestPassFlag = false;
        for (; i < 50; i++) {

            testLog.info(" ---- Time : Validate time against the job " + jobCreatedOnSubscription + " ------");

            if (TestFlagRow.equals("true")) {
                testLog.info(" ------ Get the details of row index 0 : " + getRowDetails + " -----");
                System.out.println("if");
                TestFlagRow = "false";
                Thread.sleep(1000);
                getRowDetails = getText(divFirstRow);
                System.out.println("Details of row : " + getText(divFirstRow));
            } else if (!getRowDetails.equals(getText(divSecondRow))) {
                System.out.println("else");
                testLog.info(" ------ Get the details of row index 1 : " + getRowDetails + " -----");
                getRowDetails = getText(divSecondRow);
                System.out.println("Details of row : " + getText(divSecondRow));
            }

            testLog.info(" ------ Text expected : " + jobCreatedOnSubscription + " -- Was : " + getRowDetails + " -----");
            testLog.info(" ------ Text expected : " + packageName + " -- Was : " + getRowDetails + " -----");
            testLog.info(" ------ Text expected : " + deviceStatus + " -- Was : " + getRowDetails + " -----");
            System.out.println("updated date inside the loop " + jobCreatedOnSubscription);

            //Verify the date, package name & job name of the job
            if (assertRowContains(jobCreatedOnSubscription, getRowDetails) & assertRowContains(packageName, getRowDetails)) {
                testLog.info(" ------ Condition true :  Date & Package name  -----");
                System.out.println("Condition true :  Date & Package name");
                Thread.sleep(500);
                if (isContain(getRowDetails, deviceStatus)) {
                    System.out.println("Job created successfully!");
                    testLog.info(" -------- VHQ : Package (" + deviceStatus + ") Job created successfully! -------- ");
                    TestPassFlag = true;
                    click(btnRefresh);
                    waitForLoader(btnRefresh);
                    break;
                }
            }

            testLog.info(" ------ Date expected : " + jobCreatedOnSubscription.substring(0, 11) + " -- Was : " + getRowDetails + " -----");
            if (assertRowContains(jobCreatedOnSubscription.substring(0, 11), getRowDetails) && i != 49) {
                System.out.println("true");
                testLog.info("----- Scroll the Page -----");
                click(btnScroll);
                Thread.sleep(1000);
            } else {
                System.out.println("false");
                testLog.info(" ---- Time : Failed to validate the job ------");
                System.out.println("jobCreatedOnSubscription : " + jobCreatedOnSubscription + "timeoutVal :" + timeoutVal);
                if (!jobCreatedOnSubscription.equals(timeoutVal)) {
                    //Increment minute by one if current time is not find in the list of rows.
                    jobCreatedOnSubscription = incrementTimeByOneMinute(scrollCounter, dateFormat, jobCreatedOnSubscription);
                    System.out.println("Updated time : " + jobCreatedOnSubscription);
                    testLog.info(" ---- Time : Updated time : " + jobCreatedOnSubscription + " ------");
                    i = 0;
                    scrollCounter++;
                    TestFlagRow = "true";
                    click(btnRefresh);
                    waitForLoader(btnRefresh);
                }
            }
        }

        testLog.info("-------- End Time : " + MPUtils.getDownloadScheduleTime() + " -------");

        //Fail the test if value of TestPassFlag is false
        if (!TestPassFlag) {
            testLog.info(" -------- VHQ : Verification of job failed. -------- ");
            Assert.fail("-------- VHQ : Verification of job failed. --------");
        }
    }

    // This method update the minute by 1 if the current time is not available in the list of rows.
    private String incrementTimeByOneMinute(int scrollTime, DateFormat dateFormat, String jobCreatedOnSubscription) throws Exception {
        testLog.info("----- Current Time : " + jobCreatedOnSubscription + " -----");
        System.out.println(" jobCreatedOnSubscription " + jobCreatedOnSubscription);
        System.out.println(" date to be compare :" + MPUtils.getDownloadScheduleTime());

        if (jobCreatedOnSubscription.equals(MPUtils.getDownloadScheduleTime())) {
            System.out.println("don't do anything");
            testLog.info("----- No need to update the time : Expected time == Current time  -----");
        } else {
            System.out.println("time updated");
            testLog.info("----- Need to update the time : Expected time != Current time  -----");
            //cal.add(Calendar.MINUTE, 1);
            jobCreatedOnSubscription = updateMinutesInCurrentTime(jobCreatedOnSubscription, dateFormat, 1);
        }

        testLog.info("----- Date & Time verify : " + jobCreatedOnSubscription + " -----");
        return jobCreatedOnSubscription;
    }

    public String updateMinutesInCurrentTime(String jobCreatedOnSubscription, DateFormat dateFormat, int amount) throws Exception {
        Date d = dateFormat.parse(jobCreatedOnSubscription);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, amount);
        return dateFormat.format(cal.getTime());
    }
}
