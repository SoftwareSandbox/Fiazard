package be.swsb.fiazard.eventstore;

import be.swsb.fiazard.ddd.AggregateId;
import be.swsb.fiazard.ddd.AggregateIdGenerator;
import be.swsb.fiazard.ddd.DomainEvent;
import be.swsb.fiazard.ddd.DomainEventTestBuilder;

import java.util.Arrays;

public class StoreDummyEventMain {

    public static void main(String[] args) {
        DomainEvent event = createDummyEvent();
        store(event);
    }

    private static DomainEvent createDummyEvent() {
        AggregateId id = new AggregateIdGenerator().generate();
        return DomainEventTestBuilder.aDumbaEvent().withAggregateId(id).withDumbaString("someDumbaString").build();
    }

    private static void store(DomainEvent event) {
        new EventStoreImpl(new AtomPoster(null)).store(event.getAggregateId(), Arrays.asList(event), 0);
    }
}
