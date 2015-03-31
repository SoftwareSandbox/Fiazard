package be.craftsmen.fiazard.managing.domain.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class Category {

    @JsonProperty
    @NotNull
    private Long id;

    @JsonProperty
    @NotNull
    @Length(max = 50)
    private String name;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
