package be.swsb.fiazard.ordering.condiment;

import java.util.Date;

import org.mongojack.ObjectId;

import be.swsb.fiazard.common.eventsourcing.Event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CondimentUnlockedEvent extends Event {

	private Condiment condiment;
	
	@JsonCreator
	public CondimentUnlockedEvent(@ObjectId @JsonProperty("_id") org.bson.types.ObjectId id, 
			@JsonProperty("timestamp") Date timestamp, 
			@JsonProperty("condiment") Condiment condiment) {
		super(id, timestamp);
		this.condiment = condiment;
	}
	
	public CondimentUnlockedEvent(Condiment condiment) {
		this.condiment = condiment;
	}

	public Condiment getCondiment() {
		return condiment;
	}

}
