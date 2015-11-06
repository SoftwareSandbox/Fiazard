package be.swsb.fiazard.ordering.preparation;

import be.swsb.fiazard.ddd.AggregateId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import java.time.LocalDateTime;

@MongoCollection(name = OrderItemToBePreparedViewModel.ORDER_ITEM_TO_BEPREPARED_COLLECTION_NAME)
public class OrderItemToBePreparedViewModel {

    public static final String ORDER_ITEM_TO_BEPREPARED_COLLECTION_NAME = "order_item_to_be_prepared";

    private String id;
    private AggregateId orderId;
    private LocalDateTime deliveryDateTime;
    private String description;

    @JsonCreator
    public OrderItemToBePreparedViewModel(
            @ObjectId @JsonProperty("_id") String id,
            @JsonProperty("orderId") AggregateId orderId,
            @JsonProperty("deliveryDateTime") LocalDateTime deliveryDateTime,
            @JsonProperty("description") String description) {
        this.id = id;
        this.orderId = orderId;
        this.deliveryDateTime = deliveryDateTime;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public AggregateId getOrderId() {
        return orderId;
    }

    public LocalDateTime getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public String getDescription() {
        return description;
    }
}
