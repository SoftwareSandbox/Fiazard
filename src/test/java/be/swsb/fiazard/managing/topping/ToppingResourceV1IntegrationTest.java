package be.swsb.fiazard.managing.topping;

import be.swsb.fiazard.common.mongo.MongoDBRule;
import be.swsb.fiazard.common.test.ClientRule;
import be.swsb.fiazard.main.FiazardApp;
import be.swsb.fiazard.main.FiazardConfig;
import be.swsb.fiazard.managing.topping.Topping;
import be.swsb.fiazard.managing.topping.ToppingResourceV1;

import com.sun.jersey.api.client.ClientResponse;

import io.dropwizard.testing.junit.DropwizardAppRule;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class ToppingResourceV1IntegrationTest {
    public static final String BASE_URL = "http://localhost:8080";

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
        mongoDBRule.persistTopping(new Topping(null, "Patrick", 4d));

        ClientResponse clientResponse = clientRule.getClient()
                .resource(BASE_URL)
                .path(ToppingResourceV1.TOPPINGS_BASE_URI)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        assertThat(clientResponse.getEntity(Topping[].class)).isNotEmpty();
    }
}
