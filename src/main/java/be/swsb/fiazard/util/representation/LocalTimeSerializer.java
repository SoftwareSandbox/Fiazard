package be.swsb.fiazard.util.representation;

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
		
		jgen.writeString(LocalTimeUtil.FORMATTER.format(value));
	}

}
