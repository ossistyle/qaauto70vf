package com.verifone.utils.appUtils;

import com.verifone.tests.BaseTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
}
