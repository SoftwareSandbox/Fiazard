package be.swsb.fiazard.common.eventsourcing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.ObjectId;

import java.util.Date;
import java.util.UUID;

public class TrialEvent extends Event {

    @JsonIgnore
    private String ignoredFieldBecauseOfAnnotationOnField;
    private String ignoredFieldBecauseOfAnnotationOnGetter;
    String ignoredFieldBecauseOfMissingGetter;
    @JsonProperty
    String fieldAnnotatedWithJsonPropertyWithoutGetter;
    private String persistedField;

    public TrialEvent() {
        this.ignoredFieldBecauseOfAnnotationOnField = randomUuidString();
        this.ignoredFieldBecauseOfAnnotationOnGetter = randomUuidString();
        this.ignoredFieldBecauseOfMissingGetter = randomUuidString();
        this.persistedField = randomUuidString();
        this.fieldAnnotatedWithJsonPropertyWithoutGetter = randomUuidString();
    }

    @JsonCreator
    public TrialEvent(@ObjectId @JsonProperty("_id") org.bson.types.ObjectId id, @JsonProperty("timestamp") Date timestamp) {
        super(id, timestamp);
    }

    public String getIgnoredFieldBecauseOfAnnotationOnField() {
        return ignoredFieldBecauseOfAnnotationOnField;
    }

    @JsonIgnore
    public String getIgnoredFieldBecauseOfAnnotationOnGetter() {
        return ignoredFieldBecauseOfAnnotationOnGetter;
    }

    public String getPersistedField() {
        return persistedField;
    }

    private String randomUuidString() {
        return UUID.randomUUID().toString();
    }

}
