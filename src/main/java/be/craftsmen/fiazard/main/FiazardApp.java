package be.craftsmen.fiazard.main;

import be.craftsmen.fiazard.common.exceptions.FiazardExceptionToJSONMapper;
import be.craftsmen.fiazard.managing.dw.MongoDBHealthCheck;
import be.craftsmen.fiazard.managing.guice.FiazardManagingModule;
import be.craftsmen.fiazard.managing.resource.CategoryResourceV1;
import be.craftsmen.fiazard.managing.resource.OpeningHourResourceV1;
import be.craftsmen.fiazard.managing.resource.ProductResourceV1;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import static be.craftsmen.fiazard.managing.representation.util.FiazardJacksonModule.MODULE;

public class FiazardApp extends Application<FiazardConfig> {

    private GuiceBundle<FiazardConfig> guiceBundle;

    @Override
    public String getName() {
        return "Fiazardtje";
    }

    @Override
    public void initialize(Bootstrap<FiazardConfig> bootstrap) {
        guiceBundle = GuiceBundle.<FiazardConfig>newBuilder()
                .setConfigClass(getConfigurationClass())
                .addModule(new FiazardManagingModule())
                .build();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(FiazardConfig config, Environment environment) throws Exception {
        environment.lifecycle().manage(config.getMongo().build());
        environment.healthChecks().register("MongoDBHealthCheck", guiceBundle.getInjector().getInstance(MongoDBHealthCheck.class));
        environment.jersey().register(FiazardExceptionToJSONMapper.class);
        environment.jersey().register(CategoryResourceV1.class);
        environment.jersey().register(ProductResourceV1.class);
        environment.jersey().register(OpeningHourResourceV1.class);

        environment.getObjectMapper().registerModule(MODULE);
    }

    public static void main(String[] args) throws Exception {
        new FiazardApp().run(args);
    }
}
