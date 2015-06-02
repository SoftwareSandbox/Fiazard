package be.swsb.fiazard.common.eventsourcing;

import java.util.Date;
import java.util.UUID;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {

	private String id;
	private Date timestamp;
	
			
	public Event() {
		this.id = UUID.randomUUID().toString();
		this.timestamp = new Date();
	}
	
    @JsonCreator
	public Event(@ObjectId @JsonProperty("_id") String id, 
			@JsonProperty("timestamp") Date timestamp) {
    	this.id = id;
		this.timestamp = timestamp;
	}

	public String getId() {
		return id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

}
