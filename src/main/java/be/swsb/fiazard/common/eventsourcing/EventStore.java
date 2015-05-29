package be.swsb.fiazard.common.eventsourcing;

public class EventStore {

	public void store(Event event) {
		System.out.println(String.format("event %s stored!", event));
	}
}
