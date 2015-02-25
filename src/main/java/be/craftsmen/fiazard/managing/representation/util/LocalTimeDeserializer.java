package be.craftsmen.fiazard.managing.representation.util;

import static be.craftsmen.fiazard.managing.representation.util.LocalTimeUtil.FORMATTER;

import java.io.IOException;
import java.time.LocalTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalTimeDeserializer extends JsonDeserializer<LocalTime>{

	@Override
	public LocalTime deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		return LocalTime.parse(jp.getValueAsString(), FORMATTER);
	}

}
