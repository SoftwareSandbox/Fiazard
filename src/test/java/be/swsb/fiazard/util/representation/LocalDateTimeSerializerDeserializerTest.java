package be.swsb.fiazard.util.representation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocalDateTimeSerializerDeserializerTest {

    @Mock
    private DeserializationContext contextMock;
    @Mock
    private JsonParser jsonParserMock;
    @Mock
    private SerializerProvider serializerProviderMock;

    private LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer();
    private LocalDateTimeSerializer serializer = new LocalDateTimeSerializer();

    @Test
    public void serializeDeserializeReturnsSameLocalDateTime() throws Exception {
        JsonGenerator jsonGeneratorMock = mock(JsonGenerator.class);
        LocalDateTime now = LocalDateTime.now();

        serializer.serialize(now, jsonGeneratorMock, serializerProviderMock);
        String serializedLocalDateTime = getStringWrittenToJson(jsonGeneratorMock);

        when(jsonParserMock.getValueAsString()).thenReturn(serializedLocalDateTime);
        LocalDateTime deserializedLocalDateTime = deserializer.deserialize(jsonParserMock, contextMock);

        assertThat(deserializedLocalDateTime).isEqualTo(now);
    }

    private String getStringWrittenToJson(JsonGenerator jsonGeneratorMock) throws IOException {
        ArgumentCaptor<String> stringCapturer = ArgumentCaptor.forClass(String.class);
        verify(jsonGeneratorMock).writeString(stringCapturer.capture());
        return stringCapturer.getValue();
    }
}