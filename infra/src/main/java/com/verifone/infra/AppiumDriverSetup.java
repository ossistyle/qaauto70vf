package com.verifone.infra;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;

public class AppiumDriverSetup implements WebDriverProvider {

    public DesiredCapabilities getCapabilities(Map<String, String> params) {
        // Set Logging preferences
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL); // Enable access to browser logs
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL); // Enable access to performance logs

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.APP, params.get("app"));
        caps.setCapability(MobileCapabilityType.UDID, params.get("udid"));
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, params.get("deviceName"));
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        caps.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        return caps;
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities caps) {
        try {
            return new AndroidDriver<SelenideElement>(new URL(Configuration.proxyHost), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
