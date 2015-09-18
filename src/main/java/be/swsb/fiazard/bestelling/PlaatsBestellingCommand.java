package be.swsb.fiazard.bestelling;

import be.swsb.fiazard.ddd.AbstractCommand;

public class PlaatsBestellingCommand extends AbstractCommand {

	private String naamBesteller;

	public PlaatsBestellingCommand(String naamBesteller) {
		super(VERSION_FOR_NEW_AGGREGATE);
		this.naamBesteller = naamBesteller;
	}
	
	String getNaamBesteller() {
		return naamBesteller;
	}
	
}
