package be.swsb.fiazard.ordering.preparation;

import be.swsb.fiazard.common.mongo.MongoDBRule;
import be.swsb.fiazard.common.test.ClientRule;
import be.swsb.fiazard.main.FiazardApp;
import be.swsb.fiazard.main.FiazardConfig;
import be.swsb.fiazard.util.representation.LocalDateTimeUtil;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.HashMap;

import static be.swsb.fiazard.ddd.AggregateIdTestBuilder.randomId;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderItemToBePreparedViewModelResourceIntegrationTest {

    public static final LocalDateTime DATE_TIME1 = toDate("2015-04-21T15:00:17.123Z");
    public static final LocalDateTime DATE_TIME2 = toDate("2015-04-22T15:00:17.123Z");
    public static final LocalDateTime DATE_TIME3 = toDate("2015-04-23T15:00:17.123Z");

    public static final String DESCRIPTION_1 = "someDescription1";
    public static final String DESCRIPTION_2 = "someDescription2";
    public static final String DESCRIPTION_3 = "someDescription3";

    private static final String BASE_URL = "http://localhost:8080";
    private static final String PATH = "/ordering/itemsToBePrepared";

    @ClassRule
    public static final DropwizardAppRule<FiazardConfig> appRule =
            new DropwizardAppRule<>(FiazardApp.class,
                    "src/test/resources/test.yml");
    @Rule
    public MongoDBRule mongoDBRule = MongoDBRule.create();

    @Rule
    public ClientRule clientRule = new ClientRule();

    @Test
    public void findWithoutQueryParams_ReturnsOrderItemsAsJSON() throws Exception {
        OrderItemToBePreparedViewModel item1 = orderItemToBePrepared("1", DATE_TIME1, DESCRIPTION_1);
        OrderItemToBePreparedViewModel item2 = orderItemToBePrepared("2", DATE_TIME2, DESCRIPTION_2);
        OrderItemToBePreparedViewModel item3 = orderItemToBePrepared("3", DATE_TIME3, DESCRIPTION_3);
        mongoDBRule.persistAll(item1, item2, item3);

        OrderItemToBePreparedViewModel[] foundOrderItems = queryForOrderItems(noParams());

        assertThat(foundOrderItems).hasSize(3);
    }

    @Test
    public void findWithOnlyDateFromQueryParam_FiltersOrderItems() throws Exception {
        OrderItemToBePreparedViewModel item1 = orderItemToBePrepared("1", DATE_TIME1, DESCRIPTION_1);
        OrderItemToBePreparedViewModel item2 = orderItemToBePrepared("2", DATE_TIME2, DESCRIPTION_2);
        OrderItemToBePreparedViewModel item3 = orderItemToBePrepared("3", DATE_TIME3, DESCRIPTION_3);
        mongoDBRule.persistAll(item1, item2, item3);

        OrderItemToBePreparedViewModel[] foundOrderItems = queryForOrderItems(queryParams(DATE_TIME1.plusSeconds(1), null));
        assertThat(foundOrderItems).hasSize(2);
    }

    @Test
    public void findWithOnlyDateUntilQueryParam_FiltersOrderItems() throws Exception {
        OrderItemToBePreparedViewModel item1 = orderItemToBePrepared("1", DATE_TIME1, DESCRIPTION_1);
        OrderItemToBePreparedViewModel item2 = orderItemToBePrepared("2", DATE_TIME2, DESCRIPTION_2);
        OrderItemToBePreparedViewModel item3 = orderItemToBePrepared("3", DATE_TIME3, DESCRIPTION_3);
        mongoDBRule.persistAll(item1, item2, item3);

        OrderItemToBePreparedViewModel[] foundOrderItems = queryForOrderItems(queryParams(null, DATE_TIME3.minusSeconds(1)));
        assertThat(foundOrderItems).hasSize(2);
    }

    private OrderItemToBePreparedViewModel[] queryForOrderItems(HashMap<String, String> params) {
        JerseyWebTarget target = clientRule.getClient()
                .target(BASE_URL)
                .path(PATH);
        params.forEach(target::queryParam);
        return target.request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(OrderItemToBePreparedViewModel[].class);
    }

    private OrderItemToBePreparedViewModel orderItemToBePrepared(String id, LocalDateTime dateTime, String description) {
        return new OrderItemToBePreparedViewModel(id, randomId(), dateTime, description);
    }

    private static LocalDateTime toDate(String dateTime) {
        return LocalDateTime.parse(dateTime, LocalDateTimeUtil.FORMATTER);
    }

    private HashMap<String, String> queryParams(LocalDateTime dateFrom, LocalDateTime dateUntil) {
        HashMap<String,String> result = new HashMap<>();
        addParamIfNotNull(result, "dateFrom", dateFrom);
        addParamIfNotNull(result, "dateUntil", dateUntil);
        return result;
    }

    private void addParamIfNotNull(HashMap<String,String> result, String key, LocalDateTime value) {
        if (value==null) {
            return;
        }

        result.put(key, LocalDateTimeUtil.toString(value));
    }

    private HashMap<String, String> noParams() {
        return new HashMap<>();
    }

}