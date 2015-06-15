package be.swsb.fiazard.ordering.condiment;

import java.util.List;

import org.mongojack.JacksonDBCollection;

import com.google.common.collect.Lists;
import com.mongodb.DB;

public class CondimentDAO {

    private DB db;

    public CondimentDAO(DB db) {
        this.db = db;
    }

    public List<Condiment> findAll() {
        return Lists.newArrayList(collection().find().iterator());
    }

	private JacksonDBCollection<Condiment, String> collection() {
		return JacksonDBCollection.wrap(db.getCollection(Condiment.CONDIMENTS_COLL_NAME), Condiment.class, String.class);
	}
}
