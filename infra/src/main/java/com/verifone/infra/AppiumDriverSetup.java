package com.verifone.infra;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class AppiumDriverSetup implements WebDriverProvider {

    public DesiredCapabilities getCapabilities(Map<String, String> params) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.APP, params.get("app"));
        caps.setCapability(MobileCapabilityType.UDID, params.get("udid"));
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, params.get("deviceName"));
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        caps.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
        return caps;
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities caps) {
        try {
            return new AndroidDriver<SelenideElement>(new URL("http://10.160.10.62:4723/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
