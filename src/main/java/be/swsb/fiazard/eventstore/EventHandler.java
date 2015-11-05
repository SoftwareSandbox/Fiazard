package be.swsb.fiazard.eventstore;

import org.apache.abdera.model.Content;

interface EventHandler {
    void handle(Content content);
}
