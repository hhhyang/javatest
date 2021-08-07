package com.javatest.framework.commons.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


public class TimeUtils {

    public static LocalDateTime getDateTimeOfEpochSecond(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static LocalDateTime getDateTimeofEpochMilli(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

}
