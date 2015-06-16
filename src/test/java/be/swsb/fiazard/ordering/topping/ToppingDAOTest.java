package be.swsb.fiazard.ordering.topping;

import be.swsb.fiazard.common.mongo.MongoDBRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ToppingDAOTest {

    private static final String NAME = "rudy";
    private static final double PRICE = 12d;
    private static final String ID = "123";
    public static final String IMAGE = "image";
    public static final String IMAGE_TYPE = "imageType";

    @Rule
    public MongoDBRule mongoDBRule = MongoDBRule.create();

    private ToppingDAO dao;

    @Before
    public void setUp() {
        dao = new ToppingDAO(mongoDBRule.getDB());
    }

    @Test
    public void findAll_ReturnsAllToppings() {
        persistTopping(ID, NAME, PRICE, IMAGE, IMAGE_TYPE);

        List<Topping> toppings = dao.findAll();

        assertThat(toppings).hasSize(1);
        assertThat(toppings.get(0).getId()).isEqualTo(ID);
        assertThat(toppings.get(0).getName()).isEqualTo(NAME);
        assertThat(toppings.get(0).getPrice()).isEqualTo(PRICE);
        assertThat(toppings.get(0).getImage()).isEqualTo(IMAGE);
        assertThat(toppings.get(0).getImageType()).isEqualTo(IMAGE_TYPE);
    }

    private void persistTopping(String id, String name, double price, String image, String imageType) {
        mongoDBRule.persist(new Topping(id, name, price, image,imageType));
    }

}