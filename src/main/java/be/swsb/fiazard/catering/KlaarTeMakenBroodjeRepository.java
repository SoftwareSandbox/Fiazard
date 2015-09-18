package be.swsb.fiazard.catering;

import be.swsb.fiazard.ordering.bun.Bun;
import com.google.common.collect.Lists;
import com.mongodb.DB;
import org.mongojack.JacksonDBCollection;

import java.util.List;

public class KlaarTeMakenBroodjeRepository {

    private DB db;

    public KlaarTeMakenBroodjeRepository(DB db) {
        this.db = db;
    }

    public List<KlaarTeMakenBroodje> findAll() {
        return Lists.newArrayList(this.collection().find().iterator());
    }

    private JacksonDBCollection<KlaarTeMakenBroodje, String> collection() {
        return JacksonDBCollection.wrap(this.db.getCollection(KlaarTeMakenBroodje.KLAAR_TE_MAKEN_BROODJES_COLL_NAME), KlaarTeMakenBroodje.class, String.class);
    }
}
