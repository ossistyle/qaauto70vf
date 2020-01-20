package test.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.verifone.infra.AppiumDriverSetup;
import com.verifone.infra.EnvConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlTest;
import utils.allure.AllureSelenide;
import utils.allure.LogType;
import utils.config.EnvironmentConfig;
import java.util.logging.Level;

public abstract class BaseMobileTest {

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


    }

    @BeforeMethod (description = "Base before test")
    public void setUp(ITestContext context) {
        AppiumDriverSetup driverSetup = new AppiumDriverSetup();
        XmlTest testngXml = context.getCurrentXmlTest();
        Configuration.proxyHost = String.format("%s/wd/hub", testngXml.getParameter("server_url"));
        DesiredCapabilities caps = driverSetup.getCapabilities(testngXml.getAllParameters());
        driverSetup.createDriver(caps);

        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.browserCapabilities = caps;
        Configuration.browser = AppiumDriverSetup.class.getName();
        Selenide.open();
    }

    @BeforeMethod (description = "Base after test")
    public void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted())
            WebDriverRunner.closeWebDriver();    }
}
