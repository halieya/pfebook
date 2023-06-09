package com.cubeitcorp.navit.utils;


import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class TimestampUtils {

    private static final DateFormat ISO8601_DATEFORMAT_UTC;


    static {

        // In order to persist the timestamps for the match event, we will use an ISO8601 formatter
        // that will set the timestamps in UTC and format them accordingly
        final DateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        ISO8601_DATEFORMAT_UTC = dateFormat;

    }


    public static String computeISO8601Timestamp() {
        return ISO8601_DATEFORMAT_UTC.format(Calendar.getInstance().getTime());
    }



    private TimestampUtils() {
        super();
    }

}
