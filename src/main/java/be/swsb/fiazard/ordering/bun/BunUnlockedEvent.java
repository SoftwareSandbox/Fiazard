package be.swsb.fiazard.ordering.bun;

import java.util.Date;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import be.swsb.fiazard.common.eventsourcing.Event;

public class BunUnlockedEvent extends Event {

	private Bun bun;
	
	@JsonCreator
	public BunUnlockedEvent(@ObjectId @JsonProperty("_id") org.bson.types.ObjectId id, 
			@JsonProperty("timestamp") Date timestamp, 
			@JsonProperty("bun") Bun bun) {
		super(id, timestamp);
		this.bun = bun;
	}
	
	public BunUnlockedEvent(Bun bun) {
		this.bun = bun;
	}

	public Bun getBun() {
		return bun;
	}
}
