package be.craftsmen.fiazard.managing.representation.util;

import static be.craftsmen.fiazard.managing.representation.util.LocalTimeUtil.FORMATTER;

import java.io.IOException;
import java.time.LocalTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalTimeSerializer extends JsonSerializer<LocalTime> {
	@Override
	public void serialize(LocalTime value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		
		jgen.writeString(FORMATTER.format(value));
	}

}
