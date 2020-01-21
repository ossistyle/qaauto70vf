package utils.allure;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    /**
     * Clear target/allure-results folder
     */
    public static void deleteAllureResults() {
        String allureResultsFolder = "target/allure-results";
        if (Files.exists(Paths.get(allureResultsFolder))) {
            try {
                FileUtils.cleanDirectory(new File(allureResultsFolder));
            } catch (IOException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}
