package be.craftsmen.fiazard.managing.representation.product;

import be.craftsmen.fiazard.common.Id;
import be.craftsmen.fiazard.common.Representation;
import com.google.common.collect.Lists;

import java.util.List;

public class ProductR implements Representation {
    private Id id;
    private Id categoryId;
    private String name;
    private List<String> composition;
    private List<String> sauces;

    private ProductR(){}

    public String getId() {
        return Id.asString(id);
    }

    public String getCategoryId() {
        return Id.asString(categoryId);
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

    public static class ProductRBuilder {

        Id id;
        Id categoryId;
        String name;
        List<String> composition;
        List<String> sauces;

        public ProductRBuilder(){
            id = Id.random();
        }

        public ProductR build(){
            ProductR product = new ProductR();
            product.id = id;
            product.categoryId = categoryId;
            product.name = name;
            product.composition = composition;
            product.sauces = sauces;
            return product;
        }

        public ProductRBuilder withId(Id id) {
            this.id = id;
            return this;
        }

        public ProductRBuilder withCategoryId(Id categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public ProductRBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductRBuilder withComposition(String... compositions) {
            this.composition = Lists.newArrayList(compositions);
            return this;
        }

        public ProductRBuilder withSauces(String... sauces) {
            this.sauces = Lists.newArrayList(sauces);
            return this;
        }
    }
}
