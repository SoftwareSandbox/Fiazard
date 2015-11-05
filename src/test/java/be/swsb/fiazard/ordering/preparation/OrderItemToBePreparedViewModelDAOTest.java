package be.swsb.fiazard.ordering.preparation;

import be.swsb.fiazard.common.mongo.MongoDBRule;
import be.swsb.fiazard.ddd.AggregateId;
import be.swsb.fiazard.ddd.AggregateIdTestBuilder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderItemToBePreparedViewModelDAOTest {

    @Rule
    public MongoDBRule mongoDBRule = MongoDBRule.create();

    private static final String ID = "123";
    private static final AggregateId ORDER_ID = AggregateIdTestBuilder.randomId();
    private static final LocalDateTime NOW = LocalDateTime.now();
    private static final String DESCRIPTION = "een lekker broodje";

    private OrderItemToBePreparedViewModelDAO dao;

    @Before
    public void setUp() {
        dao = new OrderItemToBePreparedViewModelDAO(mongoDBRule.getDB());
    }

    @Test
    public void findAll() {
        mongoDBRule.persist(new OrderItemToBePreparedViewModel(ID, ORDER_ID, NOW, DESCRIPTION));

        List<OrderItemToBePreparedViewModel> orderItems = dao.findAll();

        assertThat(orderItems).hasSize(1);
        assertThat(orderItems.get(0).getId()).isEqualTo(ID);
        assertThat(orderItems.get(0).getOrderId()).isEqualTo(ORDER_ID);
        assertThat(orderItems.get(0).getDeliveryDateTime()).isEqualTo(NOW);
        assertThat(orderItems.get(0).getDescription()).isEqualTo(DESCRIPTION);
    }

}