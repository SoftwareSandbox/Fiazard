package be.swsb.fiazard.util.representation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class DayOfWeekDeserializerTest {
    private static final DayOfWeek DAY_OF_WEEK = DayOfWeek.MONDAY;

    @Mock
    private DeserializationContext contextMock;
    @Mock
    private JsonParser parserMock;

    private DayOfWeekDeserializer deserializer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        deserializer = new DayOfWeekDeserializer();
    }

    @Test
    public void deserialize_GetsIntFieldToParseDayOfWeek() throws Exception {
        when(parserMock.getIntValue()).thenReturn(DAY_OF_WEEK.getValue());

        DayOfWeek actual = deserializer.deserialize(parserMock, contextMock);

        assertThat(actual).isEqualTo(DAY_OF_WEEK);
    }

}
