package be.swsb.fiazard.util.representation;

import static org.mockito.Mockito.verify;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

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
	public void serialize_SerializesToCorrectFormat() throws Exception{
		serializer.serialize(TIME, generatorMock, providerMock);
		
		verify(generatorMock).writeString("10:15");
	}

}
