package be.craftsmen.fiazard.main;

import be.craftsmen.fiazard.common.exceptions.FiazardExceptionToJSONMapper;
import be.craftsmen.fiazard.managing.domain.category.CategoryDAO;
import be.craftsmen.fiazard.managing.dw.MongoDBHealthCheck;
import be.craftsmen.fiazard.managing.resource.CategoryResourceV1;
import be.craftsmen.fiazard.managing.resource.OpeningHourResourceV1;
import be.craftsmen.fiazard.managing.resource.ProductResourceV1;
import com.commercehub.dropwizard.mongo.ManagedMongoClient;
import com.mongodb.DB;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import static be.craftsmen.fiazard.managing.representation.util.FiazardJacksonModule.MODULE;

public class FiazardApp extends Application<FiazardConfig> {

    @Override
    public String getName() {
        return "Fiazardtje";
    }

    @Override
    public void initialize(Bootstrap<FiazardConfig> bootstrap) {
    }

    @Override
    public void run(FiazardConfig config, Environment environment) throws Exception {
        ManagedMongoClient mongoClient = config.getMongo().build();
        DB db = mongoClient.getDB(config.getMongo().getDbName());
        environment.lifecycle().manage(config.getMongo().build());

        environment.healthChecks().register("MongoDBHealthCheck", new MongoDBHealthCheck(db));

        environment.jersey().register(FiazardExceptionToJSONMapper.class);
        environment.jersey().register(new CategoryResourceV1(new CategoryDAO(db)));
        environment.jersey().register(new ProductResourceV1());
        environment.jersey().register(new OpeningHourResourceV1());

        environment.getObjectMapper().registerModule(MODULE);
    }

    public static void main(String[] args) throws Exception {
        new FiazardApp().run(args);
    }
}
