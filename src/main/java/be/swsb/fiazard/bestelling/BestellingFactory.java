package be.swsb.fiazard.bestelling;

class BestellingFactory {
	
	Bestelling maakNieuwBestellingAan(PlaatsBestellingCommand plaatsBestellingCommand){
		return new Bestelling (plaatsBestellingCommand.getNaamBesteller());
	}
	
	
}
