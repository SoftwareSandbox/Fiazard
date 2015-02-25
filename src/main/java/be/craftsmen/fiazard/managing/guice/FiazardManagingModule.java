package be.craftsmen.fiazard.managing.guice;

import com.google.inject.Binder;

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
