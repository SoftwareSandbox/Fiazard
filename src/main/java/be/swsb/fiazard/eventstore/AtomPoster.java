package be.swsb.fiazard.eventstore;

import javax.ws.rs.core.MediaType;

import com.google.common.base.Preconditions;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class AtomPoster {

    private WebResource webResource;

    public AtomPoster(String stream) {
    	Preconditions.checkNotNull(stream);

        Client jerseyClient = new Client();
//        uncomment to see your request in sysout
//        jerseyClient.addFilter(new LoggingFilter(System.out));
        webResource = jerseyClient.resource(stream);
    }

    //TODO sch3lp: dw upgrade to 0.9.x works with jersey-client 2.21, this code won't work anymore then
    public void post(AtomEvent atomEvent) {
        ClientResponse response = webResource
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .header("ES-EventId", atomEvent.getUUID())
                .header("ES-EventType", atomEvent.getEventType())
                .post(ClientResponse.class, new Object[]{atomEvent.getPayload()});
        System.out.println(response);
        //TODO sch3lp: errorhandling
    }
}
