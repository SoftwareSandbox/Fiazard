package be.swsb.fiazard.util.representation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class LocalDateTimeUtil {

    public static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            .appendLiteral("Z")
            .toFormatter();


    public static LocalDateTime toLocalDateTime(String string) {
        if (string == null) {
            return null;
        }
        return LocalDateTime.parse(string, FORMATTER);
    }

    public static String toString(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.format(FORMATTER);
    }
}
