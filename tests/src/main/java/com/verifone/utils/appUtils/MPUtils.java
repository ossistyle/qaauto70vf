package com.verifone.utils.appUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
}
