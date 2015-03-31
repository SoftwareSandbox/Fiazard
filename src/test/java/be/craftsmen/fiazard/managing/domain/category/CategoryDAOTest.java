package be.craftsmen.fiazard.managing.domain.category;

import be.craftsmen.fiazard.common.mongo.MongoDBRule;
import be.craftsmen.fiazard.managing.domain.product.Product;
import be.craftsmen.fiazard.managing.domain.product.ProductBuilderForTests;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryDAOTest {

    @Rule
    public MongoDBRule mongoDBRule = MongoDBRule.create();

    private CategoryDAO dao;

    @Before
    public void setUp(){
        dao = new CategoryDAO(mongoDBRule.getDB());
    }

    @Test
    public void findAll_ReturnsAllCategories(){
        Product product = new ProductBuilderForTests().build();
        mongoDBRule.persist(product);

        assertThat(dao.findAll()).isNotEmpty();
    }
}