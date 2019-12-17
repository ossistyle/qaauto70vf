package com.verifone.utils.appUtils;

import com.verifone.tests.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.verifone.pages.BasePage.testLog;

public class MPUtils {
    /**
     * @method : Method return date and time in GMT +03:00 Time zone.
     * This helps to find the created jobs in VHQ
     */

    private static String timeZone = BaseTest.envConfig.getDeviceTimeZone();

    public static String getDownloadScheduleTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return dateFormat.format(new Date());
    }

    public static boolean validateFile(File file, String fileName, String destDirName, boolean willMove) {
        File folder = file;
        //List the files on that folder
        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        //Look for the file in the folder and move it
        for (File listOfFile : listOfFiles) {
            String actualName = listOfFile.getName();
            if (willMove) {
                if (actualName.startsWith(fileName)) {
                    found = true;
                    MPUtils.moveFile(listOfFile, destDirName);
                }
            }
        }
        return found;
    }

    public static void moveFile(File file, String destDir) {
        String srcFilePath = file.toString();
        String destFilePath = destDir;
        try {
            File srcFile = new File(srcFilePath);
            File destFile = new File(destFilePath);
            FileUtils.moveFileToDirectory(srcFile, destFile, false);
            testLog.info("Moved success from " + srcFilePath + " to " + destFilePath);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //This method describe to get the size of the element depending on visibility.
    public static int isElementExists(WebDriver driver, By loc, int timeout) throws Exception {
        Thread.sleep(timeout);
        List<WebElement> val = driver.findElements(loc);
        return val.size();
    }
}
