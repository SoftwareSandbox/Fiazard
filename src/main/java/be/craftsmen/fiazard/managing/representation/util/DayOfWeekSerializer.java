package be.craftsmen.fiazard.managing.representation.util;

import java.io.IOException;
import java.time.DayOfWeek;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DayOfWeekSerializer extends JsonSerializer<DayOfWeek> {
	@Override
	public void serialize(DayOfWeek day, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jgen.writeNumber(day.getValue());
	}

}
