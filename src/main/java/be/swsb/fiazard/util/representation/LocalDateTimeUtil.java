package be.swsb.fiazard.util.representation;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class LocalDateTimeUtil {
    public static final DateTimeFormatter FORMATTER =
            new DateTimeFormatterBuilder()
                    .appendValue(ChronoField.YEAR)
                    .appendLiteral("/")
                    .appendValue(ChronoField.MONTH_OF_YEAR)
                    .appendLiteral("/")
                    .appendValue(ChronoField.DAY_OF_MONTH)
                    .appendLiteral(" ")
                    .appendValue(ChronoField.HOUR_OF_DAY)
                    .appendLiteral(":")
                    .appendValue(ChronoField.MINUTE_OF_HOUR)
                    .appendLiteral(":")
                    .appendValue(ChronoField.SECOND_OF_MINUTE)
                    .appendLiteral(".")
                    .appendValue(ChronoField.MILLI_OF_SECOND)
                    .toFormatter();

}
