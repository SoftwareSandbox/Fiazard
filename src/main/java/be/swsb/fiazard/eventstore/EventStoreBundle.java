package be.swsb.fiazard.eventstore;

import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import be.swsb.fiazard.main.FiazardConfig;


public class EventStoreBundle implements ConfiguredBundle<FiazardConfig> {

	private AggregateRepository aggregateRepository;
	private EventStore eventStore;

	@Override
	public void initialize(Bootstrap<?> bootstrap) {
	}
	
    @Override
    public void run(FiazardConfig configuration, Environment environment) throws Exception {
        String eventStoreStream = configuration.getEventStoreStreamURL();
        eventStore = new EventStoreImpl(new AtomPoster(eventStoreStream));
        aggregateRepository = new AggregateRepository(eventStore);
    }

	public AggregateRepository getAggregateRepository() {
		return aggregateRepository;
	}

	public EventStore getEventStore() {
		return eventStore;
	}
    
}
