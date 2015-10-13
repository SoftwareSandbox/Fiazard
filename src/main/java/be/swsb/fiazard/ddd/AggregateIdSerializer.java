package be.swsb.fiazard.ddd;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.DayOfWeek;

public class AggregateIdSerializer extends JsonSerializer<AggregateId> {
    @Override
    public void serialize(AggregateId id, JsonGenerator jgen,
                          SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(id.getValue());
    }

}
