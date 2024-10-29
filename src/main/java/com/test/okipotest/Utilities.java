package com.test.okipotest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Utilities {

    public static Long convertLocalDateTimeToTimeStamp(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return instant.toEpochMilli();
    }
}
