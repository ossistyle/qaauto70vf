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
    private static String userDir = BaseTest.envConfig.getAppsDirectoryPath();
    public static String imagePath = userDir + File.separator + "apps" + File.separator + "image.jpg";

    public static String privacyPolicy = "https://www.commbank.com.au/security-privacy/general-security/privacy.html";
    public static String termsAndConditions = "https://www.samplestore.com/legal/terms_of_use_mobile";
    public static String missingMandatoryErrorMsg = "Please check your form values and try submitting again.";
    public static String splash = "A Test App for CBA";
    public static String videoURL = "https://www.youtube.com/watch?v=jWFcGoGgnu4";
    public static String documentLink = "https://help.appdirect.com/appmarket/Default.htm#GettingStarted/gsg-intro.htm%3FTocPath%3DGetting%2520Started%7C_____0/?location%20=%20appmarket";
    public static String headerInfo = "Manage Platform";

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

    /**
     * This method describe to get the size of the element depending on visibility.
     *
     * @author : Prashant Lokhande
     */
    public static int isElementExists(WebDriver driver, By loc, int timeout) throws Exception {
        Thread.sleep(timeout);
        List<WebElement> val = driver.findElements(loc);
        return val.size();
    }
}
