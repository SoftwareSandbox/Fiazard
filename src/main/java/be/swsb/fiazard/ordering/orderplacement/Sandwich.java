package be.swsb.fiazard.ordering.orderplacement;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import be.swsb.fiazard.managing.bun.Bun;
import be.swsb.fiazard.managing.condiment.Condiment;
import be.swsb.fiazard.managing.topping.Topping;

public class Sandwich {

	@JsonProperty
	private String label;
	@JsonProperty
	private Bun bun;
	@JsonProperty
	private List<Topping> toppings;
	@JsonProperty
	private List<Condiment> condiments;

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
