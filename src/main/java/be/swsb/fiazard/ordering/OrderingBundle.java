package be.swsb.fiazard.ordering;

import io.dropwizard.Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import be.swsb.fiazard.common.eventsourcing.EventStore;
import be.swsb.fiazard.main.MongoBundle;
import be.swsb.fiazard.ordering.bun.BunDAO;
import be.swsb.fiazard.ordering.bun.BunResource;
import be.swsb.fiazard.ordering.condiment.CondimentDAO;
import be.swsb.fiazard.ordering.condiment.CondimentResource;
import be.swsb.fiazard.ordering.openinghours.OpeningHourResourceV1;
import be.swsb.fiazard.ordering.topping.ToppingDAO;
import be.swsb.fiazard.ordering.topping.ToppingResource;

import com.mongodb.DB;


public class OrderingBundle implements Bundle {

	private MongoBundle mongoBundle;

	public OrderingBundle(MongoBundle mongoBundle) {
		this.mongoBundle = mongoBundle;
	}

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
    }

    @Override
    public void run(Environment environment) {
		environment.jersey().register(new BunResource(new BunDAO(db()), eventStore()));
		environment.jersey().register(new ToppingResource(new ToppingDAO(db()), eventStore()));
		environment.jersey().register(new CondimentResource(new CondimentDAO(db()), eventStore()));
		environment.jersey().register(new OpeningHourResourceV1());
    }

    private DB db() {
    	return mongoBundle.getDb();
    }
    
	private EventStore eventStore() {
		return mongoBundle.getEventStore();
	}

}
