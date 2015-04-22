package be.swsb.fiazard.util.representation;

import static be.swsb.fiazard.util.representation.LocalTimeUtil.FORMATTER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

public class LocalTimeDeserializerTest {
	private static final LocalTime TIME = LocalTime.now().withSecond(0).withNano(0);

	@Mock
	private DeserializationContext contextMock;
	@Mock
	private JsonParser parserMock;

	private LocalTimeDeserializer deserializer;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		deserializer = new LocalTimeDeserializer();
	}

	@Test
	public void deserialize_GetsStringToParseTime() throws Exception {
		when(parserMock.getValueAsString()).thenReturn(FORMATTER.format(TIME));

		LocalTime actual = deserializer.deserialize(parserMock, contextMock);

		assertThat(actual).isEqualTo(TIME);
	}
}
