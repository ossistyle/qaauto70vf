package test.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.verifone.infra.AppiumDriverSetup;
import com.verifone.infra.EnvConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlTest;
import utils.allure.AllureCommon;
import utils.allure.AllureSelenide;
import utils.allure.LogType;
import utils.config.EnvironmentConfig;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

public abstract class BaseMobileTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected static EnvConfig envConfig;
    protected EnvironmentConfig config;
    private XmlTest testngXml;

    @BeforeSuite
    public void beforeSuite(ITestContext context) throws Exception {
        // Clear allure-results folder
        AllureCommon.deleteAllureResults();

        // Allure report configuration
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true) // Add screenshot as attachments
                        .enableLogs(LogType.BROWSER, Level.ALL)); // Add browser logs to report

        config = ConfigFactory.create(EnvironmentConfig.class);
        envConfig = new EnvConfig(config.env(), config.portal());
        testngXml = context.getCurrentXmlTest();

        AppiumDriverSetup driverSetup = new AppiumDriverSetup();
        Configuration.proxyHost = String.format("%s/wd/hub", testngXml.getParameter("server_url"));
        DesiredCapabilities caps = driverSetup.getCapabilities(testngXml.getAllParameters());
        driverSetup.createDriver(caps);

        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.baseUrl = config.url();
        Configuration.browserCapabilities = caps;
        Configuration.timeout = 40000;
        Configuration.browser = AppiumDriverSetup.class.getName();
    }

    @BeforeMethod
    public void setUp(Method method) {
        logger.info("----- Start driver for test: " + method.getName() + " -----");
        Selenide.open();
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(Method method) {
        logger.info("----- Close driver for test: " + method.getName() + "-----");
        if (WebDriverRunner.hasWebDriverStarted())
            WebDriverRunner.closeWebDriver();
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
