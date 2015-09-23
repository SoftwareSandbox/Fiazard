package be.swsb.fiazard.eventstore;


import be.swsb.fiazard.ddd.DomainEvent;

public class EventTypeExtractor {
    //TODO sch3lp: less ideal because we might want to rename the class, but might not want to rename all goddamn events we ever made that are in fact the same? so do we instead put a getEventType on DomainEvent?
    public static String extractEventTypeFrom(DomainEvent event) {
        return event.getClass().getSimpleName();
    }
}
