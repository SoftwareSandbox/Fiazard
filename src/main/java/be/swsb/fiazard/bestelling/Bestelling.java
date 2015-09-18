package be.swsb.fiazard.bestelling;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import be.swsb.fiazard.ddd.AbstractAggregate;
import be.swsb.fiazard.ddd.Aggregate;
import be.swsb.fiazard.ddd.AggregateId;
import be.swsb.fiazard.ddd.DomainEvent;

import com.google.common.collect.Lists;

class Bestelling extends AbstractAggregate implements Aggregate {

	private String naamBesteller;

	Bestelling(AggregateId aggregateId, String naamBesteller) {
		super(Lists.newArrayList());
		
		checkArgument(isNotBlank(naamBesteller));

		DomainEvent event = new NieuweBestellingGeplaatstEvent(aggregateId, naamBesteller);
		addUnsavedEvent(event);
		applyEvent(event);
	}

	@Override
	protected void applyEvent(DomainEvent event) {
		// TODO jozef+bktid: iets mooiers dan instanceof vinden...
		if (event instanceof NieuweBestellingGeplaatstEvent) {
			initialize((NieuweBestellingGeplaatstEvent) event);
		}
	}
	
	private void initialize(NieuweBestellingGeplaatstEvent event) {
		setAggregateId(event.getAggregateId());
		this.naamBesteller = event.getNaamBesteller();
	}

	String getNaamBesteller() {
		return naamBesteller;
	}

}
