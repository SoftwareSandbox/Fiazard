package be.craftsmen.fiazard.managing.resource;

import static be.craftsmen.fiazard.managing.resource.CategoryResourceV1.CATEGORY_BASE_URI;
import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.testing.junit.DropwizardAppRule;

import javax.ws.rs.core.MediaType;

import org.junit.ClassRule;
import org.junit.Test;

import be.craftsmen.fiazard.common.Id;
import be.craftsmen.fiazard.common.error.ErrorR;
import be.craftsmen.fiazard.common.exceptions.IllegalIdFiazardException;
import be.craftsmen.fiazard.main.FiazardApp;
import be.craftsmen.fiazard.main.FiazardConfig;
import be.craftsmen.fiazard.managing.representation.category.CategoryR;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class CategoryResourceV1IntegrationTest {

    @ClassRule
    public static final DropwizardAppRule<FiazardConfig> appRule = new DropwizardAppRule<>(FiazardApp.class, "src/main/resources/dev.yml");
    public static final String BASE_URL = "http://localhost:8080";


    @Test
    public void categoriesAreReturnedAsJSON() throws Exception {
        ClientResponse clientResponse = new Client()
                .resource(BASE_URL)
                .path(CATEGORY_BASE_URI)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        assertThat(clientResponse.getEntity(CategoryR[].class)).isNotEmpty();
    }

    @Test
    public void getById_WhenCategoryFoundForGivenPathParam_ReturnsCategory() throws Exception {
        ClientResponse clientResponse = new Client()
                .resource(BASE_URL)
                .path(CATEGORY_BASE_URI)
                .path(CategoryResourceV1.cheese.getId())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        assertThat(clientResponse.getEntity(CategoryR.class)).isEqualTo(CategoryResourceV1.cheese);
    }

    @Test
    public void getById_WhenNoCategoryFoundForGivenPathParam_Returns404() throws Exception {
        ClientResponse clientResponse = new Client()
                .resource(BASE_URL)
                .path(CATEGORY_BASE_URI)
                .path(Id.asString(Id.random()))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        assertThat(clientResponse.getStatus()).isEqualTo(404);
    }

    @Test
    public void getById_WhenNoValidIdGivenAsPathParam_ReturnsError() throws Exception {
        ClientResponse clientResponse = new Client()
                .resource(BASE_URL)
                .path(CATEGORY_BASE_URI)
                .path("notAValidUUID")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        assertThat(clientResponse.getEntity(ErrorR.class)).isEqualTo(ErrorR.from(new IllegalIdFiazardException("notAValidUUID")));
    }
}