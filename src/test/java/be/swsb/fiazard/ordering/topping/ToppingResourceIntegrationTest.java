package be.swsb.fiazard.ordering.topping;

import be.swsb.fiazard.common.mongo.MongoDBRule;
import be.swsb.fiazard.common.test.ClientRule;
import be.swsb.fiazard.main.FiazardApp;
import be.swsb.fiazard.main.FiazardConfig;
import com.sun.jersey.api.client.ClientResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class ToppingResourceIntegrationTest {

    public static final String BASE_URL = "http://localhost:8080";
    public static final String TOPPING_PATH = "/ordering/topping";

    @ClassRule
    public static final DropwizardAppRule<FiazardConfig> appRule =
            new DropwizardAppRule<>(FiazardApp.class,
                    "src/test/resources/test.yml");
    @Rule
    public MongoDBRule mongoDBRule = MongoDBRule.create();

    @Rule
    public ClientRule clientRule = new ClientRule();

    @Test
    public void toppingsAreReturnedAsJSON() throws Exception {
        mongoDBRule.persist(new Topping(null, "Patrick", 4d, "image", "imageType"));

        ClientResponse clientResponse = clientRule.getClient()
                .resource(BASE_URL)
                .path(TOPPING_PATH)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        assertThat(clientResponse.getEntity(Topping[].class)).isNotEmpty();
    }
}
