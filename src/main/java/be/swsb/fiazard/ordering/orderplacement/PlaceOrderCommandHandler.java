package be.swsb.fiazard.ordering.orderplacement;

import be.swsb.fiazard.eventstore.AggregateRepository;

public class PlaceOrderCommandHandler {

	private OrderFactory orderFactory;
	
	private AggregateRepository aggregateRepository;
	
	// TODO jozef+bktid: Bij CommandHandlers die eerst een aggregate moeten reconstrueren from eventstore
	// gaan we een fail fast inbouwen die de versie van het readmodel (zie state op command) checkt tov de versie op de aggregate

    public PlaceOrderCommandHandler(OrderFactory orderFactory, AggregateRepository aggregateRepository) {
        this.orderFactory = orderFactory;
        this.aggregateRepository = aggregateRepository;
    }

    public void handleCommand(PlaceOrderCommand command) {
		Order newOrder = orderFactory.makeANewOrder(command);
		aggregateRepository.saveAggregate(newOrder);
	}

}
