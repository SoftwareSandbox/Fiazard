package be.swsb.fiazard.eventstore;

import java.util.Arrays;

import be.swsb.fiazard.ddd.AggregateId;
import be.swsb.fiazard.ddd.DomainEvent;
import be.swsb.fiazard.ddd.DomainEventTestBuilder;

public class StoreDummyEventMain {

	public static final String FIAZARD_STREAM = "http://localhost:2113/streams/fiazard";
	
    public static void main(String[] args) {
        DomainEvent event = createDummyEvent();
        store(event);
    }

    private static DomainEvent createDummyEvent() {
        return DomainEventTestBuilder.aDumbaEvent().withAggregateId(AggregateId.generate()).withDumbaString("someDumbaString").build();
    }

    private static void store(DomainEvent event) {
        new EventStoreImpl(new AtomPoster(FIAZARD_STREAM)).store(event.getAggregateId(), Arrays.asList(event), 0);
    }
}
