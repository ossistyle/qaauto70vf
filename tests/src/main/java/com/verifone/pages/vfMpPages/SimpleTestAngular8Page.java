package com.verifone.pages.vfMpPages;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularBinding;
import com.paulhammant.ngwebdriver.NgWebDriver;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class SimpleTestAngular8Page extends BasePage {

//    private final static String url = "http://dev.merchant.verifonecp.com/home";
//    private final static String title = "Verifone-Marketplace";

    private final static String url = BaseTest.envConfig.getWebUrl();
    private final static String title = "Verifone-Marketplace";


    //POC - Angular8
    private By btnAppCatalog = ByAngular.buttonText("Apps Catalog");
    private By btnSettings = ByAngular.buttonText("Settings");
    private By btnUsers = ByAngularBinding.partialLinkText("Users");
    private By clickImg = ByAngularBinding.xpath("//img[@src='https://images.unsplash.com/photo-1506919258185-6078bba55d2a?dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb']");


    private By firstUsername = By.id("username");
    private By username = By.id("username");
    private By password = By.id("ipassword");
    private By iframe = By.id("veriPassFrame");
    private By supportUsername = By.id("Username");
    private By SupportPassword = By.id("Passwd");
    private By loginBtn = By.id("btnPrimaryLogin");


    public SimpleTestAngular8Page() {
        super(url, title);
    }

    //open browser in incognito mode
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


    //Login to VFMP
    public void loginVFMP(User user) {

        //waitSimple(2000);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendKeys(username, user.getUserName());
        switchToIframe(iframe);
        click(password);
        sendKeys(password, user.getPassword());
        driver.switchTo().defaultContent();
        click(loginBtn);
    }


    //angular verification- poc
    public void testAngular() {
        NgWebDriver ngDriver = new NgWebDriver((JavascriptExecutor) getDriver());
        ngDriver.waitForAngularRequestsToFinish();

        testLog.info("---------------------------- Navigate to App Catalog ---------------------------------");
        click(btnAppCatalog);

        testLog.info("---------------------------- Navigate to Settings ---------------------------------");
        click(btnSettings);

        testLog.info("---------------------------- Navigate to Users ---------------------------------");
        click(btnUsers);

        testLog.info("---------------------------- Click on Image ---------------------------------");
        click(clickImg);
        driver.close();
    }
}
