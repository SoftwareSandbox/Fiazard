package be.swsb.fiazard.ordering.bun;

import java.util.Date;

import org.mongojack.ObjectId;

import be.swsb.fiazard.common.eventsourcing.Event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BunLockedEvent extends Event {

	private Bun bun;
	
	@JsonCreator
	public BunLockedEvent(@ObjectId @JsonProperty("_id") org.bson.types.ObjectId id, 
			@JsonProperty("timestamp") Date timestamp, 
			@JsonProperty("bun") Bun bun) {
		super(id, timestamp);
		this.bun = bun;
	}
	
	public BunLockedEvent(Bun bun) {
		this.bun = bun;
	}

	public Bun getBun() {
		return bun;
	}

}
