package be.swsb.fiazard.eventstore;


import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.client.JerseyClientBuilder;

import com.google.common.base.Preconditions;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class AtomPoster {
    //TODO sch3lp: make configurable (as Dropwizard module/provider?)
    public static final String FIAZARD_STREAM = "http://localhost:2113/streams/fiazard";

    private WebTarget webResource;

    public AtomPoster(String stream) {
    	Preconditions.checkNotNull(stream);

        Client jerseyClient = new JerseyClientBuilder().build();
//        uncomment to see your request in sysout
//        jerseyClient.addFilter(new LoggingFilter(System.out));
        webResource = jerseyClient.target(stream);
    }

    //TODO sch3lp: dw upgrade to 0.9.x works with jersey-client 2.21, this code won't work anymore then
    public void post(AtomEvent atomEvent) {
        ClientResponse response = webResource
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .header("ES-EventId", atomEvent.getUUID())
                .header("ES-EventType", atomEvent.getEventType())
                .post(Entity.json(new Object[]{atomEvent.getPayload()}), ClientResponse.class);
        System.out.println(response);
        //TODO sch3lp: errorhandling
    }
}
