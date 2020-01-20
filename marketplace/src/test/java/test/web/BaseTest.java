package test.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.verifone.infra.EnvConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import utils.allure.AllureSelenide;
import utils.allure.LogType;
import utils.config.EnvironmentConfig;
import utils.driver.WDManager;
import utils.driver.WDSetup;
import java.util.logging.Level;

public abstract class BaseTest {

    protected static EnvConfig envConfig;
    protected EnvironmentConfig config;

    @BeforeSuite (description = "Before suite")
    public void beforeSuite() throws Exception {
        // Allure report configuration
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true) // Add screenshot as attachments
                        .enableLogs(LogType.BROWSER, Level.ALL)); // Add browser logs to report

        config = ConfigFactory.create(EnvironmentConfig.class);
        envConfig = new EnvConfig(config.env(), config.portal());

        // Download relevant driver (geckodriver/chromedriver)
        WDManager.downloadDriver(config.browser());

        WDSetup setup = new WDSetup();
        DesiredCapabilities caps = setup.getCapabilities(config.browser());
        setup.createDriver(caps);

        // Selenide configuration
        Configuration.timeout = 40000;
        Configuration.browserCapabilities = caps;
        Configuration.headless = config.headless();
        Configuration.baseUrl = config.url();
        Configuration.startMaximized = true;
        Configuration.browser = WDSetup.class.getName();
    }

}
