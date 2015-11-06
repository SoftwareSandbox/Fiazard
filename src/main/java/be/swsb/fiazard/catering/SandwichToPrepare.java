package be.swsb.fiazard.catering;

import be.swsb.fiazard.ddd.AggregateId;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.mongojack.MongoCollection;

import javax.validation.constraints.NotNull;

@MongoCollection(name = SandwichToPrepare.SANDWICH_TO_PREPARE_COLL_NAME)
public class SandwichToPrepare {
    public static final String SANDWICH_TO_PREPARE_COLL_NAME = "sandwichestoprepare";

    @NotNull
    private String id;
    @JsonProperty("idOrder")
    private AggregateId idOrder;
    @JsonProperty("name")
    private String name;

    public SandwichToPrepare(@JsonProperty("_id") String id,
                             @JsonProperty("idOrder") AggregateId idOrder,
                             @JsonProperty("name") String name) {
        this.id = id;
        this.idOrder = idOrder;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public AggregateId getIdOrder() {
        return idOrder;
    }

    public String getName() {
        return name;
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
        return ToStringBuilder.reflectionToString(this);
    }
}
