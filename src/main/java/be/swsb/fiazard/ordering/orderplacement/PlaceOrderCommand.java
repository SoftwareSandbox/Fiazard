package be.swsb.fiazard.ordering.orderplacement;

import be.swsb.fiazard.ddd.AbstractCommand;

public class PlaceOrderCommand extends AbstractCommand {

	private String customerName;

	public PlaceOrderCommand(String customerName) {
		super(VERSION_FOR_NEW_AGGREGATE);
		this.customerName = customerName;
	}
	
	String getCustomerName() {
		return customerName;
	}
	
}
