package utils.driver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.logging.Level;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Browsers.FIREFOX;

public class WebDriverSetup implements WebDriverProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverSetup.class);

    @Override
    public WebDriver createDriver(DesiredCapabilities caps) {

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL); // Enable access to browser logs
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL); // Enable access to performance logs
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        switch (caps.getBrowserName()) {

            // Chrome configuration
            case CHROME:
                ChromeOptions chromeOpts = new ChromeOptions();
                if (Configuration.headless) {
                    chromeOpts.addArguments("--headless");
                    chromeOpts.addArguments("--window-size=1920,1080");
                }

                chromeOpts.addArguments("--incognito")
                        .merge(caps);
                return new ChromeDriver(chromeOpts);

            // Firefox configuration
            default:
                FirefoxOptions firefoxOpts = new FirefoxOptions();
                if (Configuration.headless)
                    firefoxOpts.addArguments("-headless");
                caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOpts);
                return new FirefoxDriver(firefoxOpts);
        }
    }

    public DesiredCapabilities getCapabilities(String browserName) {
        DesiredCapabilities caps = new DesiredCapabilities();

        switch (browserName) {
            case CHROME:
                caps.setCapability(CapabilityType.BROWSER_NAME, CHROME);
                break;
            case FIREFOX:
                caps.setCapability(CapabilityType.BROWSER_NAME, FIREFOX);
                break;
            default:
                LOGGER.error(String.format("'%s' browser does not exist", browserName));
        }

        return caps;
    }
}