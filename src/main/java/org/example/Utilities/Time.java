package org.example.Utilities;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Time {

    public static long getCurrentTimeInMS() {
        return System.currentTimeMillis();
    }

    public static String getNow(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return dateTime.format(formatter);
    }

    public static String getTimeStamp(){
        return new SimpleDateFormat(("HH:mm:ss")).format(new Date());
    }

    public static String getFileFormattedDate(){
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new Date());
    }
}


