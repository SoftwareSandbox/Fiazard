package be.swsb.fiazard.common.eventsourcing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import be.swsb.fiazard.common.mongo.MongoDBRule;
import be.swsb.fiazard.ordering.orderplacement.OrderPlaced;
import be.swsb.fiazard.ordering.orderplacement.Sandwich;

public class EventStoreIntegrationTest {

	@Rule
	public MongoDBRule mongoDBRule = MongoDBRule.create();
	private EventStore eventStore;
	
	@Before
	public void setUp() {
		eventStore = new EventStore(mongoDBRule.getDB());
	}
	
	@Test
	public void eventCanBeStored() {
		OrderPlaced event = new OrderPlaced("15", new ArrayList<Sandwich>());
		eventStore.store(event);
		
		List<Event> events = eventStore.findAll();
		assertThat(events).hasSize(1);
	}
}
