package be.swsb.fiazard.ordering;

import com.mongodb.DB;

import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import be.swsb.fiazard.common.eventsourcing.EventStore;
import be.swsb.fiazard.main.FiazardConfig;
import be.swsb.fiazard.main.MongoBundle;
import be.swsb.fiazard.ordering.bun.BunDAO;
import be.swsb.fiazard.ordering.bun.BunResource;
import be.swsb.fiazard.ordering.condiment.CondimentDAO;
import be.swsb.fiazard.ordering.condiment.CondimentResource;
import be.swsb.fiazard.ordering.openinghours.OpeningHourResourceV1;
import be.swsb.fiazard.ordering.topping.ToppingDAO;
import be.swsb.fiazard.ordering.topping.ToppingResource;


public class OrderingBundle implements ConfiguredBundle<FiazardConfig> {

	private MongoBundle mongoBundle;

	public OrderingBundle(MongoBundle mongoBundle) {
		this.mongoBundle = mongoBundle;
	}

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
    }

    @Override
    public void run(FiazardConfig configuration, Environment environment) throws Exception {
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
