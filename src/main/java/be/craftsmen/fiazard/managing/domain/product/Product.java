package be.craftsmen.fiazard.managing.domain.product;

import be.craftsmen.fiazard.common.Id;
import be.craftsmen.fiazard.managing.domain.category.Category;

import java.util.List;

public class Product {

    public static final String PRODUCTS_COLL_NAME = "products";

    private Id id;
    private Category category;
    private String name;
    private List<String> composition;
    private List<String> sauces;

    public Product(Id id, Category category, String name, List<String> composition, List<String> sauces) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.composition = composition;
        this.sauces = sauces;
    }

    public Product(Category category, String name, List<String> composition, List<String> sauces) {
        new Product(null, category, name, composition, sauces);
    }

    public Id getId() {
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
