package be.swsb.fiazard.common.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.rules.ExternalResource;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import static be.swsb.fiazard.util.representation.FiazardJacksonModule.MODULE;

public class ClientRule extends ExternalResource {
    private JerseyClient client;

    @Override
    protected void before() throws Throwable {
        client = new JerseyClientBuilder().build();
        client.register(ObjectMapperProvider.class);
    }

    @Provider
    public static class ObjectMapperProvider implements
            ContextResolver<ObjectMapper> {
        @Override
        public ObjectMapper getContext(Class<?> type) {
            return new ObjectMapper().registerModule(MODULE);
        }
    }

    public JerseyClient getClient() {
        return client;
    }
}
