package com.verifone.pages.vhqPages;

import com.verifone.pages.BasePage;
import com.verifone.utils.appUtils.MPUtils;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    private By row0GetDownloadScheduleDate = By.xpath("//*[@id='row0jqxgridDownloadJobProfil']/div[8]/div");
    private By row1GetDownloadScheduleDate = By.xpath("//*[@id='row1jqxgridDownloadJobProfil']/div[8]/div");


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

    public void validateJobInstall(String packageName, String deviceStatus, String jobCreatedOnSubscription, String testMode) throws Exception {
        //set the timeout value
        String timeoutVal = updateMinutesInCurrentTime(jobCreatedOnSubscription, dateFormat, 5);
        System.out.println("timeoutVal : " + timeoutVal);
        testLog.info("-------- TimeOut Value : " + timeoutVal + " -------");

        //Initially decrement the minute of current time to 1
        jobCreatedOnSubscription = updateMinutesInCurrentTime(jobCreatedOnSubscription, dateFormat, -1);
        System.out.println("jobCreatedOnSubscription : " + jobCreatedOnSubscription);

        // scroll down the web page at the bottom of the page.
        scrollToHeight();

        // following loop used to get the row value, verify against the date, job name, package name & scroll
        int i = 0;
        int scrollCounter = 1;
        String TestFlagRow = "true";
        boolean TestPassFlag = false;
        String getScheduleDate = "";
        for (; i < 50; i++) {
            if (TestFlagRow.equals("true")) {
                System.out.println("if");
                TestFlagRow = "false";
                Thread.sleep(1000);
                getRowDetails = getText(divFirstRow);
                System.out.println("Details of row : " + getText(divFirstRow));

                //----------------changes ----------
                getScheduleDate = getText(row0GetDownloadScheduleDate);
                //System.out.println("date :" + getScheduleDate);

            } else if (!getRowDetails.equals(getText(divSecondRow))) {
                System.out.println("else");
                //testLog.info(" ------ Get the details of row index 1 : " + getRowDetails + " -----");
                getRowDetails = getText(divSecondRow);
                System.out.println("Details of row : " + getText(divSecondRow));

                //---------------changes ---------------------
                getScheduleDate = getText(row1GetDownloadScheduleDate);
                //System.out.println("date :" + getScheduleDate);
            }

            //System.out.println("updated date inside the loop " + jobCreatedOnSubscription);
            testLog.info(" ------ <b>Text expected : <u>" + packageName + "</u> , <u>" + deviceStatus + "</u> , <u>" + jobCreatedOnSubscription + "</u> -- Text Actual : " + getRowDetails + " -----</b>");
            testLog.info(" -------- <b> <u>App Subscription created date & Time </u> :(" + jobCreatedOnSubscription + ") ------- <u>VHQ Download Schedule Time</u> : (" + getScheduleDate + ") -------- </b>");

            //Verify the date, package name & job name of the job
            if (assertRowContains(jobCreatedOnSubscription, getRowDetails) & assertRowContains(packageName, getRowDetails)) {
                // testLog.info(" ------ Condition true :  Date & Package name  -----");
                System.out.println("Condition true :  Date & Package name");
                Thread.sleep(500);
                if (isContain(getRowDetails, deviceStatus)) {
                    System.out.println("Job created successfully!");
                    if (deviceStatus.equals("INSTALL")) {
                        testLog.info(" -------- <b> VHQ App Name :(" + packageName + ") Subscribe  successfully! <b> -------- ");
                    } else {
                        testLog.info(" -------- <b> VHQ App Name :(" + packageName + ") Unsubscribe  successfully! <b> -------- ");
                    }
                    testLog.info(" -------- <b> VHQ Job Status :(" + deviceStatus + ") created successfully! </b>-------- ");
                    testLog.info(" -------- <b> VHQ Download Schedule Time :(" + jobCreatedOnSubscription + ") created successfully! </b>-------- ");
                    TestPassFlag = true;
                    click(btnRefresh);
                    waitForLoader(btnRefresh);
                    break;
                }
            }

            //testLog.info(" ------ Date expected : " + jobCreatedOnSubscription.substring(0, 11) + " -- Was : " + getRowDetails + " -----");
            if (assertRowContains(jobCreatedOnSubscription.substring(0, 11), getRowDetails) && i != 49) {
                System.out.println("true");
                testLog.info("----- Scroll the Page -----");
                click(btnScroll);
                Thread.sleep(1000);
            } else {
                System.out.println("false");
                //testLog.info(" ---- Time : Failed to validate the job ------");
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

        //testLog.info("-------- End Time : " + MPUtils.getDownloadScheduleTime() + " -------");

        if (!testMode.equals("negative")) {
            System.out.println("----- Test Mode : Positive  -------");

            //Fail the test if value of TestPassFlag is false
            if (!TestPassFlag) {
                testLog.info(" -------- VHQ Positive Test : Job should be created. -------- ");
                Assert.fail("-------- VHQ Positive Test : Job should be created. --------");
            }
            testLog.info(" -------- VHQ Success : Job is created successfully. -------- ");

        } else {
            System.out.println("----- Test Mode : Negative  -------");
            if (TestPassFlag) {
                testLog.info(" -------- VHQ Negative Test : Job should not be created. -------- ");
                Assert.fail("-------- VHQ Negative Test : Job should not be created. --------");
            }
            testLog.info(" -------- VHQ Success : Job is not created. -------- ");
        }
    }

    // This method update the minute by 1 if the current time is not available in the list of rows.
    private String incrementTimeByOneMinute(int scrollTime, DateFormat dateFormat, String jobCreatedOnSubscription) throws Exception {
        testLog.info("----- Current Time : " + jobCreatedOnSubscription + " -----");
        System.out.println(" jobCreatedOnSubscription " + jobCreatedOnSubscription);
        System.out.println(" date to be compare :" + MPUtils.getDownloadScheduleTime());

        if (jobCreatedOnSubscription.equals(MPUtils.getDownloadScheduleTime())) {
            System.out.println("don't do anything");
            //testLog.info("----- No need to update the time : Expected time == Current time  -----");
        } else {
            System.out.println("time updated");
            //testLog.info("----- Need to update the time : Expected time != Current time  -----");
            //cal.add(Calendar.MINUTE, 1);
            jobCreatedOnSubscription = updateMinutesInCurrentTime(jobCreatedOnSubscription, dateFormat, 1);
        }

        //testLog.info("----- Date & Time verify : " + jobCreatedOnSubscription + " -----");
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
