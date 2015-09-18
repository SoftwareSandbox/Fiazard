package be.swsb.fiazard.bestelling;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;

public class NieuweBestellingGeplaatstEvent {
	
	private String naamBesteller;
	
	public NieuweBestellingGeplaatstEvent(String naamBesteller) {
		Preconditions.checkArgument(StringUtils.isNotBlank(naamBesteller));
		this.naamBesteller = naamBesteller;
	}
	
	String getNaamBesteller() {
		return naamBesteller;
	}
	
}
