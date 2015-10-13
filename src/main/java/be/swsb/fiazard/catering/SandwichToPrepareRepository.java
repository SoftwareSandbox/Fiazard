package be.swsb.fiazard.catering;

import com.google.common.collect.Lists;
import com.mongodb.DB;
import org.mongojack.JacksonDBCollection;

import java.util.List;

public class SandwichToPrepareRepository {

    private DB db;

    public SandwichToPrepareRepository(DB db) {
        this.db = db;
    }

    public List<SandwichToPrepare> findAll() {
        return Lists.newArrayList(this.collection().find().iterator());
    }

    private JacksonDBCollection<SandwichToPrepare, String> collection() {
        return JacksonDBCollection.wrap(this.db.getCollection(SandwichToPrepare.SANDWICH_TO_PREPARE_COLL_NAME), SandwichToPrepare.class, String.class);
    }
}
