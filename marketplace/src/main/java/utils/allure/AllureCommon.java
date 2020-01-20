package utils.allure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public abstract class AllureCommon {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllureCommon.class);

    /**
     * Create environment.properties file for Allure report
     * @param props Properties
     */
    public static void addAllureEnvProperties(Properties props) {
        File f = new File("target/allure-results/environment.properties");

        try {
            if (f.createNewFile()) {
                OutputStream out = new FileOutputStream(f);
                props.store(out, "Allure report Environment variables");
            } else {
                LOGGER.warn("Allure environment.properties file exists");
            }
        } catch (Exception e) {
            LOGGER.error("Allure environment.properties file was not created");
        }
    }
}
