package be.swsb.fiazard.ordering.bun;

import be.swsb.fiazard.common.mongo.MongoDBRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BunDAOTest {

    private static final String NAME = "rudy";
    private static final double PRICE = 12d;
    private static final String ID = "123";
    public static final String IMAGE = "image";
    public static final String IMAGE_TYPE = "imageType";

    @Rule
    public MongoDBRule mongoDBRule = MongoDBRule.create();

    private BunDAO dao;

    @Before
    public void setUp() {
        dao = new BunDAO(mongoDBRule.getDB());
    }

    @Test
    public void findAll_ReturnsAllBuns() {
        persistBun(ID, NAME, PRICE, IMAGE, IMAGE_TYPE);

        List<Bun> buns = dao.findAll();

        assertThat(buns).hasSize(1);
        assertThat(buns.get(0).getId()).isEqualTo(ID);
        assertThat(buns.get(0).getName()).isEqualTo(NAME);
        assertThat(buns.get(0).getPrice()).isEqualTo(PRICE);
        assertThat(buns.get(0).getImage()).isEqualTo(IMAGE);
        assertThat(buns.get(0).getImageType()).isEqualTo(IMAGE_TYPE);
    }

    private void persistBun(String id, String name, double price, String image, String imageType) {
        mongoDBRule.persist(new Bun(id, name, price, image, imageType));
    }

}