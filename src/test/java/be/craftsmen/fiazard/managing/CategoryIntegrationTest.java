package be.craftsmen.fiazard.managing;

import be.craftsmen.fiazard.main.FiazardApp;
import be.craftsmen.fiazard.main.FiazardConfig;
import be.craftsmen.fiazard.managing.representation.category.CategoryR;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static be.craftsmen.fiazard.managing.resource.CategoryResourceV1.CATEGORY_BASE_URI;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoryIntegrationTest {

    @ClassRule
    public static final DropwizardAppRule<FiazardConfig> appRule = new DropwizardAppRule<>(FiazardApp.class, "src/main/resources/dev.yml");
    public static final String BASE_URL = "http://localhost:8080";


    @Test
    public void categoriesAreReturnedAsJSON() throws Exception {
        ClientResponse clientResponse = new Client()
                .resource(BASE_URL + CATEGORY_BASE_URI)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        assertThat(clientResponse.getEntity(CategoryR[].class)).isNotEmpty();
    }
}