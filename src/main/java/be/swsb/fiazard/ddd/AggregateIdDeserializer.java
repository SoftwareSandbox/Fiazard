package be.swsb.fiazard.ddd;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.DayOfWeek;

public class AggregateIdDeserializer extends JsonDeserializer<AggregateId> {

    @Override
    public AggregateId deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        return new AggregateId(jp.getValueAsString());
    }

}
