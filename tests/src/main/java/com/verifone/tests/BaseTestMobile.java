package com.verifone.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.verifone.infra.EnvConfig;
import com.verifone.utils.mobile.AppiumDriverSetup;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import java.io.IOException;
import java.lang.reflect.Method;

import static com.codeborne.selenide.Selenide.*;

public class BaseTestMobile {

    protected AndroidDriver<SelenideElement> driver;
    protected static EnvConfig envConfig;
    private static String ENV = "DEV";
    private static String PORTAL = "VFMPPortal";
    private static String APPIUM_SERVER_URL = "http://localhost:4723/wd/hub";

    @BeforeSuite
    public void beforeSuite() throws IOException {
        AppiumDriverSetup driverSetup = new AppiumDriverSetup();
        DesiredCapabilities caps = driverSetup.getCapabilities();
        driver = (AndroidDriver<SelenideElement>) driverSetup.createDriver(caps);

        Configuration.timeout = 30000;
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.browserCapabilities = caps;
        Configuration.browser = AppiumDriverSetup.class.getName();
        Configuration.proxyHost = APPIUM_SERVER_URL;
        envConfig = new EnvConfig(ENV, PORTAL);
    }

    @BeforeMethod
    public void beforeTest(Method method) {
        open();
    }

    @AfterMethod
    public void afterTest(Method method) {
        closeWebDriver();
    }
}
