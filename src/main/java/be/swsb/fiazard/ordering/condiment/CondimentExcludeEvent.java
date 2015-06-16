package be.swsb.fiazard.ordering.condiment;

import be.swsb.fiazard.common.eventsourcing.Event;
import be.swsb.fiazard.ordering.bun.Bun;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.ObjectId;

import java.util.Date;

public class CondimentExcludeEvent extends Event {

    private Condiment condiment;

    @JsonCreator
    public CondimentExcludeEvent(@ObjectId @JsonProperty("_id") org.bson.types.ObjectId id,
                                 @JsonProperty("timestamp") Date timestamp,
                                 @JsonProperty("condiment") Condiment condiment) {
        super(id, timestamp);
        this.condiment = condiment;
    }

    public CondimentExcludeEvent(Condiment condiment) {
        this.condiment = condiment;
    }

    public Condiment getCondiment() {
        return condiment;
    }
}
