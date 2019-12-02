package com.verifone.pages.cgPages;

import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.record.formula.functions.T;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.verifone.pages.mpPages.*;

import java.io.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.verifone.utils.Assertions.assertTextEqual;

public class CGLoginPage extends BasePage {

    private final static String getSponsorID = BaseTest.envConfig.getSponsorID();
    private final static String getApplicationID = BaseTest.envConfig.getApplicationID();
    private final static String getApplicationVersion = BaseTest.envConfig.getApplicationVersion();

    //private final static String url = BaseTest.envConfig.getWebUrl();
    private final static String url = "https://test.cgateway-portal.verifone.com";
    private final static String title = "Commerce gateway portal";

    private By toLoginPageBtn = By.partialLinkText("Log");
    private By username = By.id("username");
    private By password = By.id("ipassword");
    private By loginBtn = By.id("btnPrimaryLogin");
    private By iframe = By.id("veriPassFrame");
    private By dashboardIframe = By.id("iFrameResizer0");
    private By cgDashboardTitle = By.xpath("(//*[@class=\"dashboard-header clearfix\"]/h2)");
    private By panel = By.xpath("//*[@class='panel-body']");

    private By accountName = By.xpath("//*[@class='account-name verifone']");
    private By btnSignAndPackage = By.xpath("//button[@class='btn vf-button  ng-binding']");
    private By btnBrowse = By.id("file");
    private By inputSponsorID = By.xpath("//*[@id='sponsors']/div/input");
    private By downloadZip = By.xpath("//a[@class='btn vf-button ng-binding']");

    private static String downloadDirectory = System.getProperty("user.home") + File.separator + "Downloads";
    private static String generatedAppId = AndroidProjectOperationPage.androidProjectAppId;

    //original code
    //private static String userDir = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "apps";
    //private static String downloadFilePath = System.getProperty("user.dir") + File.separator + userDir;
    //private static String downloadedZipName = userDir + File.separator + getApplicationID + "-" + getApplicationVersion;
    //private static String instZipFileName = downloadedZipName + File.separator + "INSTALL" + File.separator + "ANDROID" + File.separator + "cp-" + getApplicationID + "-inst";

    private static String userDir = BaseTest.envConfig.getAppsDirectoryPath();

    private static String downloadedZipName = userDir + File.separator + "apps" + File.separator + getApplicationID + "-" + getApplicationVersion;
    private static String instZipFileName = downloadedZipName + File.separator + "INSTALL" + File.separator + "ANDROID" + File.separator + "cp-" + getApplicationID + "-inst";

    private static final String androidProjectPath = userDir + File.separator + "apps" + File.separator + "TestAPK";
    private static final String androidAPKPath = androidProjectPath + File.separator + "app" + File.separator + "build" + File.separator + "outputs" + File.separator + "apk" + File.separator + "debug" + File.separator + "app-debug.apk";


    public CGLoginPage() {
        super(url, title);
    }

    public void openChrome() {
        System.setProperty("webdriver.chrome.driver", new File(System.getProperty("user.dir")).getParent() + "\\infra\\drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--incognito");
        driver = new ChromeDriver();
        System.out.println("CHROME web driver started successfully");
        driver.manage().window().maximize();
        navigate();
    }

    public void doLogin(User user) {
        testLog.info("-------------------------------------------- Navigate to CG Login Page --------------------------------------------");
        click(toLoginPageBtn);
        sendKeys(username, user.getUserName());
        switchToIframe(iframe);
        click(password);
        sendKeys(password, user.getPassword());
        driver.switchTo().defaultContent();
        click(loginBtn);
    }

    public void checkTitle() {
        switchToIframe(dashboardIframe);
        String pageTitle = getText(cgDashboardTitle);
        assertTextEqual("CG Dashboard", pageTitle);
        testLog.info("Test passed - Login success, browser on page: " + pageTitle);
        driver.switchTo().defaultContent();
    }


    public void clickLoginLink() {
        click(toLoginPageBtn);
    }

    public void navigateToCGLogin() {
        navigate();
    }

    public void inputUserName(String UserName) {
        sendKeys(username, UserName);
        click(panel);
    }

    public void inputPassword(String Pwd) {
        switchToIframe(iframe);
        sendKeys(password, Pwd);
        driver.switchTo().defaultContent();
        click(panel);
//        sendKeys(password, Pwd);
    }

    public void clickLoginBtn() {
        click(loginBtn);
    }

    /**
     * This method describe the CGateway (V1) signing process
     * @author Prashant Lokhande
     */


    /**
     * Copy file to a new location
     *
     * @author Prashant Lokahnde
     */

    public static void copyfileToTargetLocation(File sourceFile, File destinationFile) throws IOException, Exception {
        try {
            FileUtils.copyFileToDirectory(sourceFile, destinationFile);
            sourceFile.delete();
            testLog.info("<b>Info -> <u>Success</b></u> : File successfully copied from one location to new. ");
        } catch (IOException ex) {
            testLog.info("<b>Info -> <u>Error</b></u> : Failed to copy the file to new location.");
            Assert.fail("<b>Info -> <u>Error</b></u> : Failed to copy the file to new location.");
        }

    }

    public void getV1SignedPackage() throws Exception {

        testLog.info("-------------------------------------------- Navigate to V1 Signing --------------------------------------------");

        waitForLoader(btnSignAndPackage);

        WebElement elementSponsorID = driver.findElement(inputSponsorID);
        WebElement elementBtnBrowse = driver.findElement(btnBrowse);
        elementSponsorID.sendKeys(getSponsorID);
        elementBtnBrowse.sendKeys(androidAPKPath);
        //elementBtnBrowse.sendKeys(downloadFilePath + File.separator + getApplicationID + ".apk");

        testLog.info("-------------------------------------------- Get Sponsor ID (" + getSponsorID + " )--------------------------------------------");
        testLog.info("-------------------------------------------- Get Application ID (" + getSponsorID + " ) --------------------------------------------");

        click(btnSignAndPackage);

        //Delete the file if it is exists in the location
        //File downloadDirPath = new File(downloadDirectory + File.separator + generatedAppId + "-" + getApplicationVersion + ".zip");
        File downloadDirPath = new File(userDir + File.separator + "downloads" + File.separator + generatedAppId + "-" + getApplicationVersion + ".zip");
        if (isFileExists(downloadDirPath, 5)) {
            testLog.info("-------------------------------------------- Fil Exists : Delete file --------------------------------------------");
            downloadDirPath.delete();
        }

        //File file = new File(downloadFilePath + File.separator + getApplicationID + "-" + getApplicationVersion + ".zip");
        click(downloadZip);

        //Wait until the zip is downloaded
        //Copy file from download dir to project location
        if (isFileExists(downloadDirPath, 28)) {
            testLog.info("-------------------------------------------- Download Result : Signed package downloaded successfully. --------------------------------------------");
            //File destFile = new File(userDir + File.separator);
            //copyfileToTargetLocation(downloadDirPath, destFile);
        } else {
            // System.out.println(" isFileExists :" + isFileExists(downloadDirPath, 20));
            Assert.fail("-------------------------------------------- Error : Failed to download package --------------------------------------------");
        }

        testLog.info("-------------------------------------------- Download Package : Package signed successfully on CGateway Portal. ");


        //Thread.sleep(10000);


        // wait.until((ExpectedCondition<Boolean>) webDriver -> file.exists());
        // System.out.println("File Downloaded");

        // DLMLoginPage.unZipCGSignedPackage(downloadedZipName, instZipFileName);

      /*  String zipFilePath = downloadedZipName + ".zip";
        String destDirectory = downloadedZipName;

        unzip(zipFilePath, destDirectory);

        zipFilePath = instZipFileName + ".zip";
        destDirectory = instZipFileName;

        unzip(zipFilePath, destDirectory);*/

    }

    private static final int BUFFER_SIZE = 4096;

    public static void unzip(String zipFilePath, String destDirectory)
            throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));

        ZipEntry entry = zipIn.getNextEntry();
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                extractFile(zipIn, filePath);
            } else {
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    private static void extractFile(ZipInputStream zipIn, String filePath)
            throws IOException {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            new File(targetFile.getParent()).mkdirs();
            targetFile.createNewFile();
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte['?'];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }


}
