package be.swsb.fiazard.ddd;


public interface DomainEvent {

	AggregateId getAggregateId();

	void play(Aggregate aggregate);
	
	int getVersion();

}
