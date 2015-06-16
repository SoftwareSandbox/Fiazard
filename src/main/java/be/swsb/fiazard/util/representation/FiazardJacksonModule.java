package be.swsb.fiazard.util.representation;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class FiazardJacksonModule {
    public static final Module MODULE = new SimpleModule("FiazardModule")
            .addSerializer(DayOfWeek.class, new DayOfWeekSerializer())
            .addDeserializer(DayOfWeek.class, new DayOfWeekDeserializer())
            .addSerializer(LocalTime.class, new LocalTimeSerializer())
            .addDeserializer(LocalTime.class, new LocalTimeDeserializer());

}
