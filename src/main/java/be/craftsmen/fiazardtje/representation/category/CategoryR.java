package be.craftsmen.fiazardtje.representation.category;

import be.craftsmen.fiazardtje.common.Id;
import be.craftsmen.fiazardtje.representation.Representation;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CategoryR implements Representation {

    @NotNull
    private Id id;

    @NotNull
    @Length(max = 50)
    private String name;

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
