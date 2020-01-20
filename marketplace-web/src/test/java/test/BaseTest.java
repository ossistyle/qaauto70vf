package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.verifone.infra.EnvConfig;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeSuite;
import utils.allure.AllureSelenide;
import utils.allure.LogType;
import utils.config.EnvironmentConfig;

import java.util.logging.Level;

public abstract class BaseTest {

    protected static EnvConfig envConfig;
    protected EnvironmentConfig config;

    @BeforeSuite (description = "Before suite")
    public void beforeSuite() throws Exception {
//         Configure Allure report
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true) // Add screenshot as attachments
                        .enableLogs(LogType.BROWSER, Level.ALL)); // Add browser logs to report

        config = ConfigFactory.create(EnvironmentConfig.class);
        envConfig = new EnvConfig(config.env(), config.portal());

        // Configure selenide
        Configuration.browser = config.browser();
        Configuration.timeout = 15000;
        Configuration.headless = config.headless();
        Configuration.baseUrl = config.url();
        Configuration.startMaximized = true;

    }

}
