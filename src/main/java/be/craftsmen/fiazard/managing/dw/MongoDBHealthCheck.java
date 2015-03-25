package be.craftsmen.fiazard.managing.dw;

import com.codahale.metrics.health.HealthCheck;
import com.google.common.base.Joiner;
import com.google.inject.Inject;
import com.mongodb.DB;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MongoDBHealthCheck extends HealthCheck {

    private static Logger logger = Logger.getLogger(MongoDBHealthCheck.class.getName());

    private DB db;

    public MongoDBHealthCheck(DB db){
        this.db = db;
    }

    @Override
    protected Result check() throws Exception {
        logger.log(Level.INFO, "db was instantiated: {0}", db);
        List<String> missingCollections = findMissingCollections("products", "openinghours");
        if (missingCollections.isEmpty()){
            return Result.healthy("All necessary collections exist");
        }
        return Result.unhealthy("Expected these Collections to exist but they do not: %s", Joiner.on(", ").join(missingCollections));
    }

    private List<String> findMissingCollections(String... collectionNames) {
        return Arrays.stream(collectionNames).filter(c->!db.collectionExists(c)).collect(Collectors.toList());
    }
}
