package be.swsb.fiazard.ordering.openinghours;

import be.swsb.fiazard.common.test.ClientRule;
import be.swsb.fiazard.main.FiazardApp;
import be.swsb.fiazard.main.FiazardConfig;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

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
        OpeningHourR[] clientResponse = clientRule.getClient()
                .target(BASE_URL)
                .path(OpeningHourResourceV1.OPENING_HOUR_BASE_URI)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(OpeningHourR[].class);

        assertThat(clientResponse).isNotEmpty();
    }
}
