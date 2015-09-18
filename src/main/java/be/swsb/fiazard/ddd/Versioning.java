package be.swsb.fiazard.ddd;

import static com.google.common.base.Preconditions.checkState;
import static org.apache.commons.lang.builder.ToStringStyle.SIMPLE_STYLE;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

class Versioning {

	private int aggregateVersionAfterReconstruction;
	private int currentAggregateVersion;

	private Versioning(int aggregateVersionAfterReconstruction, int currentAggregateVersion) {
		this.aggregateVersionAfterReconstruction = aggregateVersionAfterReconstruction;
		this.currentAggregateVersion = currentAggregateVersion;
	}

	static Versioning initVersioningAfterAggregateReconstruction(int initialAggregateVersion) {
		return new Versioning(initialAggregateVersion, initialAggregateVersion);
	}

	Versioning newEventRecorded(DomainEvent event) {
		checkState(event.getVersion() == getNextEventVersion());

		return new Versioning(this.aggregateVersionAfterReconstruction, event.getVersion());
	}
	
	int getNextEventVersion() {
		return currentAggregateVersion + 1;
	}
	
	int getAggregateVersionAfterReconstruction() {
		return aggregateVersionAfterReconstruction;
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	};
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, SIMPLE_STYLE);
	};

}
