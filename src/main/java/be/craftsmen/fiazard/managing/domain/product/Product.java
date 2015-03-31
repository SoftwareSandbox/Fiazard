package be.craftsmen.fiazard.managing.domain.product;

import be.craftsmen.fiazard.managing.domain.category.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.MongoCollection;

import java.util.List;

@MongoCollection(name = Product.PRODUCTS_COLL_NAME)
public class Product {

    public static final String PRODUCTS_COLL_NAME = "products";

    @javax.persistence.Id
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private List<String> composition;
    @JsonProperty
    private List<String> sauces;
    @JsonProperty
    private Category category;

    public Product(Long id, Category category, String name, List<String> composition, List<String> sauces) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.composition = composition;
        this.sauces = sauces;
    }

    public Product(Category category, String name, List<String> composition, List<String> sauces) {
        new Product(null, category, name, composition, sauces);
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public List<String> getComposition() {
        return composition;
    }

    public List<String> getSauces() {
        return sauces;
    }
}
