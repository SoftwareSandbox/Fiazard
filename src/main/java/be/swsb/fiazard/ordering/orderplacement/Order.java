package be.swsb.fiazard.ordering.orderplacement;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import be.swsb.fiazard.ddd.AbstractAggregate;
import be.swsb.fiazard.ddd.Aggregate;
import be.swsb.fiazard.ddd.AggregateId;
import be.swsb.fiazard.ddd.DomainEvent;

import com.google.common.collect.Lists;

class Order extends AbstractAggregate implements Aggregate {

	private String customerName;

	Order(AggregateId aggregateId, String customerName) {
		super(Lists.newArrayList());

		checkArgument(isNotBlank(customerName));

		recordNewEvent(new NewOrderPlacedEvent(aggregateId, getNextEventVersion(), customerName));
	}

	@Override
	protected void registerEventReplayStrategies() {
		registerSingleEventReplayStrategy(NewOrderPlacedEvent.class, this::initialize);
	}

	private void initialize(DomainEvent event) {
		NewOrderPlacedEvent newOrderPlacedEvent = (NewOrderPlacedEvent) event;
		setAggregateId(newOrderPlacedEvent.getAggregateId());
		this.customerName = newOrderPlacedEvent.getCustomerName();
	}

	String getCustomerName() {
		return customerName;
	}

}
