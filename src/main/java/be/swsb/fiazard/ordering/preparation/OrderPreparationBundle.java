package be.swsb.fiazard.ordering.preparation;

import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import be.swsb.fiazard.main.FiazardConfig;
import be.swsb.fiazard.main.MongoBundle;


public class OrderPreparationBundle implements ConfiguredBundle<FiazardConfig> {

    private MongoBundle mongoBundle;

	public OrderPreparationBundle(MongoBundle mongoBundle) {
		this.mongoBundle = mongoBundle;
	}

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
    }

    @Override
    public void run(FiazardConfig configuration, Environment environment) throws Exception {
        environment.jersey().register(new OrderItemToBePreparedViewModelResource(new OrderItemToBePreparedViewModelDAO(mongoBundle.getDb())));
    }

}
