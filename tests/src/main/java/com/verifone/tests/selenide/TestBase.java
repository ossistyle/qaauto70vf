//package com.verifone.tests.selenide;
//import com.codeborne.selenide.Configuration;
//import com.codeborne.selenide.WebDriverRunner;
//import com.verifone.infra.EnvConfig;
//import com.verifone.infra.wd.WDManager;
//import com.verifone.infra.wd.WDProvider;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.testng.ITestResult;
//import org.testng.annotations.*;
//import java.io.IOException;
//import java.lang.reflect.Method;
//
//import static com.codeborne.selenide.Selenide.open;
//
//public abstract class TestBase {
//
//    protected static EnvConfig envConfig;
//    private static WDProvider wdProvider;
//
//    @Parameters({"env", "portal", "browserType"})
//    @BeforeSuite
//    public void beforeSuite(String env, String portal, String browserType) throws IOException {
//        envConfig = new EnvConfig(env, portal);
//        wdProvider = new WDProvider();
//        Configuration.baseUrl = envConfig.getWebUrl();
//        WDManager.downloadDriver(browserType);
//    }
//
//    @Parameters({"browserType"})
//    @BeforeMethod
//    public void startBrowser(Method method, String browserType) {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setBrowserName(browserType);
//        WebDriver driver = wdProvider.createDriver(capabilities);
//        WebDriverRunner.setWebDriver(driver);
//        open("");
//    }
//
//    @AfterMethod(alwaysRun = true)
//    public void afterMethod(Method method, ITestResult result) {
//        WebDriverRunner.getWebDriver().quit();
//    }
//}
