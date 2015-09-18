package be.swsb.fiazard.bestelling;

public class PlaatsBestellingCommand {

	private String naamBesteller;

	public PlaatsBestellingCommand(String naamBesteller) {
		this.naamBesteller = naamBesteller;
	}
	
	String getNaamBesteller() {
		return naamBesteller;
	}
	
}
