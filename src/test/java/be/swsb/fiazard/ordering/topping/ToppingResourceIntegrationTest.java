package be.swsb.fiazard.ordering.topping;

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

public class ToppingResourceIntegrationTest {

    public static final String BASE_URL = "http://localhost:8080";
    public static final String TOPPING_PATH = "/ordering/topping";
    private static final String LOCK_TOPPING_PATH = "/ordering/topping/lock";
    private static final String UNLOCK_TOPPING_PATH = "/ordering/topping/unlock";
    private static final String EXCLUDE_TOPPING_PATH = "/ordering/topping/exclude";

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
        mongoDBRule.persist(new Topping(null, "Patrick", 4d, "image", "imageType"));

        Topping[] clientResponse = clientRule.getClient()
                .target(BASE_URL)
                .path(TOPPING_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(Topping[].class);

        assertThat(clientResponse).isNotEmpty();
    }

    @Test
    public void lock_ToppingLockedEventIsStored() throws Exception {
        Topping topping = new Topping("id", "someTopping", 4, "image", "imageType");

        Response clientResponse = clientRule.getClient()
                .target(BASE_URL)
                .path(LOCK_TOPPING_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(topping));

        assertThat(clientResponse.getStatusInfo().getStatusCode()).isEqualTo(Response.Status.OK.getStatusCode());

        List<Event> events = eventStore.findAll();
        assertThat(events).hasSize(1);

        ToppingLockedEvent toppingLockedEvent = (ToppingLockedEvent) events.get(0);
        assertThat(toppingLockedEvent.getTopping()).isEqualTo(topping);
        assertThat(toppingLockedEvent.getId()).isNotNull();
        assertThat(toppingLockedEvent.getTimestamp()).isNotNull();
    }

    @Test
    public void unlock_ToppingUnlockedEventIsStored() throws Exception {
        Topping topping = new Topping("id", "someTopping", 4, "image", "imageType");

        Response clientResponse = clientRule.getClient()
                .target(BASE_URL)
                .path(UNLOCK_TOPPING_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(topping));

        assertThat(clientResponse.getStatusInfo().getStatusCode()).isEqualTo(Response.Status.OK.getStatusCode());

        List<Event> events = eventStore.findAll();
        assertThat(events).hasSize(1);

        ToppingUnlockedEvent toppingUnlockedEvent = (ToppingUnlockedEvent) events.get(0);
        assertThat(toppingUnlockedEvent.getTopping()).isEqualTo(topping);
        assertThat(toppingUnlockedEvent.getId()).isNotNull();
        assertThat(toppingUnlockedEvent.getTimestamp()).isNotNull();
    }

    @Test
    public void exclude_ToppingExcludeEventStored() {
        Topping topping = new Topping("id", "someTopping", 4, "image", "imageType");

        Response clientResponse = clientRule.getClient()
                .target(BASE_URL)
                .path(EXCLUDE_TOPPING_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(topping));

        assertThat(clientResponse.getStatusInfo().getStatusCode()).isEqualTo(Response.Status.OK.getStatusCode());

        List<Event> events = eventStore.findAll();
        assertThat(events).hasSize(1);

        ToppingExcludeEvent event = (ToppingExcludeEvent)events.get(0);
        assertThat(event.getTopping()).isEqualTo(topping);
        assertThat(event.getId()).isNotNull();
        assertThat(event.getTimestamp()).isNotNull();
    }
}
