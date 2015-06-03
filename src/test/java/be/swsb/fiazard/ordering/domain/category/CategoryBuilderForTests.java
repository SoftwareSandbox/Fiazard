package be.swsb.fiazard.ordering.domain.category;

public class CategoryBuilderForTests {

    private static final String NAME = "Ham";

    private String id;
    private String name = NAME;

    public Category build() {
        return new Category(id, name);
    }

    public CategoryBuilderForTests withId(String id) {
        this.id = id;
        return this;
    }

    public CategoryBuilderForTests withName(String name) {
        this.name = name;
        return this;
    }
}
