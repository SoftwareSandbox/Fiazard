package be.swsb.fiazard.ordering.orderplacement;

import be.swsb.fiazard.common.eventsourcing.Event;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.ObjectId;

import java.util.Date;
import java.util.List;

public class OrderPlaced extends Event {

    private String orderId;
    private List<Sandwich> sandwiches;

    @JsonCreator
    public OrderPlaced(@ObjectId @JsonProperty("_id") org.bson.types.ObjectId id,
                       @JsonProperty("timestamp") Date timestamp,
                       @JsonProperty("orderId") String orderId,
                       @JsonProperty("sandwiches") List<Sandwich> sandwiches) {
        super(id, timestamp);
        this.orderId = orderId;
        this.sandwiches = sandwiches;
    }

    public OrderPlaced(String orderId, List<Sandwich> sandwiches) {
        this.orderId = orderId;
        this.sandwiches = sandwiches;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }
}
