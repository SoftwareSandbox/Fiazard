package be.swsb.fiazard.ordering.orderedorders;

import com.google.common.collect.Lists;
import com.mongodb.DB;
import org.mongojack.JacksonDBCollection;

import java.util.List;

public class OrdersDAO {

    private DB db;

    public OrdersDAO(DB db) {
        this.db = db;
    }

    public List<Order> findAll() {
        return Lists.newArrayList(collection().find().iterator());
    }

    private JacksonDBCollection<Order, String> collection() {
        return JacksonDBCollection.wrap(db.getCollection(Order.ORDERS_COLL_NAME), Order.class, String.class);
    }

}
