package be.swsb.fiazard.common.eventsourcing;

import java.util.Date;

import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@MongoCollection(name = "events")
@JsonTypeInfo(use=Id.CLASS, include=As.PROPERTY, property="type")
public class Event {

	@JsonProperty("_id")
	private org.bson.types.ObjectId id;
	private Date timestamp;
	
	public Event() {
		this.id = new org.bson.types.ObjectId();
		this.timestamp = new Date();
	}
	
    @JsonCreator
	public Event(@ObjectId @JsonProperty("_id") org.bson.types.ObjectId id, @JsonProperty("timestamp") Date timestamp) {
    	this.id = id;
		this.timestamp = timestamp;
	}

	public org.bson.types.ObjectId getId() {
		return id;
	}

	public Date getTimestamp() {
		return timestamp;
	}
}
