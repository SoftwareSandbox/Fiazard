package be.swsb.fiazard.ddd;

import java.util.UUID;

public class AggregateIdGenerator {
	
	public AggregateId generate() {
		return new AggregateId(UUID.randomUUID().toString());
	}
}
