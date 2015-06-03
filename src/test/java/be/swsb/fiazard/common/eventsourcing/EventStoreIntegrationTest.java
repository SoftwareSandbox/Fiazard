package be.swsb.fiazard.common.eventsourcing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import be.swsb.fiazard.common.mongo.MongoDBRule;
import be.swsb.fiazard.managing.bun.Bun;
import be.swsb.fiazard.managing.condiment.Condiment;
import be.swsb.fiazard.managing.topping.Topping;
import be.swsb.fiazard.ordering.orderplacement.OrderPlaced;
import be.swsb.fiazard.ordering.orderplacement.Sandwich;

public class EventStoreIntegrationTest {

	private static final String ORDER_ID = "15";
	@Rule
	public MongoDBRule mongoDBRule = MongoDBRule.create();
	private EventStore eventStore;
	
	@Before
	public void setUp() {
		eventStore = new EventStore(mongoDBRule.getDB());
	}
	
	@Test
	public void orderPlacedEventCanBeStored() {
		OrderPlaced event = new OrderPlaced(ORDER_ID, Arrays.asList(randomSandwich(), randomSandwich()));
		eventStore.store(event);
		
		List<Event> events = eventStore.findAll();
		assertThat(events).hasSize(1);
		OrderPlaced foundEvent = (OrderPlaced) events.get(0);
		assertThat(foundEvent.getOrderId()).isEqualTo(ORDER_ID);
		assertThat(foundEvent.getSandwiches()).hasSize(2);
	}
	
	@Test
	public void fieldsAnnotatedWithJsonIgnoreAreNotPersisted() {
		TrialEvent event = new TrialEvent();
		
		eventStore.store(event);
		TrialEvent foundEvent = (TrialEvent) eventStore.findAll().get(0);
		assertThat(foundEvent.getIgnoredFieldBecauseOfAnnotationOnField()).isNull();
	}
	
	@Test
	public void fieldsWhoseGetterMethodIsAnnotatedWithJsonIgnoreAreNotPersisted() {
		TrialEvent event = new TrialEvent();
		
		eventStore.store(event);
		TrialEvent foundEvent = (TrialEvent) eventStore.findAll().get(0);
		assertThat(foundEvent.getIgnoredFieldBecauseOfAnnotationOnGetter()).isNull();
	}
	
	@Test
	public void fieldsWithGetterArePersisted() {
		TrialEvent event = new TrialEvent();
		
		eventStore.store(event);
		TrialEvent foundEvent = (TrialEvent) eventStore.findAll().get(0);
		assertThat(foundEvent.getPersistedField()).isNotNull();
		assertThat(foundEvent.getPersistedField()).isEqualTo(event.getPersistedField());
	}
	
	@Test
	public void fieldsWithoutGetterAreNotPersisted() {
		TrialEvent event = new TrialEvent();
		
		eventStore.store(event);
		TrialEvent foundEvent = (TrialEvent) eventStore.findAll().get(0);
		assertThat(foundEvent.ignoredFieldBecauseOfMissingGetter).isNull();
	}
	
	@Test
	public void annotatedfieldWithoutGetterIsPersisted() {
		TrialEvent event = new TrialEvent();
		
		eventStore.store(event);
		TrialEvent foundEvent = (TrialEvent) eventStore.findAll().get(0);
		assertThat(foundEvent.fieldAnnotatedWithJsonPropertyWithoutGetter).isNotNull();
		assertThat(foundEvent.fieldAnnotatedWithJsonPropertyWithoutGetter).isEqualTo(event.fieldAnnotatedWithJsonPropertyWithoutGetter);
	}
	
	private Sandwich randomSandwich() {
		return new Sandwich("someLabel", randomBun(), Arrays.asList(randomTopping()), Arrays.asList(randomCondiment()));
	}

	private Condiment randomCondiment() {
		return new Condiment("condimentId", "condimentName", 18.89);
	}

	private Topping randomTopping() {
		return new Topping("toppingId", "toppingName", 15.89);
	}

	private Bun randomBun() {
		return new Bun("5", "someName", 15.25);
	}

}
