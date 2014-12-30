package be.craftsmen.fiazard.main;

import be.craftsmen.fiazard.managing.guice.FiazardManagingModule;
import be.craftsmen.fiazard.managing.resource.CategoryResourceV1;
import be.craftsmen.fiazard.common.exceptions.FiazardExceptionToJSONMapper;
import be.craftsmen.fiazard.managing.resource.ProductResourceV1;
import com.commercehub.dropwizard.mongo.ManagedMongoClient;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.mongodb.DB;
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
    }

    public static void main(String[] args) throws Exception {
        new FiazardApp().run(args);
    }
}
