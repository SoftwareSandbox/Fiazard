package be.swsb.fiazard.main;

import be.swsb.dropwizard.healthchecks.MongoDBHealthCheck;
import be.swsb.fiazard.common.eventsourcing.EventStore;
import be.swsb.fiazard.common.exceptions.FiazardExceptionToJSONMapper;
import be.swsb.fiazard.ordering.bun.BunDAO;
import be.swsb.fiazard.ordering.bun.BunResource;
import be.swsb.fiazard.ordering.condiment.CondimentDAO;
import be.swsb.fiazard.ordering.condiment.CondimentResource;
import be.swsb.fiazard.ordering.openinghours.OpeningHourResourceV1;
import be.swsb.fiazard.ordering.orderplacement.OrderingBundle;
import be.swsb.fiazard.ordering.preparation.OrderItemToBePreparedViewModelDAO;
import be.swsb.fiazard.ordering.preparation.OrderItemToBePreparedViewModelResource;
import be.swsb.fiazard.ordering.topping.ToppingDAO;
import be.swsb.fiazard.ordering.topping.ToppingResource;
import com.commercehub.dropwizard.mongo.ManagedMongoClient;
import com.mongodb.DB;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.net.UnknownHostException;

import static be.swsb.fiazard.util.representation.FiazardJacksonModule.MODULE;

public class FiazardApp extends Application<FiazardConfig> {

    @Override
    public String getName() {
        return "Fiazardtje";
    }

    @Override
    public void initialize(Bootstrap<FiazardConfig> bootstrap) {
        bootstrap.addBundle(new OrderingBundle());
    }

    @Override
    public void run(FiazardConfig config, Environment environment) throws Exception {
        DB db = configureMongo(config, environment);

        environment.jersey().register(FiazardExceptionToJSONMapper.class);
        environment.jersey().register(new OpeningHourResourceV1());
        configureOrdering(environment, db);

        environment.getObjectMapper().registerModule(MODULE);
    }

	private void configureOrdering(Environment environment, DB db) {
		EventStore eventStore = new EventStore(db);
		environment.jersey().register(new BunResource(new BunDAO(db), eventStore));
		environment.jersey().register(new ToppingResource(new ToppingDAO(db), eventStore));
		environment.jersey().register(new CondimentResource(new CondimentDAO(db), eventStore));
        environment.jersey().register(new OrderItemToBePreparedViewModelResource(new OrderItemToBePreparedViewModelDAO(db)));
	}

    //TODO move to a MongoDB Module
    private DB configureMongo(FiazardConfig config, Environment environment) throws UnknownHostException {
        ManagedMongoClient mongoClient = config.getMongo().build();
        DB db = mongoClient.getDB(config.getMongo().getDbName());
        environment.lifecycle().manage(config.getMongo().build());
        environment.healthChecks().register("MongoDBHealthCheck", new MongoDBHealthCheck(db));
        return db;
    }

    public static void main(String[] args) throws Exception {
        new FiazardApp().run(args);
    }
}
