package be.swsb.fiazard.main;

import static be.swsb.fiazard.util.representation.FiazardJacksonModule.MODULE;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import be.swsb.fiazard.common.exceptions.FiazardExceptionToJSONMapper;
import be.swsb.fiazard.eventstore.EventStoreBundle;
import be.swsb.fiazard.ordering.bun.BunDAO;
import be.swsb.fiazard.ordering.bun.BunResource;
import be.swsb.fiazard.ordering.condiment.CondimentDAO;
import be.swsb.fiazard.ordering.condiment.CondimentResource;
import be.swsb.fiazard.ordering.openinghours.OpeningHourResourceV1;
import be.swsb.fiazard.ordering.orderplacement.OrderPlacementBundle;
import be.swsb.fiazard.ordering.preparation.OrderPreparationBundle;
import be.swsb.fiazard.ordering.topping.ToppingDAO;
import be.swsb.fiazard.ordering.topping.ToppingResource;

public class FiazardApp extends Application<FiazardConfig> {

	// TODO als de prul in configureOrdering() ook in een aparte module zou steken, hebben we dit field niet nodig
    private MongoBundle mongoBundle;

	@Override
    public String getName() {
        return "Fiazardtje";
    }

    @Override
    public void initialize(Bootstrap<FiazardConfig> bootstrap) {
        EventStoreBundle eventStoreBundle = new EventStoreBundle();
        bootstrap.addBundle(eventStoreBundle);
        
		mongoBundle = new MongoBundle();
		bootstrap.addBundle(mongoBundle);
		
        bootstrap.addBundle(new OrderPlacementBundle(eventStoreBundle));
        bootstrap.addBundle(new OrderPreparationBundle(mongoBundle));
    }

    @Override
    public void run(FiazardConfig config, Environment environment) throws Exception {
        environment.jersey().register(FiazardExceptionToJSONMapper.class);
        environment.jersey().register(new OpeningHourResourceV1());
        configureOrdering(environment);
        environment.getObjectMapper().registerModule(MODULE);
    }

	private void configureOrdering(Environment environment) {
		environment.jersey().register(new BunResource(new BunDAO(mongoBundle.getDb()), mongoBundle.getEventStore()));
		environment.jersey().register(new ToppingResource(new ToppingDAO(mongoBundle.getDb()), mongoBundle.getEventStore()));
		environment.jersey().register(new CondimentResource(new CondimentDAO(mongoBundle.getDb()), mongoBundle.getEventStore()));
	}

    public static void main(String[] args) throws Exception {
        new FiazardApp().run(args);
    }
}
