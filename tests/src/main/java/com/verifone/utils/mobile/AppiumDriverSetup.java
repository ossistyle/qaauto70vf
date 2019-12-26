package com.verifone.utils.mobile;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverSetup implements WebDriverProvider {

    public DesiredCapabilities getCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "device");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        caps.setCapability(MobileCapabilityType.UDID, "emulator-5556");
        caps.setCapability(MobileCapabilityType.APP, "C:\\Users\\vdontsov\\Downloads\\app-debug.apk");
        caps.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
        return caps;
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities caps) {
        try {
            return new AndroidDriver<SelenideElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
