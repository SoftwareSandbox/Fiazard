package be.swsb.fiazard.ordering.orderplacement;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import be.swsb.fiazard.ddd.AggregateId;
import be.swsb.fiazard.ddd.DomainEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewOrderPlacedEvent implements DomainEvent {

	@JsonProperty
	private AggregateId aggregateId;
	private int version;
	private String customerName;
	
	public NewOrderPlacedEvent(@JsonProperty AggregateId aggregateId, int version, String customerName) {
		checkArgument(aggregateId != null);
		checkArgument(isNotBlank(customerName));
		
		this.aggregateId = aggregateId;
		this.version = version;
		this.customerName = customerName;
	}

	@Override
	public AggregateId getAggregateId() {
		return aggregateId;
	}

	String getCustomerName() {
		return customerName;
	}
	
	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public String getEventType() {
		return "NewOrderPlaced";
	}

}
