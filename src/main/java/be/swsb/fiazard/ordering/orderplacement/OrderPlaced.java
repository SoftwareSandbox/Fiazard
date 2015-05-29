package be.swsb.fiazard.ordering.orderplacement;

import java.util.List;

import be.swsb.fiazard.common.eventsourcing.Event;

public class OrderPlaced extends Event {

	private String orderId;
	private List<Sandwich> sandwiches;
	
	public OrderPlaced(String orderId, List<Sandwich> sandwiches) {
		this.orderId = orderId;
		this.sandwiches = sandwiches;
	}
	
	public String getOrderId() {
		return orderId;
	}
	
	public List<Sandwich> getSandwiches() {
		return sandwiches;
	}
}
