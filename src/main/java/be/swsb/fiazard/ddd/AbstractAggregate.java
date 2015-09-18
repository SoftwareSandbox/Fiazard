package be.swsb.fiazard.ddd;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import com.google.common.base.Preconditions;

public abstract class AbstractAggregate implements Aggregate {

	private AggregateId aggregateId;
	private List<DomainEvent> unsavedEvents;
	private int version = 0;

	protected AbstractAggregate(List<DomainEvent> savedEvents) {
		checkArgument(savedEvents != null);

		savedEvents.forEach(this::applyEventAndAlignVersions);
	}

	private final void applyEventAndAlignVersions(DomainEvent event) {
		alignVersion(event);
		replaySingleEventOnAggregate(event);
	}

	protected abstract void replaySingleEventOnAggregate(DomainEvent event);

	protected void setAggregateId(AggregateId aggregateId) {
		this.aggregateId = aggregateId;
	}

	@Override
	public AggregateId getAggregateId() {
		return aggregateId;
	}

	protected void recordNewEvent(DomainEvent event) {
		unsavedEvents.add(event);
		applyEventAndAlignVersions(event);
	}

	@Override
	public List<DomainEvent> getUnsavedEvents() {
		return unsavedEvents;
	}

	protected void alignVersion(DomainEvent event) {
		Preconditions.checkState(event.getVersion() == getNextVersion());
		this.version = event.getVersion();
	}

	protected int getNextVersion() {
		return version + 1;
	}

}
