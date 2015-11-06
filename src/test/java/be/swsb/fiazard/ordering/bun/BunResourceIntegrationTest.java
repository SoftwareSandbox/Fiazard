package be.swsb.fiazard.ordering.bun;

import be.swsb.fiazard.common.eventsourcing.Event;
import be.swsb.fiazard.common.eventsourcing.EventStore;
import be.swsb.fiazard.common.mongo.MongoDBRule;
import be.swsb.fiazard.common.test.ClientRule;
import be.swsb.fiazard.main.FiazardApp;
import be.swsb.fiazard.main.FiazardConfig;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.ClientResponse;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BunResourceIntegrationTest {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String BUN_PATH = "/ordering/bun";
    private static final String LOCK_BUN_PATH = "/ordering/bun/lock";
    private static final String UNLOCK_BUN_PATH = "/ordering/bun/unlock";
    private static final String EXCLUDE_BUN_PATH = "/ordering/bun/exclude";

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
    public void getAll_ReturnsBunsAsJSON() throws Exception {
        mongoDBRule.persist(new Bun(null, "Patrick", 4d, "image", "imageType"));

        Bun[] buns = clientRule.getClient()
                .target(BASE_URL)
                .path(BUN_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(Bun[].class);

        assertThat(buns).isNotEmpty();
    }
    
    @Test
    public void lock_BunLockedEventIsStored() throws Exception {
        Bun bun = new Bun("id", "someBun", 4, "image", "imageType");

        ClientResponse clientResponse = clientRule.getClient()
                .target(BASE_URL)
                .path(LOCK_BUN_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(bun), ClientResponse.class);

        assertThat(clientResponse.getStatusInfo().getStatusCode()).isEqualTo(Response.Status.OK.getStatusCode());

        List<Event> events = eventStore.findAll();
        assertThat(events).hasSize(1);

        BunLockedEvent bunLockedEvent = (BunLockedEvent) events.get(0);
        assertThat(bunLockedEvent.getBun()).isEqualTo(bun);
        assertThat(bunLockedEvent.getId()).isNotNull();
        assertThat(bunLockedEvent.getTimestamp()).isNotNull();
	}
    
    @Test
    public void unlock_BunUnlockedEventIsStored() throws Exception {
        Bun bun = new Bun("id", "someBun", 4, "image", "imageType");

        ClientResponse clientResponse = clientRule.getClient()
                .target(BASE_URL)
                .path(UNLOCK_BUN_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(bun), ClientResponse.class);

        assertThat(clientResponse.getStatusInfo().getStatusCode()).isEqualTo(Response.Status.OK.getStatusCode());

        List<Event> events = eventStore.findAll();
        assertThat(events).hasSize(1);

        BunUnlockedEvent bunUnlockedEvent = (BunUnlockedEvent) events.get(0);
        assertThat(bunUnlockedEvent.getBun()).isEqualTo(bun);
        assertThat(bunUnlockedEvent.getId()).isNotNull();
        assertThat(bunUnlockedEvent.getTimestamp()).isNotNull();
    }

    @Test
    public void exclude_BunExcludeEventStored() {
        Bun bun = new Bun("id", "someBun", 4, "image", "imageType");

        ClientResponse clientResponse = clientRule.getClient()
                .target(BASE_URL)
                .path(EXCLUDE_BUN_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(bun), ClientResponse.class);

        assertThat(clientResponse.getStatusInfo().getStatusCode()).isEqualTo(Response.Status.OK.getStatusCode());

        List<Event> events = eventStore.findAll();
        assertThat(events).hasSize(1);

        BunExcludeEvent event = (BunExcludeEvent)events.get(0);
        assertThat(event.getBun()).isEqualTo(bun);
        assertThat(event.getId()).isNotNull();
        assertThat(event.getTimestamp()).isNotNull();
    }
}
