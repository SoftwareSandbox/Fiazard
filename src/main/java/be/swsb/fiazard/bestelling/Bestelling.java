package be.swsb.fiazard.bestelling;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;

import be.swsb.fiazard.ddd.Aggregate;
import be.swsb.fiazard.ddd.AggregateId;
import be.swsb.fiazard.ddd.DomainEvent;

class Bestelling implements Aggregate {

	private String naamBesteller;
	private AggregateId aggregateId;
	
	private List<DomainEvent> newEvents = new ArrayList<>();

	Bestelling(AggregateId aggregateId, String naamBesteller) {
		checkArgument(aggregateId != null);
		checkArgument(isNotBlank(naamBesteller));

		DomainEvent event = new NieuweBestellingGeplaatstEvent(aggregateId, naamBesteller);
		newEvents.add(event);
		
		playEvents(Lists.<DomainEvent>newArrayList(event));
	}
	
	Bestelling(List<DomainEvent> events) {
		playEvents(events);
	}
	
	private void playEvents(List<DomainEvent> events) {
		// TODO jozef+bktid: apply events
		throw new UnsupportedOperationException();
		
		// this.aggregateId = aggregateId;
		//this.naamBesteller = naamBesteller;
	}
	
	@Override
	public AggregateId getAggregateId() {
		return aggregateId;
	}
	
	String getNaamBesteller() {
		return naamBesteller;
	}
	
}
