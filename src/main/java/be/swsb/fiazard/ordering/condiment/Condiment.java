package be.swsb.fiazard.ordering.condiment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import javax.validation.constraints.NotNull;

@MongoCollection(name = Condiment.CONDIMENTS_COLL_NAME)
public class Condiment {

    public static final String CONDIMENTS_COLL_NAME = "condiments";

    @NotNull
    private String id;

    @JsonProperty
    @NotNull
    @Length(max = 50)
    private String name;

    @JsonProperty
    @NotNull
    private double price;

    @JsonProperty
    @NotNull
    private String image;

    @JsonProperty
    @NotNull
    private String imageType;

    @JsonCreator
    public Condiment(
            @ObjectId @JsonProperty("_id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("price") double price,
            @JsonProperty("image") String image,
            @JsonProperty("imageType") String imageType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.imageType = imageType;
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

    public String getImageType() {
        return imageType;
    }

    public String getImage() {
        return image;
    }
}
