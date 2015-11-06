package be.swsb.fiazard.ordering.orderplacement;

import io.dropwizard.Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import be.swsb.fiazard.eventstore.EventStoreBundle;


public class OrderPlacementBundle implements Bundle {

    private EventStoreBundle eventStoreBundle;

	public OrderPlacementBundle(EventStoreBundle eventStoreBundle) {
		this.eventStoreBundle = eventStoreBundle;
	}

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
    }

    @Override
    public void run(Environment environment) {
        environment.jersey().register(new OrderingResource(new OrderFactory(), eventStoreBundle.getAggregateRepository()));
    }

}
