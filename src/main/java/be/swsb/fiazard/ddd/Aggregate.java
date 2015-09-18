package be.swsb.fiazard.ddd;

import be.swsb.fiazard.common.eventsourcing.Event;

import java.util.List;

public interface Aggregate {
	
	AggregateId getAggregateId();

	List<Event> getUnsavedEvents();
}
