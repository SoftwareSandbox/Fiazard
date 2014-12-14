package be.craftsmen.fiazard.main;

import be.craftsmen.fiazard.resource.CategoryResourceV1;
import be.craftsmen.fiazardtje.exceptions.FiazardExceptionToJSONMapper;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.Application;

public class FiazardApp extends Application<FiazardConfig> {

    public static void main(String[] args) throws Exception {
        new FiazardApp().run(args);
    }

    @Override
    public String getName() {
        return "Fiazardtje";
    }

    @Override
    public void initialize(Bootstrap bootstrap) {

    }

    @Override
    public void run(FiazardConfig configuration, Environment environment) throws Exception {
        environment.jersey().register(new FiazardExceptionToJSONMapper());
        environment.jersey().register(new CategoryResourceV1());
    }
}
