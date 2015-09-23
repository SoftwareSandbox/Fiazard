package be.swsb.fiazard.ddd;


public interface DomainEvent {

	AggregateId getAggregateId();

	int getVersion();

	String getEventType();

}
