package be.swsb.fiazard.catering;

import be.swsb.fiazard.ddd.AggregateId;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import javax.validation.constraints.NotNull;

@MongoCollection(name = KlaarTeMakenBroodje.KLAAR_TE_MAKEN_BROODJES_COLL_NAME)
public class KlaarTeMakenBroodje {
    public static final String KLAAR_TE_MAKEN_BROODJES_COLL_NAME = "klaartemakenbroodjes";

    @NotNull
    private String id;
    @JsonProperty("idBestelling")
    private AggregateId idBestelling;
    @JsonProperty("naam")
    private String naam;

    public KlaarTeMakenBroodje(@JsonProperty("_id")String id,
                               @JsonProperty("idBestelling")AggregateId idBestelling,
                               @JsonProperty("naam") String naam
                               ) {
        this.id = id;
        this.idBestelling = idBestelling;
        this.naam = naam;
    }

    public String getId() {
        return id;
    }

    public AggregateId getIdBestelling() {
        return idBestelling;
    }

    public String getNaam() {
        return naam;
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
