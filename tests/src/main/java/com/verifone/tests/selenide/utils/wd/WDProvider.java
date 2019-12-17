package com.verifone.tests.selenide.utils.wd;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.verifone.tests.selenide.utils.wd.Browser.*;

public class WDProvider implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {

        Configuration.timeout = 20000;

        switch (capabilities.getBrowserName()) {
            case FIREFOX:
                return createFirefoxDriver(capabilities);
            default:
                return createChromeDriver(capabilities);
        }
    }

    private static WebDriver createChromeDriver(DesiredCapabilities capabilities)  {
        Path profile = Paths.get("/feqaautomation/tests/src/main/java/com/verifone/tests/selenide/utils/chrome_profile");
        ChromeOptions options = new ChromeOptions()
                .addArguments(String.format("user-data-dri=%s", profile))
                .addArguments("--incognito")
                .merge(capabilities);

        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver(DesiredCapabilities capabilities) {
        FirefoxOptions options = new FirefoxOptions()
                .merge(capabilities);

        return new FirefoxDriver(options);
    }

}
