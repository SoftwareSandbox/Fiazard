package be.craftsmen.fiazard.managing.representation.util;

import java.io.IOException;
import java.time.DayOfWeek;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DayOfWeekDeserializer extends JsonDeserializer<DayOfWeek>{

	@Override
	public DayOfWeek deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		return DayOfWeek.of(jp.getIntValue());
	}

}
