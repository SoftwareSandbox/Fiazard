package be.swsb.fiazard.util.representation;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class LocalTimeUtil {
	public static final DateTimeFormatter FORMATTER =
			new DateTimeFormatterBuilder()
					.appendValue(ChronoField.HOUR_OF_DAY)
					.appendLiteral(":")
					.appendValue(ChronoField.MINUTE_OF_HOUR)
					.toFormatter();

}
