package be.swsb.fiazard.ordering.preparation;

import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import be.swsb.fiazard.main.FiazardConfig;
import be.swsb.fiazard.main.MongoBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;


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
        OrderItemToBePreparedViewModelDAO orderItemToBePreparedViewModelDAO = new OrderItemToBePreparedViewModelDAO(db(), objectMapper());
        
		environment.jersey().register(new OrderItemToBePreparedViewModelResource(orderItemToBePreparedViewModelDAO));
    }
    
    private DB db() {
    	return mongoBundle.getDb();
    }
    
	private ObjectMapper objectMapper() {
		return mongoBundle.getObjectMapper();
	}

}
