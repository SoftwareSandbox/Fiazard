package be.swsb.fiazard.ddd;

public interface ReplayEventStrategy {

	void replay(DomainEvent domainEvent);
	
}
