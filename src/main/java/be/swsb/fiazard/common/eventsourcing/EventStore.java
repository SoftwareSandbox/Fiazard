package be.swsb.fiazard.common.eventsourcing;

import java.util.List;

import org.mongojack.JacksonDBCollection;

import com.google.common.collect.Lists;
import com.mongodb.DB;

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
