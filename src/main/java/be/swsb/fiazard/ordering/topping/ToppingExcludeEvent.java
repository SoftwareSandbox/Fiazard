package be.swsb.fiazard.ordering.topping;

import be.swsb.fiazard.common.eventsourcing.Event;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.ObjectId;

import java.util.Date;

public class ToppingExcludeEvent extends Event {

    private Topping topping;

    @JsonCreator
    public ToppingExcludeEvent(@ObjectId @JsonProperty("_id") org.bson.types.ObjectId id,
                              @JsonProperty("timestamp") Date timestamp,
                              @JsonProperty("topping") Topping topping) {
        super(id, timestamp);
        this.topping = topping;
    }

    public ToppingExcludeEvent(Topping topping) {
        this.topping = topping;
    }

    public Topping getTopping() {
        return topping;
    }


}
