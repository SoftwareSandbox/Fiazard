package be.swsb.fiazard.ordering.orderplacement;

import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.testing.junit.DropwizardAppRule;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import be.swsb.fiazard.common.Identifiable;
import be.swsb.fiazard.common.eventsourcing.Event;
import be.swsb.fiazard.common.eventsourcing.EventStore;
import be.swsb.fiazard.common.mongo.MongoDBRule;
import be.swsb.fiazard.common.test.ClientRule;
import be.swsb.fiazard.main.FiazardApp;
import be.swsb.fiazard.main.FiazardConfig;

import com.sun.jersey.api.client.ClientResponse;

public class OrderingResourceV1IntegrationTest {
	
    public static final String BASE_URL = "http://localhost:8080";

    @ClassRule
    public static final DropwizardAppRule<FiazardConfig> appRule =
            new DropwizardAppRule<>(FiazardApp.class,
                    "src/test/resources/test.yml");
    @Rule
    public MongoDBRule mongoDBRule = MongoDBRule.create();

    @Rule
    public ClientRule clientRule = new ClientRule();
    
    private EventStore eventStore;

    @Before
    public void setUp() {
    	eventStore = new EventStore(mongoDBRule.getDB());
    }
    
    @Test
    public void toppingsAreReturnedAsJSON() throws Exception {
    	PlaceOrder placeOrder = new PlaceOrder(new ArrayList<Sandwich>());
        ClientResponse clientResponse = clientRule.getClient()
                .resource(BASE_URL)
                .path(OrderingResourceV1.ORDERING_BASE_URI + "/placeorder")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .entity(placeOrder)
                .post(ClientResponse.class);

        Identifiable id = clientResponse.getEntity(Identifiable.class);
        assertThat(id.getId()).isNotNull();
        
        assertOrderPlacedEventPersisted(id.getId());
    }

	private void assertOrderPlacedEventPersisted(String orderId) {
		List<Event> events = eventStore.findAll();
        assertThat(events).hasSize(1);
        OrderPlaced orderPlaced = (OrderPlaced) events.get(0);
        assertThat(orderPlaced.getOrderId()).isEqualTo(orderId);
	}
    
}
