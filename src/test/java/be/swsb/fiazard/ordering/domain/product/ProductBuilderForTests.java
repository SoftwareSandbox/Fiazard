package be.swsb.fiazard.ordering.domain.product;

import be.swsb.fiazard.ordering.domain.category.Category;
import be.swsb.fiazard.ordering.domain.category.CategoryBuilderForTests;
import com.google.common.collect.Lists;

import java.util.List;

public class ProductBuilderForTests {

    public static final String NAME = "Club Ham";
    public static final List<String> COMPOSITION = Lists.newArrayList("salad", "tomato", "olives");
    public static final List<String> SAUCES = Lists.newArrayList("mayonaise");

    private String id;
    private Category category = new CategoryBuilderForTests().build();
    private String name = NAME;
    private List<String> composition = COMPOSITION;
    private List<String> sauces = SAUCES;

    public Product build() {
        return new Product(id, category, name, composition, sauces);
    }

    public ProductBuilderForTests withId(String id) {
        this.id = id;
        return this;
    }

    public ProductBuilderForTests withCategory(Category category) {
        this.category = category;
        return this;
    }

    public ProductBuilderForTests withName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilderForTests withComposition(List<String> composition) {
        this.composition = composition;
        return this;
    }

    public ProductBuilderForTests withSauces(List<String> sauces) {
        this.sauces = sauces;
        return this;
    }
}