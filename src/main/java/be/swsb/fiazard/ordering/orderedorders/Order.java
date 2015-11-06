package be.swsb.fiazard.ordering.orderedorders;

import be.swsb.fiazard.ordering.bun.Bun;
import be.swsb.fiazard.ordering.condiment.Condiment;
import be.swsb.fiazard.ordering.topping.Topping;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

public class Order {
    public static final String ORDERS_COLL_NAME = "Orders";

    private Bun bun;
    private List<Topping> toppings;
    private List<Condiment> condiments;
    private Date orderedOn;

    @JsonCreator
    public Order(
            @JsonProperty("bun") Bun bun,
            @JsonProperty("toppings") List<Topping> toppings,
            @JsonProperty("condiments") List<Condiment> condiments,
            @JsonProperty("orderedOn") Date orderedOn) {
        this.bun = bun;
        this.toppings = toppings;
        this.condiments = condiments;
        this.orderedOn = orderedOn;
    }

    public Bun getBun() {
        return bun;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public List<Condiment> getCondiments() {
        return condiments;
    }

    public Date getOrderedOn() {
        return orderedOn;
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
                .append("bun", getBun())
                .append("toppings", getToppings())
                .append("condiments", getCondiments())
                .append("orderedOn", getOrderedOn())
                .toString();
    }
}
