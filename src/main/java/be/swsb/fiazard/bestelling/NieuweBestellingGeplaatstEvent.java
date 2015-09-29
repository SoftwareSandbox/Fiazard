package be.swsb.fiazard.bestelling;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import be.swsb.fiazard.ddd.AggregateId;
import be.swsb.fiazard.ddd.DomainEvent;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NieuweBestellingGeplaatstEvent implements DomainEvent {

	@JsonProperty
	private AggregateId aggregateId;
	private int version;
	private String naamBesteller;
	
	public NieuweBestellingGeplaatstEvent(@JsonProperty AggregateId aggregateId, int version, String naamBesteller) {
		checkArgument(aggregateId != null);
		checkArgument(isNotBlank(naamBesteller));
		
		this.aggregateId = aggregateId;
		this.version = version;
		this.naamBesteller = naamBesteller;
	}

	@Override
	public AggregateId getAggregateId() {
		return aggregateId;
	}

	String getNaamBesteller() {
		return naamBesteller;
	}
	
	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public String getEventType() {
		return "NieuweBestellingGeplaatst";
	}

}
