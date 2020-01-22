package test.api;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import utils.allure.AllureCommon;

import java.util.ResourceBundle;

public class BaseApiTest {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ResourceBundle testData;
    protected Gson jsonParser = new Gson();

    //protected final String qa_free_app = "776a8141-73ed-4ec3-a993-7baa2be09b65";
    //protected final String qa_free_app_1 = "0b9a8ca9-969d-4cf5-a35e-932a7fd8e9f8";

    protected String env;

    @BeforeSuite
    public void configureSuite() {
        //Clear allure-results folder
        AllureCommon.deleteAllureResults();
    }

    @BeforeClass
    @Parameters("env")
    public void initializeVars(String env) {
        this.env = env;
        testData = ResourceBundle.getBundle("testData/" + env.toLowerCase());

    }


}
