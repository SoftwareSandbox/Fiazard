package be.swsb.fiazard.ddd;

import be.swsb.fiazard.common.eventsourcing.Event;

import java.util.List;

public abstract class AbstractAggregate implements Aggregate {

    private AggregateId aggregateId;
    private List<Event> unsavedEvents;
    private int version;

    protected AbstractAggregate(AggregateId aggregateId, List<Event> savedEvents) {
        this.aggregateId = aggregateId;
        savedEvents.forEach(this::applyEvent);
        // TODO: dit is momenteel de makkelijkst mogelijke implementatie, beter zou zijn om het event te pakken
        // met het hoogste versienummer
        this.version = savedEvents.size();
    }

    protected abstract void applyEvent(Event event);


    @Override
    public AggregateId getAggregateId() {
        return aggregateId;
    }

    @Override
    public List<Event> getUnsavedEvents() {
        return unsavedEvents;
    }

    public int getVersion() {
        return version;
    }
}
