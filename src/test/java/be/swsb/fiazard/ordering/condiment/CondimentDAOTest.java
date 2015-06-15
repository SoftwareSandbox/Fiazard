package be.swsb.fiazard.ordering.condiment;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import be.swsb.fiazard.common.mongo.MongoDBRule;

public class CondimentDAOTest {

    private static final String NAME = "rudy";
    private static final double PRICE = 12d;
    private static final String ID = "123";

    @Rule
    public MongoDBRule mongoDBRule = MongoDBRule.create();

    private CondimentDAO dao;

    @Before
    public void setUp() {
        dao = new CondimentDAO(mongoDBRule.getDB());
    }

    @Test
    public void findAll_ReturnsAllContiments() {
        persistCondiment(ID, NAME, PRICE);

        List<Condiment> condiments = dao.findAll();

        assertThat(condiments).hasSize(1);
        assertThat(condiments.get(0).getId()).isEqualTo(ID);
        assertThat(condiments.get(0).getName()).isEqualTo(NAME);
        assertThat(condiments.get(0).getPrice()).isEqualTo(PRICE);
    }

    private void persistCondiment(String id, String name, double price) {
        mongoDBRule.persist(new Condiment(id, name, price));
    }

}