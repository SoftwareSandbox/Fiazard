package be.swsb.fiazard.ordering.orderplacement;

import be.swsb.fiazard.common.Identifiable;
import be.swsb.fiazard.common.test.ClientRule;
import be.swsb.fiazard.main.FiazardApp;
import be.swsb.fiazard.main.FiazardConfig;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderingResourceIntegrationTest {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String ORDERING_BASE_URI = "/ordering";

    @ClassRule
    public static final DropwizardAppRule<FiazardConfig> appRule =
            new DropwizardAppRule<>(FiazardApp.class,
                    "src/test/resources/test.yml");

    @Rule
    public ClientRule clientRule = new ClientRule();

    @Test
    @Ignore("until eventstore can be mocked/integrated")
    public void toppingsAreReturnedAsJSON() throws Exception {
        PlaceOrder placeOrder = new PlaceOrder("snarf");
        Identifiable id = clientRule.getClient()
                .target(BASE_URL)
                .path(ORDERING_BASE_URI + "/placeorder")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(placeOrder), Identifiable.class);

        assertThat(id.getId()).isNotNull();

        assertOrderPlacedEventPersisted(id.getId());
    }

    private void assertOrderPlacedEventPersisted(String orderId) {
        //TODO sch3lp: figure out a way to integrationtest with eventstore on travis? Issue #55
    }

}
