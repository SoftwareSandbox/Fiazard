package be.swsb.fiazard.ddd;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.assertj.core.util.Maps;

import com.google.common.base.Preconditions;

public abstract class AbstractAggregate implements Aggregate {

	private AggregateId aggregateId;
	private List<DomainEvent> unsavedEvents;
	private int version = 0;
	
	private Map<Class<? extends DomainEvent>, Consumer<DomainEvent>> eventReplayStrategies = Maps.newHashMap();

	protected AbstractAggregate(List<DomainEvent> savedEvents) {
		checkArgument(savedEvents != null);

		registerEventReplayStrategies();
		
		savedEvents.forEach(this::applyEventAndAlignVersions);
	}

	protected abstract void registerEventReplayStrategies();
	
	protected final void registerSingleEventReplayStrategy(Class<? extends DomainEvent> domainEventClass, Consumer<DomainEvent> consumer) {
		eventReplayStrategies.put(domainEventClass, consumer);
	}

	private final void applyEventAndAlignVersions(DomainEvent event) {
		alignVersion(event);
		eventReplayStrategies.get(event.getClass()).accept(event);
	}

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
