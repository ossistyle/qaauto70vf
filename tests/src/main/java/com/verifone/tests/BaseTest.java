package com.verifone.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.verifone.infra.EnvConfig;
import com.verifone.infra.SeleniumUtils;
import com.verifone.pages.BasePage;
import com.verifone.utils.allure.AllureCommon;
import com.verifone.utils.allure.AllureSelenide;
import com.verifone.utils.allure.LogType;
import com.verifone.utils.apiClient.BaseApi;
import com.verifone.utils.apiClient.BaseDDTApi;
import com.verifone.infra.AppiumDriverSetup;
import com.verifone.utils.mobile.Context;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.open;
import static com.verifone.tests.steps.Steps.getVersions;

public abstract class BaseTest {

    protected AndroidDriver<SelenideElement> androidDriver;
    private XmlTest testngXml;
    public static EnvConfig envConfig;
    private static ExtentReports extent;
    private static ThreadLocal parentTest = new ThreadLocal();
    protected static ThreadLocal test = new ThreadLocal();
    public Date date = new Date();
    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    //    public String reportDirectory = java.nio.file.Paths.get(
//            System.getProperty("user.dir"), "reports", dateFormat.format(date)).toString() + File.separator;
//    public String reportLocation = reportDirectory + dateFormat.format(date) + ".html";
    public String reportDirectory = java.nio.file.Paths.get(
            System.getProperty("user.dir"), "reports").toString() + File.separator;
    public String reportLocation = reportDirectory + "index.html";
    protected SeleniumUtils seleniumUtils;

    @Parameters({"env", "portal", "getVersions"})
    @BeforeSuite
    public void beforeSuite(ITestContext context, String env, String portal, String getVersions) throws Exception {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true) // Add screenshot as attachments
                        .enableLogs(LogType.BROWSER, Level.ALL)); // Add browser logs to report

        testngXml = context.getCurrentXmlTest();
//        new File(reportDirectory).mkdir();
        extent = ExtentManager.createInstance(reportLocation);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportLocation);
        extent.attachReporter(htmlReporter);
        setEnv(env, portal);

        Configuration.baseUrl = envConfig.getWebUrl();
        Configuration.timeout = 15000;

        if (getVersions.equalsIgnoreCase("true")) {
            ExtentTest parent = extent.createTest("Get Versions");
            parentTest.set(parent);
            starTestLog("Get Versions", "");
            SeleniumUtils.reportDirectory = reportDirectory;
            SeleniumUtils.isLinuxMachine = "FALSE";
            SeleniumUtils.setBrowser("CHROME");
            parent.info("Versions: " + getVersions());
            WebDriverRunner.closeWebDriver();
        }

    }

    public void setEnv(String env, String portal) throws IOException {
        envConfig = new EnvConfig(env, portal);
        System.out.println("The Automation tests runs on : " + env + " environment");

    }
    @Parameters({"env", "portal", "getVersions", "isLinuxMachine"})
    @BeforeClass
    public synchronized void beforeClass(String env, String portal, String getVersions, String isLinuxMachine) {
        ExtentTest parent = extent.createTest(getClass().getName());
        parentTest.set(parent);
        parent.info("PARAMETERS: Env: " + env + ", Portal: " + portal + ", Get Version: " + getVersions +
                ", Is Linux: " + isLinuxMachine);
        parent.info("The Automation tests runs on : " + envConfig.getWebUrl()
                + " " + envConfig.getEnv() + " environment");
    }


    @Parameters({"browserType", "isLinuxMachine"})
    @BeforeMethod
    public void startBrowser(Method method, String browserType, String isLinuxMachine) throws Exception {
        SeleniumUtils.reportDirectory = reportDirectory;
        SeleniumUtils.isLinuxMachine = isLinuxMachine;
        String methodName = method.getName();
        if (methodName.contains("UI")) {
            SeleniumUtils.setBrowser(browserType);
        } else if (methodName.contains("Mobile")) {
            AppiumDriverSetup driverSetup = new AppiumDriverSetup();
            Configuration.proxyHost = String.format("%s/wd/hub", testngXml.getParameter("server_url"));
            DesiredCapabilities caps = driverSetup.getCapabilities(testngXml.getAllParameters());
            driverSetup.createDriver(caps);

            Configuration.startMaximized = false;
            Configuration.browserSize = null;
            Configuration.browserCapabilities = caps;
            Configuration.browser = AppiumDriverSetup.class.getName();
            open();
            androidDriver = (AndroidDriver<SelenideElement>) WebDriverRunner.getAndCheckWebDriver();
        } else if (methodName.contains("DDT")) {
            return;
        }
        Test test = method.getAnnotation(Test.class);
        starTestLog(test.testName(), test.description());
    }


    protected void starTestLog(String testName, String description) {
        ExtentTest p = (ExtentTest) parentTest.get();
        test.set(p.createNode(testName, description));
        BaseApi.testLog = BaseDDTApi.testLog = BasePage.testLog = (ExtentTest) test.get();
    }


    @AfterMethod()
    public void stopTestReport(Method method, ITestResult result) throws Exception {
        ExtentTest child = (ExtentTest) test.get();
        child.info("result get status is: " + result.getStatus());
        switch (result.getStatus()) {
            case ITestResult.SKIP:
                child.skip("Test SKIP <span class='label info'>info</span>");
                break;
            case ITestResult.SUCCESS:
                child.pass("Test Passed <span class='label success'>success</span>");
                break;
            case ITestResult.FAILURE:
                String methodName = method.getName();
                if (methodName.contains("UI") || methodName.contains("Mobile")) {
                    if (methodName.contains("Mobile")) { Context.switchTo(Context.NATIVE); }
                    String capScreenShootPath = SeleniumUtils.getScreenshot(WebDriverRunner.getWebDriver());
                    child.info("Snapshot path: " + (capScreenShootPath));
                    child.info("Snapshot below: " + child.addScreenCaptureFromPath(capScreenShootPath));
                    if (methodName.contains("Mobile")) { Context.switchTo(Context.WEBVIEW); }
                }
                child.fail(result.getThrowable() + " <span class='label label-fail'>fail</span>");
                break;
        }

        String methodName = method.getName();
        if ((methodName.contains("UI") & !methodName.contains("UI_Cont")) || methodName.contains("Mobile")) {
            child.info("Closing Web Page");
            if (WebDriverRunner.hasWebDriverStarted())
                WebDriverRunner.closeWebDriver();
        }
        extent.flush();
    }

    @AfterSuite
    public void afterSuite() {
        Properties props = new Properties();
        props.setProperty("Env URL", envConfig.getWebUrl());
        Map<String, String> params = testngXml.getAllParameters();
        for (Map.Entry<String,String> param : params.entrySet())
            props.setProperty(param.getKey(), param.getValue());
        AllureCommon.addAllureEnvProperties(props);
    }
}


class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance("test-output/extent.html");

        return extent;
    }

    public static ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }
}