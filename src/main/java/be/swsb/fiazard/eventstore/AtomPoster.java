package be.swsb.fiazard.eventstore;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AtomPoster {
    //TODO sch3lp: make configurable (as Dropwizard module/provider?)
    public static final String FIAZARD_STREAM = "http://localhost:2113/streams/fiazard";

    private JerseyWebTarget target;

    public AtomPoster() {
        JerseyClient jerseyClient = new JerseyClientBuilder().register(JacksonFeature.class).build();
        target = jerseyClient.target(FIAZARD_STREAM);
    }

    public void post(AtomEvent atomEvent) {
        Response response = target
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("ES-EventId", atomEvent.getUUID())
                .header("ES-EventType", atomEvent.getEventType())
                .post(Entity.json(atomEvent.getPayload()));
        System.out.println(response);
        //TODO sch3lp: errorhandling
    }
}
