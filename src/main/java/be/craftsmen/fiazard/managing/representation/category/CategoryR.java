package be.craftsmen.fiazard.managing.representation.category;

import be.craftsmen.fiazard.common.Id;
import be.craftsmen.fiazard.common.Representation;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CategoryR implements Representation {

    @NotNull
    private Id id;

    @NotNull
    @Length(max = 50)
    private String name;

    private CategoryR(){/*noop, necessary for Jackson :s, or we might need to annotate the actual constructor*/}

    public CategoryR(Id id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return Id.asString(id);
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
