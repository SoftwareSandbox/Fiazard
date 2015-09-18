package be.swsb.fiazard.ddd;

import java.util.List;

public interface Aggregate {
	
	AggregateId getAggregateId();

	List<DomainEvent> getUnsavedEvents();

	int getVersionBeforeReplayingUnsavedEvents();
}
