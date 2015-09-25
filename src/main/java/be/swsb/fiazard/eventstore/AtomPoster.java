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

    public AtomPoster(String stream) {
        if (stream == null){
            stream = FIAZARD_STREAM;
        }
        JerseyClient jerseyClient = new JerseyClientBuilder().register(JacksonFeature.class).build();
        target = jerseyClient.target(stream);
    }

    //TODO sch3lp: jersey client conflicts with dropwizards jersey client
//    !java.lang.NoSuchMethodError: javax.ws.rs.core.MultivaluedMap.addAll(Ljava/lang/Object;[Ljava/lang/Object;)V
//    ! at org.glassfish.jersey.client.ClientRequest.accept(ClientRequest.java:326) ~[jersey-client-2.21.jar:na]
//    ! at org.glassfish.jersey.client.JerseyWebTarget.request(JerseyWebTarget.java:229) ~[jersey-client-2.21.jar:na]
//    ! at be.swsb.fiazard.eventstore.AtomPoster.post(AtomPoster.java:28) ~[main/:na]
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
