package test.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.SoftAsserts;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import org.testng.xml.XmlTest;
import utils.allure.AllureCommon;
import utils.allure.AllureSelenide;
import utils.allure.LogType;
import utils.config.EnvironmentConfig;
import utils.driver.WebDriverManager;
import utils.driver.WebDriverSetup;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;

import static com.codeborne.selenide.AssertionMode.STRICT;

@Listeners({ SoftAsserts.class })
public abstract class BaseWebTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private XmlTest testngXml;
    protected EnvironmentConfig config;
    protected ResourceBundle testData;
    protected String env;

    @BeforeSuite
    @Parameters({"browserName", "headless"})
    public void beforeSuite(ITestContext context, String browserName, @Optional boolean headless) {
//         Clear allure-results folder
        AllureCommon.deleteAllureResults();

        // Allure report configuration
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true) // Add screenshot as attachments
                        .savePageSource(true) // Save html page source
                        .enableLogs(LogType.BROWSER, Level.ALL)); // Add browser logs to report

        config = ConfigFactory.create(EnvironmentConfig.class);
        testngXml = context.getCurrentXmlTest();

        // Download relevant driver (geckodriver/chromedriver) and set up browser
        WebDriverManager.downloadDriver(browserName);
        WebDriverSetup driverSetup = new WebDriverSetup();
        DesiredCapabilities caps = driverSetup.getCapabilities(browserName);

        // Selenide configuration
        Configuration.reportsFolder = "target/allure-results/attachments";
        Configuration.screenshots = true;
        Configuration.timeout = 40000;
        Configuration.browserCapabilities = caps;
        Configuration.headless = headless;
        Configuration.baseUrl = config.url();
        Configuration.startMaximized = true;
        Configuration.browser = WebDriverSetup.class.getName();
    }

    @BeforeClass
    @Parameters("env")
    public void initializeVars(String env) {
        this.env = env;
        testData = ResourceBundle.getBundle("testData/" + env.toLowerCase());
    }

    @BeforeMethod
    public void openBrowser(Method method) {
        Configuration.assertionMode = STRICT;
        logger.info("----- Start WebDriver for test: " + method.getName() + " -----");
    }

    @AfterMethod
    public void closeBrowser(Method method) {
        logger.info("----- Close WebDriver for test: " + method.getName() + "-----");
        if (WebDriverRunner.hasWebDriverStarted())
            WebDriverRunner.closeWebDriver();
    }

    @AfterSuite (alwaysRun = true)
    public void addPropsToReport() {
        Properties props = new Properties();
        props.setProperty("Env URL", this.env);
        Map<String, String> params = testngXml.getAllParameters();
        for (Map.Entry<String,String> param : params.entrySet())
            props.setProperty(param.getKey(), param.getValue());
        AllureCommon.addAllureEnvProperties(props);
    }
}
