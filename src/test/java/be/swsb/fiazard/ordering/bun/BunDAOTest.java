package be.swsb.fiazard.ordering.bun;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import be.swsb.fiazard.common.mongo.MongoDBRule;

public class BunDAOTest {

    private static final String NAME = "rudy";
    private static final double PRICE = 12d;
    private static final String ID = "123";

    @Rule
    public MongoDBRule mongoDBRule = MongoDBRule.create();

    private BunDAO dao;

    @Before
    public void setUp() {
        dao = new BunDAO(mongoDBRule.getDB());
    }

    @Test
    public void findAll_ReturnsAllBuns() {
        persistBun(ID, NAME, PRICE);

        List<Bun> buns = dao.findAll();

        assertThat(buns).hasSize(1);
        assertThat(buns.get(0).getId()).isEqualTo(ID);
        assertThat(buns.get(0).getName()).isEqualTo(NAME);
        assertThat(buns.get(0).getPrice()).isEqualTo(PRICE);
    }

    private void persistBun(String id, String name, double price) {
        mongoDBRule.persist(new Bun(id, name, price));
    }

}