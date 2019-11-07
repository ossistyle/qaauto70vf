package com.verifone.utils.appUtils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.verifone.pages.BasePage.testLog;

public class MPUtils {
    /**
     * @method : Method return date and time in GMT +03:00 Time zone.
     * This helps to find the created jobs in VHQ
     */
    public static String getDownloadScheduleTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+03:00"));
        return dateFormat.format(new Date());
    }

    public static boolean validateFile(File file, String fileName, String destDirName, boolean willMove){
        File folder = file;
        //List the files on that folder
        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        //Look for the file in the folder and move it
        for (File listOfFile : listOfFiles) {
                String actualName = listOfFile.getName();
                if(willMove) {
                    if (actualName.startsWith(fileName)) {
                        found = true;
                        MPUtils.moveFile(listOfFile, destDirName);
                    }
                }
            }
        return found;
    }

    public static void moveFile(File file,String destDir){
        String srcFilePath = file.toString();
        String destFilePath = destDir;
        try{
            File srcFile = new File(srcFilePath);
            File destFile = new File(destFilePath);
            FileUtils.moveFileToDirectory(srcFile, destFile,false);
            testLog.info("Moved success from " + srcFilePath + " to " + destFilePath);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
