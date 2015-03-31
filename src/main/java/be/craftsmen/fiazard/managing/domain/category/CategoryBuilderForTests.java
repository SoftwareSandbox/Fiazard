package be.craftsmen.fiazard.managing.domain.category;

import be.craftsmen.fiazard.common.Id;

public class CategoryBuilderForTests {

    private static final String NAME = "Ham";

    private Id id;
    private String name = NAME;

    public Category build() {
        return new Category(id, name);
    }

    public CategoryBuilderForTests withId(Id id) {
        this.id = id;
        return this;
    }

    public CategoryBuilderForTests withName(String name) {
        this.name = name;
        return this;
    }
}
