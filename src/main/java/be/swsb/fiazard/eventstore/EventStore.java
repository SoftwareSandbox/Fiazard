package be.swsb.fiazard.eventstore;


import java.util.List;

import be.swsb.fiazard.ddd.AggregateId;
import be.swsb.fiazard.ddd.DomainEvent;

public interface EventStore {

    void store(AggregateId aggregateId, List<DomainEvent> events, int version);

    List<DomainEvent> getEventHistoryFor(AggregateId aggregateId);
    
}
