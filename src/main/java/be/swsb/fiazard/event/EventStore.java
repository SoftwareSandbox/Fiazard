package be.swsb.fiazard.event;


import java.util.List;

public interface EventStore {

    void store(AggregateId aggregateId, List<Event> events, int version);

    List<Event> getEventHistoryFor(AggregateId aggregateId);
    
}
