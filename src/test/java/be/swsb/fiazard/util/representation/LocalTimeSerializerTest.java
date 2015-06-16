package be.swsb.fiazard.util.representation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;

import static org.mockito.Mockito.verify;

public class LocalTimeSerializerTest {
    private static final LocalTime TIME = LocalTime.of(10, 15);

    @Mock
    private JsonGenerator generatorMock;
    @Mock
    private SerializerProvider providerMock;

    private LocalTimeSerializer serializer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        serializer = new LocalTimeSerializer();
    }

    @Test
    public void serialize_SerializesToCorrectFormat() throws Exception {
        serializer.serialize(TIME, generatorMock, providerMock);

        verify(generatorMock).writeString("10:15");
    }

}
