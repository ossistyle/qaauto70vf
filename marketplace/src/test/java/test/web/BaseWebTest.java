package test.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.verifone.infra.EnvConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlTest;
import utils.allure.AllureCommon;
import utils.allure.AllureSelenide;
import utils.allure.LogType;
import utils.config.EnvironmentConfig;
import utils.driver.WebDriverManager;
import utils.driver.WebDriverSetup;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

public abstract class BaseWebTest {

    protected static EnvConfig envConfig;
    protected EnvironmentConfig config;
    private XmlTest testngXml;

    @BeforeSuite
    @Parameters({"browserName", "headless"})
    public void beforeSuite(ITestContext context, String browserName, @Optional boolean headless) throws Exception {
        // Allure report configuration
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true) // Add screenshot as attachments
                        .enableLogs(LogType.BROWSER, Level.ALL)); // Add browser logs to report

        config = ConfigFactory.create(EnvironmentConfig.class);
        envConfig = new EnvConfig(config.env(), config.portal());
        testngXml = context.getCurrentXmlTest();

        // Download relevant driver (geckodriver/chromedriver) and set up browser
        WebDriverManager.downloadDriver(browserName);
        WebDriverSetup driverSetup = new WebDriverSetup();
        DesiredCapabilities caps = driverSetup.getCapabilities(browserName);
        driverSetup.createDriver(caps);

        // Selenide configuration
        Configuration.timeout = 40000;
        Configuration.browserCapabilities = caps;
        Configuration.headless = headless;
        Configuration.baseUrl = config.url();
        Configuration.startMaximized = true;
        Configuration.browser = WebDriverSetup.class.getName();
    }

    @AfterSuite (alwaysRun = true)
    public void afterSuite() {
        Properties props = new Properties();
        props.setProperty("Env URL", config.url());
        Map<String, String> params = testngXml.getAllParameters();
        for (Map.Entry<String,String> param : params.entrySet())
            props.setProperty(param.getKey(), param.getValue());
        AllureCommon.addAllureEnvProperties(props);
    }
}
