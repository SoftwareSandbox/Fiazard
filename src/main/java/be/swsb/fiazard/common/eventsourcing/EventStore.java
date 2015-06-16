package be.swsb.fiazard.common.eventsourcing;

import com.google.common.collect.Lists;
import com.mongodb.DB;
import org.mongojack.JacksonDBCollection;

import java.util.List;

public class EventStore {

    private static final String EVENTS_COLL_NAME = "events";
    private DB db;

    public EventStore(DB db) {
        this.db = db;
    }

    public void store(Event event) {
        dbCollection().save(event);
    }

    public List<Event> findAll() {
        return Lists.newArrayList(dbCollection().find().iterator());
    }

    private JacksonDBCollection<Event, String> dbCollection() {
        return JacksonDBCollection.wrap(db.getCollection(EVENTS_COLL_NAME), Event.class, String.class);
    }
}
