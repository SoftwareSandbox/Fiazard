package be.craftsmen.fiazard.managing.guice;

import be.craftsmen.fiazard.main.FiazardConfig;
import com.commercehub.dropwizard.mongo.ManagedMongoClient;
import com.google.inject.Binder;
import com.google.inject.Provides;
import com.mongodb.DB;

import java.net.UnknownHostException;

public class FiazardManagingModule implements com.google.inject.Module {

    private DB db;

    @Override
    public void configure(Binder binder) {

    }

    @Provides
    public DB provideMongoDB(FiazardConfig config) throws UnknownHostException {
        if (db == null){
            ManagedMongoClient mongoClient = config.getMongo().build();
            db = mongoClient.getDB(config.getMongo().getDbName());
        }
        return db;
    }
}
