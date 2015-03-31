package be.craftsmen.fiazard.common.mongo;


import be.craftsmen.fiazard.managing.domain.product.Product;
import com.commercehub.dropwizard.mongo.MongoClientFactory;
import com.mongodb.DB;
import com.mongodb.MongoClientURI;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.mongojack.JacksonDBCollection;

import java.net.UnknownHostException;

public class MongoDBRule extends TestWatcher {

    private DB db;

    public static MongoDBRule create() {
        try {
            return new MongoDBRule();
        } catch (UnknownHostException e) {
            throw new RuntimeException("shit went wrong!", e);
        }
    }

    private MongoDBRule() throws UnknownHostException {
        MongoClientFactory mongoClientFactory = new MongoClientFactory();
        String dbName = "fiazard";
        mongoClientFactory.setDbName(dbName);
        mongoClientFactory.setUri(new MongoClientURI("mongodb://localhost:27017/?maxPoolSize=50&maxIdleTimeMS=300000"));
        db = mongoClientFactory.build().getDB(dbName);
    }

    public DB getDB() {
        return db;
    }

    public void persist(Product product) {
        JacksonDBCollection<Product, String> products = JacksonDBCollection.wrap(db.getCollection(Product.PRODUCTS_COLL_NAME), Product.class, String.class);
        products.save(product);
    }

    @Override
    protected void starting(Description description) {
        super.starting(description);
        emptyAllMongoCollections();
    }

    @Override
    protected void finished(Description description) {
        super.finished(description);
        emptyAllMongoCollections();
    }

    private void emptyAllMongoCollections() {
        db.getCollectionNames().stream().map(collName -> db.getCollection(collName).remove(null));
    }
}