package be.swsb.fiazard.ddd;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

public abstract class AbstractAggregate implements Aggregate {

	private AggregateId aggregateId;
	private List<DomainEvent> unsavedEvents;
	private int version = 0;

	protected AbstractAggregate(List<DomainEvent> savedEvents) {
		checkArgument(savedEvents != null);

		savedEvents.forEach(this::applyEvent);
	}

	protected abstract void applyEvent(DomainEvent event);

	protected void setAggregateId(AggregateId aggregateId) {
		this.aggregateId = aggregateId;
	}

	@Override
	public AggregateId getAggregateId() {
		return aggregateId;
	}

	protected void addUnsavedEvent(DomainEvent event) {
		unsavedEvents.add(event);
	}

	@Override
	public List<DomainEvent> getUnsavedEvents() {
		return unsavedEvents;
	}

	protected void alignVersion(DomainEvent event) {
		this.version = event.getVersion();
	}

	protected int getNextVersion() {
		return version + 1;
	}

}
