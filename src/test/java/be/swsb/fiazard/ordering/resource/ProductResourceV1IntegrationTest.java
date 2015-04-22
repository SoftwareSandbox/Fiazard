package be.swsb.fiazard.ordering.resource;

import static be.swsb.fiazard.ordering.resource.ProductResourceV1.PRODUCTS_BASE_URI;
import static org.assertj.core.api.Assertions.assertThat;

import be.swsb.fiazard.ordering.representation.product.ProductR;
import io.dropwizard.testing.junit.DropwizardAppRule;

import javax.ws.rs.core.MediaType;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import be.swsb.fiazard.common.error.ErrorR;
import be.swsb.fiazard.common.exceptions.IllegalIdFiazardException;
import be.swsb.fiazard.common.test.ClientRule;
import be.swsb.fiazard.main.FiazardApp;
import be.swsb.fiazard.main.FiazardConfig;

import com.sun.jersey.api.client.ClientResponse;

public class ProductResourceV1IntegrationTest {
	public static final String BASE_URL = "http://localhost:8080";

	@ClassRule
    public static final DropwizardAppRule<FiazardConfig> appRule = new DropwizardAppRule<>(FiazardApp.class, "src/main/resources/dev.yml");
	
	@Rule
	public ClientRule clientRule = new ClientRule();

    @Test
    public void getByCategoryId_WhenIdIsNotAUUID_ReturnsInvalidIdError() throws Exception {
        ClientResponse clientResponse = clientRule.getClient()
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
        ClientResponse clientResponse = clientRule.getClient()
                .resource(BASE_URL)
                .path(PRODUCTS_BASE_URI)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        assertThat(clientResponse.getEntity(ProductR[].class)).hasSize(4);
    }

    @Test
    public void getByCategoryId_WhenCategoryIdGiven_ReturnsAllProductsOfGivenCategoryId() throws Exception {
        ClientResponse clientResponse = clientRule.getClient()
                .resource(BASE_URL)
                .path(PRODUCTS_BASE_URI)
                .queryParam("categoryId", CategoryResourceV1.cheese.getId())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        assertThat(clientResponse.getEntity(ProductR[].class)).hasSize(2);
    }
}