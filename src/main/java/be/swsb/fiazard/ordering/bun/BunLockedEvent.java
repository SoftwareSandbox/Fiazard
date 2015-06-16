package be.swsb.fiazard.ordering.bun;

import be.swsb.fiazard.common.eventsourcing.Event;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.ObjectId;

import java.util.Date;

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
