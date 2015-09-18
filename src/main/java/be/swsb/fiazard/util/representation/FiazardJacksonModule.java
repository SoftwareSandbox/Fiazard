package be.swsb.fiazard.util.representation;

import be.swsb.fiazard.ddd.AggregateId;
import be.swsb.fiazard.ddd.AggregateIdDeserializer;
import be.swsb.fiazard.ddd.AggregateIdSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class FiazardJacksonModule {
    public static final Module MODULE = new SimpleModule("FiazardModule")
            .addSerializer(DayOfWeek.class, new DayOfWeekSerializer())
            .addDeserializer(DayOfWeek.class, new DayOfWeekDeserializer())
            .addSerializer(LocalTime.class, new LocalTimeSerializer())
            .addDeserializer(LocalTime.class, new LocalTimeDeserializer())
            .addSerializer(AggregateId.class, new AggregateIdSerializer())
            .addDeserializer(AggregateId.class, new AggregateIdDeserializer());

}
