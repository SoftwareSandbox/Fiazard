package be.swsb.fiazard.ordering.bun;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import javax.validation.constraints.NotNull;

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

    @JsonProperty
    @NotNull
    private String image;

    @JsonProperty
    @NotNull
    private String imageType;

    @JsonCreator
    public Bun(
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

    public String getImage() {
        return image;
    }

    public String getImageType() {
        return imageType;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("name", getName())
                .append("price", getPrice())
                .append("imageType", getImageType())
                .toString();
    }
}
