package be.swsb.fiazard.ordering.orderplacement;

import be.swsb.fiazard.ddd.AggregateId;

class OrderFactory {
	
	public OrderFactory() {
	}
	
	Order makeANewOrder(PlaceOrderCommand placeOrderCommand){
		return new Order(AggregateId.generate(), placeOrderCommand.getCustomerName());
	}
	
	
}
