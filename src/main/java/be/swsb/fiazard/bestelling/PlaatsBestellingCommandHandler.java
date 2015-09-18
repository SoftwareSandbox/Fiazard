package be.swsb.fiazard.bestelling;

import be.swsb.fiazard.eventstore.AggregateRepository;

public class PlaatsBestellingCommandHandler {

	private BestellingFactory bestellingFactory;
	
	private AggregateRepository aggregateRepository;
	
	public void handleCommand(PlaatsBestellingCommand command) {		
		Bestelling nieuweBestelling = bestellingFactory.maakNieuwBestellingAan(command);	
		aggregateRepository.saveAggregate(nieuweBestelling);
	}

}
