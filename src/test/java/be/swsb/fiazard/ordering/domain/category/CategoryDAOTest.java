package be.swsb.fiazard.ordering.domain.category;

import java.util.List;

import be.swsb.fiazard.common.mongo.MongoDBRule;
import be.swsb.fiazard.ordering.domain.product.Product;
import be.swsb.fiazard.ordering.domain.product.ProductBuilderForTests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryDAOTest {

    private static final String CATEGORY_ID1 = "categoryId-1";
    private static final String CATEGORY_ID2 = "categoryId-2";
	private static final String CATEGORY_NAME1 = "categoryName-1";
	private static final String CATEGORY_NAME2 = "categoryName-2";

	@Rule
    public MongoDBRule mongoDBRule = MongoDBRule.create();

    private CategoryDAO dao;

    @Before
    public void setUp(){
        dao = new CategoryDAO(mongoDBRule.getDB());
    }

    @Test
    public void findAll_ReturnsAllCategories(){
        persistProductForCategory(CATEGORY_ID1, CATEGORY_NAME1);
        persistProductForCategory(CATEGORY_ID2, CATEGORY_NAME2);

        List<Category> categories = dao.findAll();
		assertThat(categories).hasSize(2);
		assertThat(categoryById(categories, CATEGORY_ID1).getName()).isEqualTo(CATEGORY_NAME1);
		assertThat(categoryById(categories, CATEGORY_ID2).getName()).isEqualTo(CATEGORY_NAME2);
    }

	private Category categoryById(List<Category> categories, String id) {
		for (Category category : categories) {
			if (id.equals(category.getId())) {
				return category;
			}
		}
		return null;
	}

	private Product persistProductForCategory(String categoryId, String categoryName) {
		Category category1 = new CategoryBuilderForTests().withId(categoryId).withName(categoryName).build();
		Product product = new ProductBuilderForTests().withCategory(category1).build();
		mongoDBRule.persist(product);
		return product;
	}
}