package be.craftsmen.fiazard.managing.domain.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.mongojack.ObjectId;

import javax.validation.constraints.NotNull;

public class Category {

    @NotNull
    private String id;

    @JsonProperty
    @NotNull
    @Length(max = 50)
    private String name;

    @JsonCreator
    public Category(
            @ObjectId @JsonProperty("_id") String id,
            @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
