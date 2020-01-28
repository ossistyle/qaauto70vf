package com.verifone.infra;

import com.codeborne.selenide.WebDriverRunner;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;

//import com.gargoylesoftware.htmlunit.javascript.host.URL;
//import org.testng.annotations.Test;

/**
 * This class contains convenience methods for working with Selenium.
 *
 * @Giora Tovim
 */

@SuppressWarnings("unused")
public class SeleniumUtils {
    public static String reportDirectory;
    public static String isLinuxMachine;
    private static String downloadDir = java.nio.file.Paths.get(
            System.getProperty("user.dir"), "src","test","resources","downloads").toString() + File.separator;

    /**
     * This method set browser
     * BrowserType:
     * Chrome - CHROME
     * FireFox - FF
     * Internet Explorer - IE
     *
     * @param browserType
     * @return driver
     * @throws Exception
     * @author Giora Tovim
     */
    @SuppressWarnings("deprecation")
    public static void setBrowser(String browserType) {
        WebDriver driver;
        System.out.println("Starting web browser switch: " + browserType);
        switch (browserType.toUpperCase()) {
            case "FF":
                System.setProperty("webdriver.gecko.driver", pathToDrivers("geckodriver.exe"));
//			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//			desiredCapabilities.setAcceptInsecureCerts(true);
//			FirefoxProfile firefoxProfile = new FirefoxProfile();
//			firefoxProfile.setPreference("browser.private.browsing.autostart",true);
                driver = new FirefoxDriver();
                System.out.println("finish start FireFox!!!");
                break;

            case "CHROME":
                System.out.println("Starting CHROME web driver");
                System.out.println(new File(System.getProperty("user.dir")).getParent());
                if (!isLinuxMachine.contains("FALSE")) {
                    System.setProperty("webdriver.chrome.driver", pathToDrivers("chromedriver"));
                } else {
                    System.setProperty("webdriver.chrome.driver", pathToDrivers("chromedriver.exe"));
                }
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", downloadDir);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                options.addArguments("--incognito");
                options.setExperimentalOption("prefs", chromePrefs);
                if (!isLinuxMachine.equalsIgnoreCase("FALSE")) {
                    options.addArguments("--headless");
                    options.addArguments("window-size=1743x600");
                }

                // Set Logging preferences
                LoggingPreferences logPrefs = new LoggingPreferences();
                logPrefs.enable(LogType.BROWSER, Level.ALL); // Enable access to browser logs
                logPrefs.enable(LogType.PERFORMANCE, Level.ALL); // Enable access to performance logs
                options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
                driver = new ChromeDriver(options);
                System.out.println("CHROME web driver started successfully");
                break;
            case "EDGE":
                System.out.println("Starting EDGE web driver");
                System.setProperty("webdriver.edge.driver", pathToDrivers("MicrosoftWebDriver.exe"));
                driver = new EdgeDriver();
                System.out.println("EDGE web driver started successfully");
                break;

            case "IE":
                System.out.println("Starting IE web driver");
                System.setProperty("webdriver.ie.driver", pathToDrivers("IEDriverServer.exe"));
                driver = new InternetExplorerDriver();
                //				DesiredCapabilities desiredCapabilities1 = new DesiredCapabilities();
                //		    	desiredCapabilities1.setAcceptInsecureCerts(true);
                //				driver = new InternetExplorerDriver(desiredCapabilities1);
                System.out.println("IE web driver started successfully");
                break;

            default:
                System.out.println("Browser name is not exist!!!");
                return;

        }
        if (isLinuxMachine.equalsIgnoreCase("FALSE"))
            driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        WebDriverRunner.setWebDriver(driver);
    }


    /**
     * This method get screenshot name of file is date
     *
     * @throws Exception
     * @author Giora Tovim
     */
    public String getScreenshot() throws Exception {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        //The below method will save the screen shot in d drive with folder "screenshot" + filenameDate + ".png "
        String screenshotPath = reportDirectory + dateFormat.format(date) + ".png";
        try {
            File scrFile = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(screenshotPath));
        } catch (NoSuchSessionException e) {
            e.printStackTrace();
            return "";
        }
        return screenshotPath;
    }


    public static String getScreenshot(WebDriver driver) throws Exception {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        //The below method will save the screen shot in d drive with folder "screenshot" + filenameDate + ".png "
        String screenshotPath = reportDirectory + dateFormat.format(date) + ".png";
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(screenshotPath));
        } catch (NoSuchSessionException e) {
            e.printStackTrace();
            return "";
        }
        return screenshotPath;
    }


    //    TODO this method is just for support old tets
    public static void setEnv(String env, String urlDev, String urlTest,
                              String urlStaging1, String urlProduction, ExtentTest test) throws Exception {
        System.out.println("The Automation tests runs on : " + env + " environment");


    }


    private static String pathToDrivers(String fileName) {
        return java.nio.file.Paths.get(
                new File(System.getProperty("user.dir")).getParent(), "infra", "drivers", fileName).toString();
    }

//    public static void restartDriver() {
//        driver.manage().deleteAllCookies();         // Clear Cookies on the browser
//        driver.quit();
//        driver = null;
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//    }

}