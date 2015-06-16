package be.swsb.fiazard.ordering.orderplacement;

import be.swsb.fiazard.ordering.bun.Bun;
import be.swsb.fiazard.ordering.condiment.Condiment;
import be.swsb.fiazard.ordering.topping.Topping;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Sandwich {

    @JsonProperty
    private String label;
    @JsonProperty
    private Bun bun;
    @JsonProperty
    private List<Topping> toppings;
    @JsonProperty
    private List<Condiment> condiments;

    @SuppressWarnings("unused")
    private Sandwich() {
    }

    public Sandwich(String label, Bun bun, List<Topping> toppings, List<Condiment> condiments) {
        this.label = label;
        this.bun = bun;
        this.toppings = toppings;
        this.condiments = condiments;
    }

    public String getLabel() {
        return label;
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

}
