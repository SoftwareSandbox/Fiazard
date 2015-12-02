package be.swsb.fiazard.eventstore;


import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.client.JerseyClientBuilder;

import com.google.common.base.Preconditions;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AtomPoster {
    //TODO sch3lp: make configurable (as Dropwizard module/provider?)
    public static final String FIAZARD_STREAM = "http://localhost:2113/streams/fiazard";

    private WebTarget webResource;

    public AtomPoster(String stream) {
    	Preconditions.checkNotNull(stream);

        Client jerseyClient = new JerseyClientBuilder().build();
        jerseyClient.register(JacksonJsonProvider.class);
//        uncomment to see your request in sysout
//        jerseyClient.addFilter(new LoggingFilter(System.out));
        webResource = jerseyClient.target(stream);
    }

    public void post(AtomEvent atomEvent) {
        Response post = webResource
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .header("ES-EventId", atomEvent.getUUID())
                .header("ES-EventType", atomEvent.getEventType())
                .post(Entity.json(atomEvent.getPayload()), Response.class);
        System.out.println(post);
        //TODO sch3lp: errorhandling
    }
}
