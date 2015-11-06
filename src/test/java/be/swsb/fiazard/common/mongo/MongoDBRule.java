package be.swsb.fiazard.common.mongo;


import be.swsb.fiazard.util.representation.FiazardJacksonModule;
import com.commercehub.dropwizard.mongo.MongoClientFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClientURI;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.mongojack.JacksonDBCollection;
import org.mongojack.MongoCollection;
import org.mongojack.internal.MongoJackModule;

import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoDBRule extends TestWatcher {

    private static Logger logger = Logger.getLogger(MongoDBRule.class.getName());

    private DB db;
    private List<String> mongoDBReservedColls = Lists.newArrayList("system.indexes");
    private boolean cleanUpDataAtEnd = true;
    private boolean loggingOn = false;

    public static MongoDBRule create() {
        try {
            return new MongoDBRule();
        } catch (UnknownHostException e) {
            throw new RuntimeException("shit went wrong!", e);
        }
    }

    private MongoDBRule() throws UnknownHostException {
        MongoClientFactory mongoClientFactory = new MongoClientFactory();
        String dbName = "fiazard-test";
        mongoClientFactory.setDbName(dbName);
        mongoClientFactory.setUri(new MongoClientURI("mongodb://localhost:27017/?maxPoolSize=50&maxIdleTimeMS=300000"));
        db = mongoClientFactory.build().getDB(dbName);
    }

    public MongoDBRule withoutCleaningUpDataAtEnd() {
        this.cleanUpDataAtEnd = false;
        return this;
    }

    public MongoDBRule withCleaningUpDataAtEnd() {
        this.cleanUpDataAtEnd = true;
        return this;
    }

    public MongoDBRule withLoggingOn() {
        this.loggingOn = true;
        return this;
    }

    public DB getDB() {
        return db;
    }

    public <T> void persist(T persistableObject) {
        collectionFor(persistableObject).save(persistableObject);
    }

    public void persistAll(Object... persistableObjects) {
        for (Object obj: persistableObjects) {
            persist(obj);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> JacksonDBCollection<T, String> collectionFor(T persistableObject) {
        ObjectMapper objectMapper = MongoJackModule.configure(new ObjectMapper());
        objectMapper.registerModule(FiazardJacksonModule.MODULE);
        return (JacksonDBCollection<T, String>) JacksonDBCollection.wrap(db.getCollection(collectionNameFor(persistableObject)), persistableObject.getClass(), String.class, objectMapper);
    }

    private String collectionNameFor(Object obj) {
        return obj.getClass().getAnnotation(MongoCollection.class).name();
    }

    @Override
    protected void starting(Description description) {
        super.starting(description);
        emptyAllMongoCollections();
    }

    @Override
    protected void finished(Description description) {
        super.finished(description);
        if (cleanUpDataAtEnd) {
            emptyAllMongoCollections();
        }
    }

    private void emptyAllMongoCollections() {
        db.getCollectionNames()
                .stream()
                .filter(collName -> !mongoDBReservedColls.contains(collName))
                .forEach(collName -> {
                    recreate(collName);
                });
    }

    private void recreate(String collName) {
        if (loggingOn) {
            logger.log(Level.INFO, "attempting recreation of {0}", collName);
        }
        db.getCollection(collName).drop();
        db.createCollection(collName, new BasicDBObject("capped", true).append("size", 1048576));
    }
}