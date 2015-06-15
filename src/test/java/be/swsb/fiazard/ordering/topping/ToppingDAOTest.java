package be.swsb.fiazard.ordering.topping;

import be.swsb.fiazard.common.mongo.MongoDBRule;
import be.swsb.fiazard.ordering.topping.Topping;
import be.swsb.fiazard.ordering.topping.ToppingDAO;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ToppingDAOTest {

    private static final String NAME = "rudy";
    private static final double PRICE = 12d;
    private static final String ID = "123";

    @Rule
    public MongoDBRule mongoDBRule = MongoDBRule.create();

    private ToppingDAO dao;

    @Before
    public void setUp() {
        dao = new ToppingDAO(mongoDBRule.getDB());
    }

    @Test
    public void findAll_ReturnsAllToppings() {
        persistTopping(ID, NAME, PRICE);

        List<Topping> toppings = dao.findAll();

        assertThat(toppings).hasSize(1);
        assertThat(toppings.get(0).getId()).isEqualTo(ID);
        assertThat(toppings.get(0).getName()).isEqualTo(NAME);
        assertThat(toppings.get(0).getPrice()).isEqualTo(PRICE);
    }

    private void persistTopping(String id, String name, double price) {
        mongoDBRule.persistTopping(new Topping(id, name, price));
    }

}