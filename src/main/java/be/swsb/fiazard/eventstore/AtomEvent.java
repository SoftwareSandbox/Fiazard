package be.swsb.fiazard.eventstore;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.UUID;

import static org.apache.commons.lang.builder.ToStringStyle.SIMPLE_STYLE;

public class AtomEvent {

    private UUID uuid;
    private String eventType;
    private Object payload;

    public AtomEvent(String stream, Object payload) {
        this.uuid = UUID.randomUUID();
        this.eventType = stream;
        this.payload = payload;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getEventType() {
        return eventType;
    }

    public Object getPayload() {
        return payload;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SIMPLE_STYLE);
    }
}
