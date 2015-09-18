package be.swsb.fiazard.bestelling;

import com.google.common.base.Preconditions;

import be.swsb.fiazard.event.AggregateIdGenerator;

class BestellingFactory {
	
	private AggregateIdGenerator aggregateIdGenerator;
	
	public BestellingFactory(AggregateIdGenerator aggregateIdGenerator) {
		Preconditions.checkArgument(aggregateIdGenerator != null);
		this.aggregateIdGenerator = aggregateIdGenerator;
	}
	
	Bestelling maakNieuwBestellingAan(PlaatsBestellingCommand plaatsBestellingCommand){
		return new Bestelling(aggregateIdGenerator.generate(), plaatsBestellingCommand.getNaamBesteller());
	}
	
	
}
