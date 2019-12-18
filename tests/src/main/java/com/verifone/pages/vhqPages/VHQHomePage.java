package com.verifone.pages.vhqPages;

import com.verifone.pages.BasePage;
import com.verifone.pages.mpPages.CBAAssignPage;
import com.verifone.utils.appUtils.MPUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.verifone.utils.Assertions.assertTextContains;

public class VHQHomePage extends BasePage {
    //private final static String url = "https://vhqtest.verifone.com/";
    private final static String url = "https://qa.mumbai.verifonehq.net";
    private final static String title = "";
    //private final static String customerName = "AppDirect2";
    private final static String customerName = "TestOrg1_VHQ5";
    //private final static String deviceSN = "401-686-709";

    public VHQHomePage() {
        super(url, title);
        navigate();
    }

    /////Device Management/////
    private By customer = By.xpath("//p[@data-blind='text:customerFullName']");
    private By devManag = By.xpath("//*[@id='device']");
    private By managDownl = By.xpath("//*[@id=\"downloads\"]");
    private By downlLib = By.xpath("//*[@id=\"downloadLibrarysublink\"]");
    private By ShowHide = By.id("btnShowHide");

    /////Device Search/////
    private By devSearch = By.xpath("//*[@class='icon-device-search-filter']");
    //private By selectAttributes = By.xpath("//div[@id='deviceAttributDDL_chosen']");
    private By selectDeviceAttribute = By.xpath("//select[@id='deviceAttributDDL']");
    private By selectAttributeName = By.xpath("//select[@id='ddlAttrName']");
    private By contralValue = By.xpath("//input[@id='txtAttrValue']");
    private By btnAdd = By.xpath("//a[@id='btnAddAttr']");
    private By btnApplyFilter = By.xpath("//button[@id='btnApplyFilter']");

    private By linkSerialNumber = By.xpath("//*[@id='row0Devicejqxgrid']/child::div[2]//div[1]/a");
    private By btnDownload = By.xpath("//li[@id='Download_HistroytabLi']");
    private By txtPackages = By.xpath("//div[@id='row0jqxgridDownloadJobProfil']");
    private By btnRefresh = By.xpath("//*[@id='btnRefresh']");
    private By btnReset = By.xpath("//a[@id='btnRestFilter']");
    private By loadingDiv = By.xpath("//div[@id='loadingDiv']");
    private By deviceMode = By.id("btncomputedStatus");
    private By setDeviceStateActive = By.xpath("//*[text()='Active']");
    private By btnConfirmState = By.id("btnChangStatusYes");
    private By btnInfo = By.id("infoBtnOk");
    private By btnDeviceSearchReset = By.xpath("//*[@id='resetBtnForAdSearch']");
    private By btnSearch = By.xpath("//*[@id='resultsection']//a[1]");
    private By deviceSearchPopup = By.xpath("//*[@id='AdvanceSearchModal' and @class='modal bs-example-modal-lg']");


    private DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy ");
    private String currentDate = dateFormat.format(new Date());

    public void verifyCustomer() {
        ExpectedConditions.textToBe(customer, customerName);
        testLog.info("Customer name is " + getText(customer));
    }

    public void verifyDownloadLibrary() throws InterruptedException {
        ExpectedConditions.elementToBeClickable(devManag);
        click(devManag);
        ExpectedConditions.elementToBeClickable(managDownl);
        hoverAndClickOnElement(managDownl);
        ExpectedConditions.elementToBeClickable(downlLib);
        click(downlLib);
        waitForLoader(ShowHide);
    }

    public void deviceSearch(String deviceSN) throws Exception {

        testLog.info("----------------------------------- Navigate to Device Search ----------------------------------------");

        //Thread.sleep(10000);
        waitForLoader(devSearch);
        ExpectedConditions.elementToBeClickable(devSearch);
        click(devSearch);
        // Thread.sleep(3000);

      /*  //reset the search result and search new device
        List<WebElement> popup = driver.findElements(deviceSearchPopup);
        System.out.println("reset btn size : " + popup.size());
        if (popup.size() != 0) {
            testLog.info("----------------------------------- Reset Search ----------------------------------------");
            click(btnDeviceSearchReset);
            waitUntilPageLoad(btnSearch);
            click(btnSearch);
        }*/

        //reset the search result and search new device
        List<WebElement> popup = driver.findElements(deviceSearchPopup);
        System.out.println("reset btn size : " + popup.size());
        if (popup.size() != 0) {
            testLog.info("----------------------------------- Reset Search ----------------------------------------");
            click(btnDeviceSearchReset);
            waitUntilPageLoad(btnSearch);
            click(btnSearch);
        }

        testLog.info("----------------------------------- Navigate to Search Dialog ----------------------------------------");

        //hoverAndClickOnElement(selectAttributes);

        //Wait until element found
        //Add search contents
        //Apply filter
        if (isExists(btnAdd, 28)) {
            testLog.info("-----------------------------------Add Button Element found ----------------------------------------");

            select(selectDeviceAttribute, "SerialNumber");

            ExpectedConditions.visibilityOfElementLocated(selectAttributeName);
            select(selectAttributeName, "27");

            ExpectedConditions.visibilityOfElementLocated(contralValue);
            sendKeys(contralValue, deviceSN);
            click(btnAdd);

            ExpectedConditions.elementToBeClickable(btnApplyFilter);
            scrollToElement(btnApplyFilter);
            click(btnApplyFilter);
        }
    }

    public void deviceProfile() {

        testLog.info("----------------------------------- Navigate to Device Search ----------------------------------------");

        waitForLoader(linkSerialNumber);
        click(linkSerialNumber);

        ExpectedConditions.elementToBeClickable(btnDownload);
        click(btnDownload);
        //Thread.sleep(2000);

        waitForLoader(deviceMode);
        System.out.println("text of the button :" + getText(deviceMode));
        if (getText(deviceMode).equals("Active")) {
            System.out.println("Active");
            click(btnRefresh);
        } else {
            System.out.println("Not Active");
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

    public void getDeviceStatus(String deviceIPAddress, String deviceSerialNo) throws Exception {
        InetAddress geek = InetAddress.getByName(deviceIPAddress);
        System.out.println("Sending Ping Request to " + deviceIPAddress);
        if (geek.isReachable(5000)) {
            testLog.info("---------------------- Device serial no: " + deviceSerialNo + " with IPAddress: " + deviceIPAddress + " is reachable. ----------------------");
        } else {
            testLog.warning("---------------------- Can not connect to the Device serial no: " + deviceSerialNo + " ----------------------");
            // throw new SkipException("Skipping this exception");
        }
    }

}

