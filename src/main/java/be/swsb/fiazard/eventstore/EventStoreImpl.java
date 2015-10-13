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

    /**
     * Before storing events, does OptimisticLocking on given version
     *
     * @param aggregateId The AggregateId to which all these events belong
     * @param events The new, unsaved events
     * @param version The version that the aggregate was in before the unsaved events were recorded.
     */
    @Override
    public void store(AggregateId aggregateId, List<DomainEvent> events, int version) {
        //TODO sch3lp: validate aggregateId in events are all the same as passed aggregateId, or introduce param object
        //TODO sch3lp: do optimistic locking here: given version needs to be same as max(events.version)?
        events.stream().forEach((event)-> atomPoster.post(new AtomEvent(extractEventTypeFrom(event), event)));
    }

    @Override
    public List<DomainEvent> getEventHistoryFor(AggregateId aggregateId) {
        return null;
    }
}
