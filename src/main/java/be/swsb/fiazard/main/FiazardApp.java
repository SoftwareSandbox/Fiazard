package be.swsb.fiazard.main;

import static be.swsb.fiazard.util.representation.FiazardJacksonModule.MODULE;
import io.dropwizard.Application;
import io.dropwizard.Bundle;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import be.swsb.fiazard.common.exceptions.FiazardExceptionToJSONMapper;
import be.swsb.fiazard.eventstore.EventStoreBundle;
import be.swsb.fiazard.ordering.OrderingBundle;
import be.swsb.fiazard.ordering.orderplacement.OrderPlacementBundle;
import be.swsb.fiazard.ordering.preparation.OrderPreparationBundle;

public class FiazardApp extends Application<FiazardConfig> {

	@Override
    public String getName() {
        return "Fiazardtje";
    }

    @Override
    public void initialize(Bootstrap<FiazardConfig> bootstrap) {
        EventStoreBundle eventStoreBundle = registerBundle(bootstrap, new EventStoreBundle());
        MongoBundle mongoBundle = registerBundle(bootstrap, new MongoBundle());
		
        registerBundle(bootstrap, new OrderingBundle(mongoBundle));
        registerBundle(bootstrap, new OrderPlacementBundle(eventStoreBundle));
        registerBundle(bootstrap, new OrderPreparationBundle(mongoBundle));
    }

	private <T extends ConfiguredBundle<FiazardConfig>> T registerBundle(Bootstrap<FiazardConfig> bootstrap, T bundle) {
        bootstrap.addBundle(bundle);
		return bundle;
	}

	private <T extends Bundle> T registerBundle(Bootstrap<FiazardConfig> bootstrap, T bundle) {
        bootstrap.addBundle(bundle);
		return bundle;
	}

	@Override
    public void run(FiazardConfig config, Environment environment) throws Exception {
        configureJersey(environment);
    }

	private void configureJersey(Environment environment) {
		environment.jersey().register(FiazardExceptionToJSONMapper.class);
        environment.getObjectMapper().registerModule(MODULE);
	}

    public static void main(String[] args) throws Exception {
        new FiazardApp().run(args);
    }
}
