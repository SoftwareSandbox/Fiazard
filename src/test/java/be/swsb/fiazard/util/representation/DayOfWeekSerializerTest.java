package be.swsb.fiazard.util.representation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;

import static org.mockito.Mockito.verify;

public class DayOfWeekSerializerTest {
    private static final DayOfWeek DAY_OF_WEEK = DayOfWeek.MONDAY;

    @Mock
    private JsonGenerator generatorMock;
    @Mock
    private SerializerProvider serializerProviderMock;

    private DayOfWeekSerializer serializer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        serializer = new DayOfWeekSerializer();
    }

    @Test
    public void serialize_UsesValueFromDayOfWeek() throws Exception {
        serializer.serialize(DAY_OF_WEEK, generatorMock, serializerProviderMock);

        verify(generatorMock).writeNumber(DAY_OF_WEEK.getValue());
    }

}
