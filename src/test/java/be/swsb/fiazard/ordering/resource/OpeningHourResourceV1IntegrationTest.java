package be.swsb.fiazard.ordering.resource;

import static org.assertj.core.api.Assertions.assertThat;

import be.swsb.fiazard.main.FiazardApp;
import io.dropwizard.testing.junit.DropwizardAppRule;

import javax.ws.rs.core.MediaType;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import be.swsb.fiazard.common.test.ClientRule;
import be.swsb.fiazard.main.FiazardConfig;
import be.swsb.fiazard.ordering.representation.openinghour.OpeningHourR;

import com.sun.jersey.api.client.ClientResponse;

public class OpeningHourResourceV1IntegrationTest {
	public static final String BASE_URL = "http://localhost:8080";
	
	@ClassRule
	public static final DropwizardAppRule<FiazardConfig> appRule =
			new DropwizardAppRule<>(FiazardApp.class,
					"src/main/resources/dev.yml");
	
	@Rule
	public ClientRule clientRule = new ClientRule();

	@Test
	public void openingHoursAreReturnedAsJSON() throws Exception {
		ClientResponse clientResponse = clientRule.getClient()
				.resource(BASE_URL)
				.path(OpeningHourResourceV1.OPENING_HOUR_BASE_URI)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.get(ClientResponse.class);

		assertThat(clientResponse.getEntity(OpeningHourR[].class)).isNotEmpty();
	}
}
