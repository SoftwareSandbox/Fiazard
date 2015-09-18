package be.swsb.fiazard.bestelling;

import be.swsb.fiazard.ddd.AbstractCommand;

public class PlaatsBestellingCommand extends AbstractCommand{

	private String naamBesteller;

	public PlaatsBestellingCommand(int versionOfReadModel, String naamBesteller) {
		super(versionOfReadModel);
		this.naamBesteller = naamBesteller;
	}
	
	String getNaamBesteller() {
		return naamBesteller;
	}
	
}
