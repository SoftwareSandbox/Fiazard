package be.swsb.fiazard.ddd;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public abstract class AbstractAggregate implements Aggregate {

	private AggregateId aggregateId;
	private List<DomainEvent> unsavedEvents;
	private Versioning versioning;
	
	private Map<Class<? extends DomainEvent>, ReplayEventStrategy> eventReplayStrategies = Maps.newHashMap();

	protected AbstractAggregate(List<DomainEvent> eventsToReplay) {
		checkArgument(eventsToReplay != null);

		registerEventReplayStrategies();
		
		eventsToReplay.forEach(this::replayEvent);
		versioning = Versioning.initVersioningAfterAggregateReconstruction(eventsToReplay.size());
	}

	protected abstract void registerEventReplayStrategies();
	
	protected final void registerSingleEventReplayStrategy(Class<? extends DomainEvent> domainEventClass, ReplayEventStrategy strategy) {
		eventReplayStrategies.put(domainEventClass, strategy);
	}

	private final void replayEvent(DomainEvent event) {
		eventReplayStrategies.get(event.getClass()).replay(event);
	}

	protected void setAggregateId(AggregateId aggregateId) {
		this.aggregateId = aggregateId;
	}

	@Override
	public AggregateId getAggregateId() {
		return aggregateId;
	}

	// TODO verwarrend: we zeggen record, maar we doen replay
	protected void recordNewEvent(DomainEvent event) {
		unsavedEvents.add(event);
		versioning = versioning.newEventRecorded(event);
		// TODO verwarrend: we zeggen replay, maar we doen eigenlijk play
		replayEvent(event);
	}

	@Override
	public List<DomainEvent> getUnsavedEvents() {
		return unsavedEvents;
	}
	
	public int getNextEventVersion() {
		return versioning.getNextEventVersion(); 
	}
	
	public int getAggregateVersionAfterReconstruction() {
		return versioning.getAggregateVersionAfterReconstruction();
	};

}
