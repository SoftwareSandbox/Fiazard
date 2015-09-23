package be.swsb.fiazard.bestelling;

import be.swsb.fiazard.eventstore.AggregateRepository;

public class PlaatsBestellingCommandHandler {

	private BestellingFactory bestellingFactory;
	
	private AggregateRepository aggregateRepository;
	
	// TODO jozef+bktid: Bij CommandHandlers die eerst een aggregate moeten reconstrueren from eventstore
	// gaan we een fail fast inbouwen die de versie van het readmodel (zie state op command) checkt tov de versie op de aggregate

    public PlaatsBestellingCommandHandler(BestellingFactory bestellingFactory, AggregateRepository aggregateRepository) {
        this.bestellingFactory = bestellingFactory;
        this.aggregateRepository = aggregateRepository;
    }

    public void handleCommand(PlaatsBestellingCommand command) {
		Bestelling nieuweBestelling = bestellingFactory.maakNieuwBestellingAan(command);	
		aggregateRepository.saveAggregate(nieuweBestelling);
	}

}
