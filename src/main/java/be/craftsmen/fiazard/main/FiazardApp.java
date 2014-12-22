package be.craftsmen.fiazard.main;

import be.craftsmen.fiazard.managing.guice.FiazardManagingModule;
import be.craftsmen.fiazard.managing.resource.CategoryResourceV1;
import be.craftsmen.fiazard.common.exceptions.FiazardExceptionToJSONMapper;
import be.craftsmen.fiazard.managing.resource.ProductResourceV1;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.Application;

public class FiazardApp extends Application<FiazardConfig> {

    private GuiceBundle<FiazardConfig> guiceBundle;

    @Override
    public String getName() {
        return "Fiazardtje";
    }

    @Override
    public void initialize(Bootstrap bootstrap) {
        guiceBundle = GuiceBundle.<FiazardConfig>newBuilder()
                .addModule(new FiazardManagingModule())
                .setConfigClass(FiazardConfig.class)
                .build();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(FiazardConfig configuration, Environment environment) throws Exception {
        environment.jersey().register(FiazardExceptionToJSONMapper.class);
        environment.jersey().register(CategoryResourceV1.class);
        environment.jersey().register(ProductResourceV1.class);
    }

    public static void main(String[] args) throws Exception {
        new FiazardApp().run(args);
    }
}
