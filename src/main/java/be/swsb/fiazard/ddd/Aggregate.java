package be.swsb.fiazard.ddd;

import java.util.List;

public interface Aggregate {
	
	AggregateId getAggregateId();

	void addUnsavedEvent(DomainEvent event);
		
	List<DomainEvent> getUnsavedEvents();
}
