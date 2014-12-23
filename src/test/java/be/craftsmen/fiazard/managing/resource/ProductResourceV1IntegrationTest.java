package be.craftsmen.fiazard.managing.resource;

import be.craftsmen.fiazard.common.error.ErrorR;
import be.craftsmen.fiazard.common.exceptions.IllegalIdFiazardException;
import be.craftsmen.fiazard.main.FiazardApp;
import be.craftsmen.fiazard.main.FiazardConfig;
import be.craftsmen.fiazard.managing.representation.product.ProductR;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static be.craftsmen.fiazard.managing.resource.ProductResourceV1.PRODUCTS_BASE_URI;
import static org.assertj.core.api.Assertions.*;

public class ProductResourceV1IntegrationTest {

    @ClassRule
    public static final DropwizardAppRule<FiazardConfig> appRule = new DropwizardAppRule<>(FiazardApp.class, "src/main/resources/dev.yml");
    public static final String BASE_URL = "http://localhost:8080";

    @Test
    public void getByCategoryId_WhenIdIsNotAUUID_ReturnsInvalidIdError() throws Exception {
        ClientResponse clientResponse = new Client()
                .resource(BASE_URL)
                .path(PRODUCTS_BASE_URI)
                .queryParam("categoryId","not-a-uuid")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        assertThat(clientResponse.getEntity(ErrorR.class)).isEqualTo(ErrorR.from(new IllegalIdFiazardException("not-a-uuid")));
    }

    @Test
    public void getByCategoryId_WhenNoCategoryIdGiven_ReturnsAllProducts() throws Exception {
        ClientResponse clientResponse = new Client()
                .resource(BASE_URL)
                .path(PRODUCTS_BASE_URI)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        assertThat(clientResponse.getEntity(ProductR[].class)).hasSize(4);
    }

    @Test
    public void getByCategoryId_WhenCategoryIdGiven_ReturnsAllProductsOfGivenCategoryId() throws Exception {
        ClientResponse clientResponse = new Client()
                .resource(BASE_URL)
                .path(PRODUCTS_BASE_URI)
                .queryParam("categoryId", CategoryResourceV1.cheese.getId())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        assertThat(clientResponse.getEntity(ProductR[].class)).hasSize(2);
    }
}