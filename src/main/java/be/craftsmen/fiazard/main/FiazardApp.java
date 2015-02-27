package be.craftsmen.fiazard.main;

import static be.craftsmen.fiazard.managing.representation.util.FiazardJacksonModule.MODULE;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import be.craftsmen.fiazard.common.exceptions.FiazardExceptionToJSONMapper;
import be.craftsmen.fiazard.managing.guice.FiazardManagingModule;
import be.craftsmen.fiazard.managing.resource.CategoryResourceV1;
import be.craftsmen.fiazard.managing.resource.OpeningHourResourceV1;
import be.craftsmen.fiazard.managing.resource.ProductResourceV1;

import com.hubspot.dropwizard.guice.GuiceBundle;

public class FiazardApp extends Application<FiazardConfig> {

    private GuiceBundle<FiazardConfig> guiceBundle;

    @Override
    public String getName() {
        return "Fiazardtje";
    }

    @Override
    public void initialize(Bootstrap<FiazardConfig> bootstrap) {
        guiceBundle = GuiceBundle.<FiazardConfig>newBuilder()
                .addModule(new FiazardManagingModule())
                .setConfigClass(getConfigurationClass())
                .build();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(FiazardConfig config, Environment environment) throws Exception {
//        ManagedMongoClient mongoClient = config.getMongo().build();
//        environment.lifecycle().manage(mongoClient);
//        DB db = mongoClient.getDB(config.getMongo().getDbName());
    	
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
