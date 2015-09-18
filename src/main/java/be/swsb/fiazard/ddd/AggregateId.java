package be.swsb.fiazard.ddd;

import static org.apache.commons.lang.builder.ToStringStyle.SIMPLE_STYLE;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class AggregateId {
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	};
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, SIMPLE_STYLE);
	};
	
}
