package be.swsb.fiazard.bestelling;

import com.google.common.base.Preconditions;

import be.swsb.fiazard.ddd.AggregateIdGenerator;

class BestellingFactory {
	
	// TODO jozef+bktid: deze injecteren! hoe?
	private AggregateIdGenerator aggregateIdGenerator = new AggregateIdGenerator();
	
	public BestellingFactory(AggregateIdGenerator aggregateIdGenerator) {
		Preconditions.checkArgument(aggregateIdGenerator != null);
		this.aggregateIdGenerator = aggregateIdGenerator;
	}
	
	Bestelling maakNieuwBestellingAan(PlaatsBestellingCommand plaatsBestellingCommand){
		return new Bestelling(aggregateIdGenerator.generate(), plaatsBestellingCommand.getNaamBesteller());
	}
	
	
}
