package be.swsb.fiazard.bestelling;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import be.swsb.fiazard.event.Aggregate;
import be.swsb.fiazard.event.AggregateId;

class Bestelling implements Aggregate {

	private String naamBesteller;
	private AggregateId aggregateId;

	Bestelling(AggregateId aggregateId, String naamBesteller) {
		checkArgument(aggregateId != null);
		checkArgument(isNotBlank(naamBesteller));

		this.aggregateId = aggregateId;
		this.naamBesteller = naamBesteller;		
	}
	
	@Override
	public AggregateId getAggregateId() {
		return aggregateId;
	}
	
	String getNaamBesteller() {
		return naamBesteller;
	}
	
}
