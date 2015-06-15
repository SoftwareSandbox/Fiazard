package be.swsb.fiazard.ordering.topping;

import com.google.common.collect.Lists;
import com.mongodb.DB;

import org.mongojack.JacksonDBCollection;

import java.util.List;

import static be.swsb.fiazard.ordering.topping.Topping.TOPPINGS_COLL_NAME;

public class ToppingDAO {

    private DB db;

    public ToppingDAO(DB db) {
        this.db = db;
    }

    public List<Topping> findAll() {
        JacksonDBCollection<Topping, String> products =
                JacksonDBCollection.wrap(db.getCollection(TOPPINGS_COLL_NAME), Topping.class, String.class);

        return Lists.newArrayList(products.find().iterator());
    }
}
