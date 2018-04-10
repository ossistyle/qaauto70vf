package com.verifone.infra;
//import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;


//import com.gargoylesoftware.htmlunit.javascript.host.URL;
import com.google.common.collect.Lists;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
//import org.testng.annotations.Test;

/**
 * This class contains convenience methods for working with Selenium.
 * @Giora Tovim
 */

@SuppressWarnings("unused")
public class SeleniumUtils  {
	private static WebDriver driver;
//	private static Reporter report = ListenerstManager.getInstance();


 /**
 * This method set browser
 * BrowserType:
 * Chrome - CHROME
 * FireFox - FF
 * Internet Explorer - IE
 * @author Giora Tovim 
 * @param browserType
 * @return driver
 * @throws Exception
 */
@SuppressWarnings("deprecation")
public static WebDriver setBrowser(String browserType) throws Exception 
	  
 		{
 
		System.out.println("Starting web browser switch: " + browserType);
		switch (browserType) {
	    case "FF":
	    	System.setProperty("webdriver.gecko.driver","C:\\automation_resources\\seleniumDrivers\\geckodriver.exe");
	    	DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	    	desiredCapabilities.setAcceptInsecureCerts(true);
	    	FirefoxProfile firefoxProfile = new FirefoxProfile();    
	    	firefoxProfile.setPreference("browser.private.browsing.autostart",true);
	       	driver = new FirefoxDriver(desiredCapabilities);
	    	System.out.println("finish start FireFox!!!");
	       break;
	   
	    case "CHROME":
	    		System.out.println("Starting CHROME web driver");
	   	 		System.setProperty("webdriver.chrome.driver", "C:\\automation_resources\\seleniumDrivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				options.addArguments("--incognito");
				driver = new ChromeDriver();
				System.out.println("CHROME web driver started successfully");
	        break;
	    case "EDGE":
	    		System.out.println("Starting EDGE web driver");
	    		System.setProperty("webdriver.edge.driver", "C:\\automation_resources\\seleniumDrivers\\MicrosoftWebDriver.exe");	
				driver = new EdgeDriver();
				System.out.println("EDGE web driver started successfully");
	        break;
	        
	    case "IE":
	    		System.out.println("Starting IE web driver");
	    		System.setProperty("webdriver.ie.driver", "C:\\automation_resources\\seleniumDrivers\\IEDriverServer.exe");
				WebDriver Driver = new InternetExplorerDriver();
//				DesiredCapabilities desiredCapabilities1 = new DesiredCapabilities();
//		    	desiredCapabilities1.setAcceptInsecureCerts(true);
//				driver = new InternetExplorerDriver(desiredCapabilities1);
				System.out.println("IE web driver started successfully");
	        break;
	  
	    default:
	        System.out.println("Browser name is not exist!!!" );
		
		 }
		return driver;
					
	    }	
	
	
/**
 * This method get screenshot name of file is date
 * @author Giora Tovim
 * @param driver
 * @throws Exception
 */
public static String getscreenshot(WebDriver driver) throws Exception 
{
	Date date = new Date() ;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 //The below method will save the screen shot in d drive with folder "screenshot" + filenameDate + ".png "
	 String screeshootPath = "C:\\screenshot\\" + dateFormat.format(date) + ".png"; 
	 FileUtils.copyFile(scrFile, new File(screeshootPath));
	 return screeshootPath;
}


/**
 * This method find text in web page and get screenshot
 * @author Giora Tovim
 * @param driver
 * @param textToFind
 * @throws Exception
 */
public static void findTextInPage(WebDriver driver, String textToFind) throws Exception 
{
	System.out.println("Try to Find the text: " + textToFind +" in web page");		
	if(driver.getPageSource().contains(textToFind)){
		System.out.println("Test Passed the text " + textToFind + " was found successfully in the web page " );
		getscreenshot(driver);
	}else{
		System.out.println("Test Failed !!!! the text: " + textToFind + " was not found");
		org.testng.Assert.fail("Test Failed !!!! the text: " + textToFind + " was not found");
		getscreenshot(driver);
	}
} 



/**
 * This method set env portal
 * @author Giora Tovim
 * @param env
 * @param urlDev
 * @param urlTest
 * @param urlStaging1
 * @param urlProduction
 * @param test - use for ExtentTest report
 * @throws Exception
 */
public static void setEnv(String env,String urlDev, String urlTest,
		String urlStaging1, String urlProduction, ExtentTest test) throws Exception 
{
System.out.println("The Automation tests runs on : " + env + " environment");
	
switch (env) {
case "DEV":
	System.out.println("Open urlDev");
	driver.get(urlDev);
	System.out.println("Open url" + urlDev + "ended successfully");
	test.log(LogStatus.INFO, "Open " + urlDev + " in " + env + " environment");
    break;

case "TEST":
	System.out.println("Open urlTest");
	driver.get(urlTest);
	System.out.println("Open url" + urlTest + "ended successfully");
	test.log(LogStatus.INFO, "Open " + urlTest + " in " + env + " environment");
    break;
    
case "STAGING1":
	System.out.println("Open urlStaging1");
	driver.get(urlStaging1);
	System.out.println("Open url" + urlStaging1 + "ended successfully");
	test.log(LogStatus.INFO, "Open " + urlStaging1 + " in " + env + " environment");
    break;
    
case "PRODUCTION":
	System.out.println("Open urlProduction");
	driver.get(urlProduction);
	System.out.println("Open url" + urlProduction + "ended successfully");
	test.log(LogStatus.INFO, "Open " + urlProduction + " in " + env + " environment");
    break;

default:
    System.out.println("env name is not exist!!!" );

 }
}

}