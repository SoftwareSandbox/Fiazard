package be.swsb.fiazard.common.eventsourcing;

import be.swsb.fiazard.common.mongo.MongoDBRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

}
