package be.swsb.fiazard.ddd;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

public abstract class AbstractAggregate implements Aggregate {

    private AggregateId aggregateId;
    private List<DomainEvent> unsavedEvents;
    private int version;

    protected AbstractAggregate(List<DomainEvent> savedEvents) {
		checkArgument(savedEvents != null);

        savedEvents.forEach(this::applyEvent);
        // TODO: dit is momenteel de makkelijkst mogelijke implementatie, beter zou zijn om het event te pakken
        // met het hoogste versienummer
        this.version = savedEvents.size();
    }

    protected abstract void applyEvent(DomainEvent event);

    protected void setAggregateId(AggregateId aggregateId) {
		this.aggregateId = aggregateId;
	}

    @Override
    public AggregateId getAggregateId() {
        return aggregateId;
    }

    @Override
    public void addUnsavedEvent(DomainEvent event) {
    	unsavedEvents.add(event);
    }
    
    @Override
    public List<DomainEvent> getUnsavedEvents() {
        return unsavedEvents;
    }

    public int getVersion() {
        return version;
    }
}
