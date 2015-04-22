package be.swsb.fiazard.ordering.representation.product;

import java.util.List;

import be.swsb.fiazard.common.Id;
import be.swsb.fiazard.common.Representation;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductR other = (ProductR) o;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.categoryId, other.categoryId)
                && Objects.equal(this.name, other.name)
                && Objects.equal(this.composition, other.composition)
                && Objects.equal(this.sauces, other.sauces);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.categoryId, this.name, this.composition, this.sauces);
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
