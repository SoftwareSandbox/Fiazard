package be.craftsmen.fiazard.managing.domain.category;

import be.craftsmen.fiazard.common.Id;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class Category {

    @NotNull
    private Id id;

    @NotNull
    @Length(max = 50)
    private String name;

    public Category(Id id, String name) {
        this.id = id;
        this.name = name;
    }

    public Id getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
