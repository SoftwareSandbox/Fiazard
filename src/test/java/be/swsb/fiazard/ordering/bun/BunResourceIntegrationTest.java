package be.swsb.fiazard.ordering.bun;

import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.testing.junit.DropwizardAppRule;

import javax.ws.rs.core.MediaType;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import be.swsb.fiazard.common.mongo.MongoDBRule;
import be.swsb.fiazard.common.test.ClientRule;
import be.swsb.fiazard.main.FiazardApp;
import be.swsb.fiazard.main.FiazardConfig;

import com.sun.jersey.api.client.ClientResponse;

public class BunResourceIntegrationTest {
	
    public static final String BASE_URL = "http://localhost:8080";
    public static final String BUN_PATH = "/ordering/bun";

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
        mongoDBRule.persist(new Bun(null, "Patrick", 4d));

        ClientResponse clientResponse = clientRule.getClient()
                .resource(BASE_URL)
                .path(BUN_PATH)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        assertThat(clientResponse.getEntity(Bun[].class)).isNotEmpty();
    }
}