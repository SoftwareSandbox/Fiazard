package be.swsb.fiazard.eventstore;


import org.apache.abdera.model.Content;
import org.apache.abdera.model.Element;

public class ConsoleOutputEventHandler implements EventHandler {
    @Override
    public void handle(Content content) {
        for (Element contentElement : content.getElements()) {
            System.out.println(contentElement);
        }
    }
}
