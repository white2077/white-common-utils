package common;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    private DateTimeUtils() {
    }

    public static String format(LocalDateTime date, String pattern) {
        if (date == null || pattern == null || pattern.isBlank()) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    public static LocalDateTime parse(String dateStr, String pattern) {
        if (dateStr == null || pattern == null || pattern.isBlank()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateStr, formatter);
    }


    public static String nowAsString(String pattern) {
        return format(LocalDateTime.now(), pattern);
    }

    public static Duration between(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            return Duration.ZERO;
        }
        return Duration.between(start, end);
    }

}
