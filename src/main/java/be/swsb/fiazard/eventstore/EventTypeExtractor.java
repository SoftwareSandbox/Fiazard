package be.swsb.fiazard.eventstore;


import be.swsb.fiazard.ddd.DomainEvent;

public class EventTypeExtractor {

    public static String extractEventTypeFrom(DomainEvent event) {
        return event.getEventType();
    }
}
