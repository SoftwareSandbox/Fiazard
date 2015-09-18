package be.swsb.fiazard.eventstore;

import be.swsb.fiazard.ddd.Aggregate;

public class AggregateRepository {
	
	private EventStore eventStore;
	
	public void saveAggregate(Aggregate aggregate){
		eventStore.store(aggregate.getAggregateId(), aggregate.getUnsavedEvents(), aggregate.getAggregateVersionAfterReconstruction());
	}
	
}
