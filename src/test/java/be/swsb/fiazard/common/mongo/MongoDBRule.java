package be.swsb.fiazard.common.mongo;


import be.swsb.fiazard.management.domain.topping.Topping;
import be.swsb.fiazard.ordering.domain.product.Product;
import com.commercehub.dropwizard.mongo.MongoClientFactory;
import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClientURI;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.mongojack.JacksonDBCollection;

import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoDBRule extends TestWatcher {

    private static Logger logger = Logger.getLogger(MongoDBRule.class.getName());

    private DB db;
    private List<String> mongoDBReservedColls = Lists.newArrayList("system.indexes");

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

    public DB getDB() {
        return db;
    }

    public void persistProduct(Product product) {
        JacksonDBCollection<Product, String> products = JacksonDBCollection.wrap(db.getCollection(Product.PRODUCTS_COLL_NAME), Product.class, String.class);
        products.save(product);
    }

    public void persistTopping(Topping topping){
        JacksonDBCollection<Topping, String> toppings = JacksonDBCollection.wrap(db.getCollection(Topping.TOPPINGS_COLL_NAME), Topping.class, String.class);
        toppings.save(topping);
    }

    @Override
    protected void starting(Description description) {
        super.starting(description);
//        emptyAllMongoCollections();
    }

    @Override
    protected void finished(Description description) {
        super.finished(description);
        emptyAllMongoCollections();
    }

    private void emptyAllMongoCollections() {
        db.getCollectionNames()
                .stream()
                .filter(collName -> !mongoDBReservedColls.contains(collName))
                .forEach(collName -> {
                    recreate(collName);
//                    logger.log(Level.INFO, "deleted {0} docs of collection {1}", new Object[]{result.getN(), collName});
                });
    }

    private void recreate(String collName) {
        logger.log(Level.INFO, "attempting recreation of {0}", collName);
        db.getCollection(collName).drop();
        db.createCollection(collName, new BasicDBObject("capped", true).append("size", 1048576));
    }
}