package be.swsb.fiazard.main;

import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import be.swsb.dropwizard.healthchecks.MongoDBHealthCheck;
import be.swsb.fiazard.common.eventsourcing.EventStore;

import com.commercehub.dropwizard.mongo.ManagedMongoClient;
import com.mongodb.DB;

public class MongoBundle implements ConfiguredBundle<FiazardConfig> {

    private DB db;
	private EventStore eventStore;

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
    }

    @Override
    public void run(FiazardConfig configuration, Environment environment) throws Exception {
        ManagedMongoClient mongoClient = configuration.getMongo().build();
        db = mongoClient.getDB(configuration.getMongo().getDbName());
        environment.lifecycle().manage(configuration.getMongo().build());
        environment.healthChecks().register("MongoDBHealthCheck", new MongoDBHealthCheck(db));
        eventStore = new EventStore(db);
    }
    
    public DB getDb() {
		return db;
	}
    
    public EventStore getEventStore() {
		return eventStore;
	}

}
