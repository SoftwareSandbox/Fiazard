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

		recordNewEvent(new NieuweBestellingGeplaatstEvent(aggregateId,
				getNextVersion(), naamBesteller));
	}

	@Override
	protected void registerEventReplayStrategies() {
		registerSingleEventReplayStrategy(NieuweBestellingGeplaatstEvent.class, this::initialize);
	}

	private void initialize(DomainEvent event) {
		NieuweBestellingGeplaatstEvent nieuweBestellingGeplaatstEvent = (NieuweBestellingGeplaatstEvent) event;
		setAggregateId(nieuweBestellingGeplaatstEvent.getAggregateId());
		this.naamBesteller = nieuweBestellingGeplaatstEvent.getNaamBesteller();
	}

	String getNaamBesteller() {
		return naamBesteller;
	}

}
