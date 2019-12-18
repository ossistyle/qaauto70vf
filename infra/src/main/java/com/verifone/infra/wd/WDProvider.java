package com.verifone.infra.wd;
import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.verifone.infra.wd.Browser.*;

public class WDProvider implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {

        switch (capabilities.getBrowserName()) {
            case FIREFOX:
                return createFirefoxDriver(capabilities);
            default:
                return createChromeDriver(capabilities);
        }
    }

    private static WebDriver createChromeDriver(DesiredCapabilities capabilities)  {
        Path profile = Paths.get("feqaautomation/infra/src/main/java/com/verifone/infra/wd");
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
