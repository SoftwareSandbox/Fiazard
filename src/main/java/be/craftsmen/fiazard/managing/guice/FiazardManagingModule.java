package be.craftsmen.fiazard.managing.guice;

import be.craftsmen.fiazard.main.FiazardConfig;
import com.commercehub.dropwizard.mongo.ManagedMongoClient;
import com.google.inject.Binder;
import com.google.inject.Provides;
import com.mongodb.DB;
import com.mongodb.Mongo;

import javax.inject.Singleton;
import java.net.UnknownHostException;

public class FiazardManagingModule implements com.google.inject.Module {

    @Override
    public void configure(Binder binder) {

    }

//    @Provides
//    @Singleton
//    public DB provideMongoDB(FiazardConfig config) throws UnknownHostException {
//        ManagedMongoClient mongoClient = config.getMongo().build();
//        return mongoClient.getDB(config.getMongo().getDbName());
//    }
}
