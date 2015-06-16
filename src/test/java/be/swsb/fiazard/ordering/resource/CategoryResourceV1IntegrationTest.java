package be.swsb.fiazard.ordering.resource;

import be.swsb.fiazard.common.Id;
import be.swsb.fiazard.common.error.ErrorR;
import be.swsb.fiazard.common.exceptions.IllegalIdFiazardException;
import be.swsb.fiazard.common.test.ClientRule;
import be.swsb.fiazard.main.FiazardApp;
import be.swsb.fiazard.main.FiazardConfig;
import be.swsb.fiazard.ordering.representation.category.CategoryR;
import com.sun.jersey.api.client.ClientResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static be.swsb.fiazard.ordering.resource.CategoryResourceV1.CATEGORY_BASE_URI;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoryResourceV1IntegrationTest {
    public static final String BASE_URL = "http://localhost:8080";

    @ClassRule
    public static final DropwizardAppRule<FiazardConfig> appRule = new DropwizardAppRule<>(FiazardApp.class, "src/main/resources/dev.yml");

    @Rule
    public ClientRule clientRule = new ClientRule();


    @Test
    public void categoriesAreReturnedAsJSON() throws Exception {
        ClientResponse clientResponse = clientRule.getClient()
                .resource(BASE_URL)
                .path(CATEGORY_BASE_URI)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        assertThat(clientResponse.getEntity(CategoryR[].class)).isNotEmpty();
    }

    @Test
    public void getById_WhenCategoryFoundForGivenPathParam_ReturnsCategory() throws Exception {
        ClientResponse clientResponse = clientRule.getClient()
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
        ClientResponse clientResponse = clientRule.getClient()
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
        ClientResponse clientResponse = clientRule.getClient()
                .resource(BASE_URL)
                .path(CATEGORY_BASE_URI)
                .path("notAValidUUID")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        assertThat(clientResponse.getEntity(ErrorR.class)).isEqualTo(ErrorR.from(new IllegalIdFiazardException("notAValidUUID")));
    }
}