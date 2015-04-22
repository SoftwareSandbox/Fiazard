package be.swsb.fiazard.ordering.domain.product;

import be.swsb.fiazard.ordering.domain.category.Category;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import java.util.List;

@MongoCollection(name = Product.PRODUCTS_COLL_NAME)
public class Product {

    public static final String PRODUCTS_COLL_NAME = "products";

    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    private List<String> composition;
    @JsonProperty
    private List<String> sauces;
    @JsonProperty
    private Category category;

    @JsonCreator
    public Product(
            @ObjectId @JsonProperty("_id") String id,
            @JsonProperty("category") Category category,
            @JsonProperty("name") String name,
            @JsonProperty("composition") List<String> composition,
            @JsonProperty("sauces") List<String> sauces) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.composition = composition;
        this.sauces = sauces;
    }

    public Product(Category category, String name, List<String> composition, List<String> sauces) {
        new Product(null, category, name, composition, sauces);
    }

    public String getId() {
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
