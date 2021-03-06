package be.swsb.fiazard.ordering.condiment;

import be.swsb.fiazard.common.mongo.MongoDBRule;
import be.swsb.fiazard.common.test.ClientRule;
import be.swsb.fiazard.main.FiazardApp;
import be.swsb.fiazard.main.FiazardConfig;
import com.sun.jersey.api.client.ClientResponse;
import static org.assertj.core.api.Assertions.assertThat;

import be.swsb.fiazard.ordering.bun.Bun;
import be.swsb.fiazard.ordering.bun.BunExcludeEvent;
import io.dropwizard.testing.junit.DropwizardAppRule;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import be.swsb.fiazard.common.eventsourcing.Event;
import be.swsb.fiazard.common.eventsourcing.EventStore;
import be.swsb.fiazard.common.mongo.MongoDBRule;
import be.swsb.fiazard.common.test.ClientRule;
import be.swsb.fiazard.main.FiazardApp;
import be.swsb.fiazard.main.FiazardConfig;
import javax.ws.rs.core.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class CondimentResourceIntegrationTest {
	
    private static final String BASE_URL = "http://localhost:8080";
    private static final String CONDIMENT_PATH = "/ordering/condiment";
    private static final String LOCK_CONDIMENT_PATH = "/ordering/condiment/lock";
    private static final String UNLOCK_CONDIMENT_PATH = "/ordering/condiment/unlock";
    private static final String EXCLUDE_CONDIMENT_PATH = "/ordering/condiment/exclude";


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
        mongoDBRule.persist(new Condiment(null, "Patrick", 4d, "image", "imageType"));

        ClientResponse clientResponse = clientRule.getClient()
                .resource(BASE_URL)
                .path(CONDIMENT_PATH)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        assertThat(clientResponse.getEntity(Condiment[].class)).isNotEmpty();
    }
    

    @Test
    public void lock_CondimentLockedEventIsStored() throws Exception {
    	Condiment condiment = new Condiment("id", "someCondiment", 4, "image", "imageType");
    	
    	ClientResponse clientResponse = clientRule.getClient()
    			.resource(BASE_URL)
    			.path(LOCK_CONDIMENT_PATH)
    			.type(MediaType.APPLICATION_JSON_TYPE)
    			.accept(MediaType.APPLICATION_JSON_TYPE)
    			.entity(condiment)
    			.post(ClientResponse.class);
    	
    	assertThat(clientResponse.getStatusInfo().getStatusCode()).isEqualTo(ClientResponse.Status.OK.getStatusCode());
    	
		List<Event> events = eventStore.findAll();
        assertThat(events).hasSize(1);
        
        CondimentLockedEvent condimentLockedEvent = (CondimentLockedEvent) events.get(0);
        assertThat(condimentLockedEvent.getCondiment()).isEqualTo(condiment);
        assertThat(condimentLockedEvent.getId()).isNotNull();
        assertThat(condimentLockedEvent.getTimestamp()).isNotNull();
	}
    
    @Test
    public void unlock_CondimentUnlockedEventIsStored() throws Exception {
    	Condiment condiment = new Condiment("id", "someCondiment", 4, "image", "imageType");
    	
    	ClientResponse clientResponse = clientRule.getClient()
    			.resource(BASE_URL)
    			.path(UNLOCK_CONDIMENT_PATH)
    			.type(MediaType.APPLICATION_JSON_TYPE)
    			.accept(MediaType.APPLICATION_JSON_TYPE)
    			.entity(condiment)
    			.post(ClientResponse.class);
    	
    	assertThat(clientResponse.getStatusInfo().getStatusCode()).isEqualTo(ClientResponse.Status.OK.getStatusCode());
    	
    	List<Event> events = eventStore.findAll();
    	assertThat(events).hasSize(1);
    	
    	CondimentUnlockedEvent condimentUnlockedEvent = (CondimentUnlockedEvent) events.get(0);
    	assertThat(condimentUnlockedEvent.getCondiment()).isEqualTo(condiment);
    	assertThat(condimentUnlockedEvent.getId()).isNotNull();
    	assertThat(condimentUnlockedEvent.getTimestamp()).isNotNull();
    }

    @Test
    public void exclude_CondimentExcludeEventStored() {
        Condiment condiment = new Condiment("id", "someCondiment", 4, "image", "imageType");

        ClientResponse clientResponse = clientRule.getClient()
                .resource(BASE_URL)
                .path(EXCLUDE_CONDIMENT_PATH)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .entity(condiment)
                .post(ClientResponse.class);

        assertThat(clientResponse.getStatusInfo().getStatusCode()).isEqualTo(ClientResponse.Status.OK.getStatusCode());

        List<Event> events = eventStore.findAll();
        assertThat(events).hasSize(1);

        CondimentExcludeEvent event = (CondimentExcludeEvent)events.get(0);
        assertThat(event.getCondiment()).isEqualTo(condiment);
        assertThat(event.getId()).isNotNull();
        assertThat(event.getTimestamp()).isNotNull();
    }
}
