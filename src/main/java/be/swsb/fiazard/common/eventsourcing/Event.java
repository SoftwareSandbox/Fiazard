package be.swsb.fiazard.common.eventsourcing;

import java.util.Date;

public class Event {

	private String id;
	private Date timestamp;

	public String getId() {
		return id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

}
