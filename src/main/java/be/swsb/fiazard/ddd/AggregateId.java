package be.swsb.fiazard.ddd;

import static org.apache.commons.lang.builder.ToStringStyle.SIMPLE_STYLE;

import java.util.UUID;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AggregateId {

	private String value;

	@JsonCreator
	AggregateId(String value) {
		this.value = value;
	}

	String getValue() {
		return value;
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

	public static AggregateId generate() {
		return new AggregateId(UUID.randomUUID().toString());
	}

}
