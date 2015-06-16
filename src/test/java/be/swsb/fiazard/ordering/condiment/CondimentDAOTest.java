package be.swsb.fiazard.ordering.condiment;

import be.swsb.fiazard.common.mongo.MongoDBRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CondimentDAOTest {

    private static final String NAME = "rudy";
    private static final double PRICE = 12d;
    private static final String ID = "123";
    public static final String IMAGE = "image";
    public static final String IMAGE_TYPE = "imageType";

    @Rule
    public MongoDBRule mongoDBRule = MongoDBRule.create();

    private CondimentDAO dao;

    @Before
    public void setUp() {
        dao = new CondimentDAO(mongoDBRule.getDB());
    }

    @Test
    public void findAll_ReturnsAllContiments() {
        persistCondiment(ID, NAME, PRICE, IMAGE, IMAGE_TYPE);

        List<Condiment> condiments = dao.findAll();

        assertThat(condiments).hasSize(1);
        assertThat(condiments.get(0).getId()).isEqualTo(ID);
        assertThat(condiments.get(0).getName()).isEqualTo(NAME);
        assertThat(condiments.get(0).getPrice()).isEqualTo(PRICE);
        assertThat(condiments.get(0).getImage()).isEqualTo(IMAGE);
        assertThat(condiments.get(0).getImageType()).isEqualTo(IMAGE_TYPE);
    }

    private void persistCondiment(String id, String name, double price, String image, String imageType) {
        mongoDBRule.persist(new Condiment(id, name, price, image, imageType));
    }

}