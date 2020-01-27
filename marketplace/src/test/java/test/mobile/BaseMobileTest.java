package test.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.SoftAsserts;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;
import utils.allure.AllureCommon;
import utils.allure.AllureSelenide;
import utils.allure.LogType;
import utils.config.EnvironmentConfig;
import utils.driver.AppiumDriverSetup;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;

import static com.codeborne.selenide.AssertionMode.STRICT;

@Listeners({ SoftAsserts.class} )
public abstract class BaseMobileTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private XmlTest testngXml;
    protected EnvironmentConfig config;
    protected ResourceBundle testData;
    protected String env;

    @BeforeSuite
    public void beforeSuite(ITestContext context) {
//         Clear allure-results folder
        AllureCommon.deleteAllureResults();
//         Create open_report.bat file in generate directory
        AllureCommon.addAllureOpenFile();

        // Allure report configuration
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true) // Add screenshot as attachments
                        .enableLogs(LogType.BROWSER, Level.ALL)); // Add browser logs to report

        config = ConfigFactory.create(EnvironmentConfig.class);
        testngXml = context.getCurrentXmlTest();

        AppiumDriverSetup driverSetup = new AppiumDriverSetup();
        DesiredCapabilities caps = driverSetup.getCapabilities(testngXml.getAllParameters());

        // Selenide configuration
        Configuration.proxyHost = String.format("%s/wd/hub", testngXml.getParameter("server_url"));
        Configuration.reportsFolder = "target/allure-results/attachments";
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.baseUrl = config.url();
        Configuration.browserCapabilities = caps;
        Configuration.timeout = 40000;
        Configuration.browser = AppiumDriverSetup.class.getName();
    }

    @BeforeClass
    @Parameters("env")
    public void initializeVars(String env) {
        this.env = env;
        testData = ResourceBundle.getBundle("testData/" + env.toLowerCase());
    }

    @BeforeMethod
    public void openApp(Method method) {
        Configuration.assertionMode = STRICT;
        logger.info("----- Start driver for test: " + method.getName() + " -----");
        Selenide.open();
    }

    @AfterMethod (alwaysRun = true)
    public void closeApp(Method method) {
        logger.info("----- Close driver for test: " + method.getName() + "-----");
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
