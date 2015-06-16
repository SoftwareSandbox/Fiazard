package be.swsb.fiazard.common.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandler;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import org.junit.rules.ExternalResource;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import static be.swsb.fiazard.util.representation.FiazardJacksonModule.MODULE;

public class ClientRule extends ExternalResource {
    private Client client;

    @Override
    protected void before() throws Throwable {
        ClientHandler handler = new URLConnectionClientHandler();
        ClientConfig config =
                new DefaultClientConfig(ObjectMapperProvider.class);
        client = new Client(handler, config);
    }

    @Provider
    public static class ObjectMapperProvider implements
            ContextResolver<ObjectMapper> {
        @Override
        public ObjectMapper getContext(Class<?> type) {
            return new ObjectMapper().registerModule(MODULE);
        }
    }

    public Client getClient() {
        return client;
    }
}
