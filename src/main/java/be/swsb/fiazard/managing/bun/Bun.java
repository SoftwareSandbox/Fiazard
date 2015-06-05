package be.swsb.fiazard.managing.bun;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@MongoCollection(name = Bun.BUNS_COLL_NAME)
public class Bun {

    public static final String BUNS_COLL_NAME = "buns";

    @NotNull
    private String id;

    @JsonProperty
    @NotNull
    @Length(max = 50)
    private String name;

    @JsonProperty
    @NotNull
    private double price;

    @JsonCreator
    public Bun(
            @ObjectId @JsonProperty("_id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("price") double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
