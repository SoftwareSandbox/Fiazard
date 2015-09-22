package be.swsb.fiazard.eventstore;

import be.swsb.fiazard.ddd.AggregateId;
import be.swsb.fiazard.ddd.DomainEvent;

import java.util.List;

import static be.swsb.fiazard.eventstore.EventTypeExtractor.extractEventTypeFrom;

public class EventStoreImpl implements EventStore {

    private AtomPoster atomPoster;

    public EventStoreImpl(AtomPoster atomPoster) {
        this.atomPoster = atomPoster;
    }

    //TODO sch3lp: validate aggregateId in events are all the same as passed aggregateId?
    //TODO sch3lp: do optimistic locking here: given version needs to be same as max(events.version)?
    @Override
    public void store(AggregateId aggregateId, List<DomainEvent> events, int version) {
        events.stream().forEach((event)-> atomPoster.post(new AtomEvent(extractEventTypeFrom(event), event)));
    }

    @Override
    public List<DomainEvent> getEventHistoryFor(AggregateId aggregateId) {
        return null;
    }
}
