package com.somosmas.app.util;

import java.sql.Timestamp;
import java.time.Instant;

public class TimestampUtil {

    private TimestampUtil() {

    }

    public static Timestamp getCurrentTime() {
        return Timestamp.from(Instant.now());
    }
}
