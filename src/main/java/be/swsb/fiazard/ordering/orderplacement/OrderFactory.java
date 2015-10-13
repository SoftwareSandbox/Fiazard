package be.swsb.fiazard.ordering.orderplacement;

import com.google.common.base.Preconditions;

import be.swsb.fiazard.ddd.AggregateIdGenerator;

class OrderFactory {
	
	private AggregateIdGenerator aggregateIdGenerator = new AggregateIdGenerator();
	
	public OrderFactory(AggregateIdGenerator aggregateIdGenerator) {
		Preconditions.checkArgument(aggregateIdGenerator != null);
		this.aggregateIdGenerator = aggregateIdGenerator;
	}
	
	Order makeANewOrder(PlaceOrderCommand placeOrderCommand){
		return new Order(aggregateIdGenerator.generate(), placeOrderCommand.getCustomerName());
	}
	
	
}
