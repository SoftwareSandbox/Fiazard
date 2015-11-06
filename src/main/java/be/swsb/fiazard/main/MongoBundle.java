package be.swsb.fiazard.main;

import static be.swsb.fiazard.util.representation.FiazardJacksonModule.MODULE;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.mongojack.internal.MongoJackModule;

import be.swsb.dropwizard.healthchecks.MongoDBHealthCheck;
import be.swsb.fiazard.common.eventsourcing.EventStore;

import com.commercehub.dropwizard.mongo.ManagedMongoClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;

public class MongoBundle implements ConfiguredBundle<FiazardConfig> {

    private DB db;
	private EventStore eventStore;
	private ObjectMapper objectMapper;

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
        
        objectMapper = MongoJackModule.configure(new ObjectMapper());
        objectMapper.registerModule(MODULE);
    }
    
    public DB getDb() {
		return db;
	}
    
    public EventStore getEventStore() {
		return eventStore;
	}
    
    public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

}
