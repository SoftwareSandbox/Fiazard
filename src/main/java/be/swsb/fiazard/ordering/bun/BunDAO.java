package be.swsb.fiazard.ordering.bun;

import java.util.List;

import org.mongojack.JacksonDBCollection;

import com.google.common.collect.Lists;
import com.mongodb.DB;

public class BunDAO {

    private DB db;

    public BunDAO(DB db) {
        this.db = db;
    }

    public List<Bun> findAll() {
        return Lists.newArrayList(collection().find().iterator());
    }

	private JacksonDBCollection<Bun, String> collection() {
		return JacksonDBCollection.wrap(db.getCollection(Bun.BUNS_COLL_NAME), Bun.class, String.class);
	}
}
