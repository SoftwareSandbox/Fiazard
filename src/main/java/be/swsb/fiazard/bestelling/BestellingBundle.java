package be.swsb.fiazard.bestelling;

import be.swsb.fiazard.ddd.AggregateIdGenerator;
import be.swsb.fiazard.eventstore.AggregateRepository;
import be.swsb.fiazard.eventstore.AtomPoster;
import be.swsb.fiazard.eventstore.EventStore;
import be.swsb.fiazard.eventstore.EventStoreImpl;
import be.swsb.fiazard.main.FiazardConfig;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class BestellingBundle implements ConfiguredBundle<FiazardConfig> {

    @Override
    public void run(FiazardConfig configuration, Environment environment) throws Exception {
        String eventStoreStream = configuration.getEventStoreStreamURL();
        EventStore eventStore = new EventStoreImpl(new AtomPoster(eventStoreStream));
        BestellingFactory bestellingFactory = new BestellingFactory(new AggregateIdGenerator());
        AggregateRepository aggregateRepo = new AggregateRepository(eventStore);

        environment.jersey().register(new BestellingResource(bestellingFactory, aggregateRepo));
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
    }
}
