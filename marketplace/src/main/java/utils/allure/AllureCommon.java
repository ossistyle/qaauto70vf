package utils.allure;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public abstract class AllureCommon {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllureCommon.class);
    private static final String ALLURE_RESULTS_FOLDER = "target/allure-results";

    /**
     * Create environment.properties file for Allure report
     * @param props Properties
     */
    public static void addAllureEnvProperties(Properties props) {
        File f = new File(ALLURE_RESULTS_FOLDER + "/environment.properties");

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
        if (Files.exists(Paths.get(ALLURE_RESULTS_FOLDER))) {
            try {
                LOGGER.info(String.format("Cleaning %s folder", ALLURE_RESULTS_FOLDER));
                FileUtils.cleanDirectory(new File(ALLURE_RESULTS_FOLDER));
            } catch (IOException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Create open_report.bat file in generate directory
     */
    public static void createAllureOpenBatFile() {
        String script = "cd .. && allure generate --clean && allure open";
        Path path = Paths.get(ALLURE_RESULTS_FOLDER, "_open_report.bat");

        try {
            // Create allure-results dir if not exists
            if (!Files.exists(Paths.get(ALLURE_RESULTS_FOLDER))) {
                Files.createDirectory(Paths.get(ALLURE_RESULTS_FOLDER));
            }

            LOGGER.info("Create open-report.bat file in " + ALLURE_RESULTS_FOLDER + "folder");
            Files.createFile(path);

            LOGGER.info("Write into open-report.bat file: " + script);
            Files.write(path, script.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
