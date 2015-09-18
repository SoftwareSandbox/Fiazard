package be.swsb.fiazard.bestelling;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;

class Bestelling {

	private String naamBesteller;

	Bestelling(String naamBesteller) {
		Preconditions.checkArgument(StringUtils.isNotBlank(naamBesteller));
		this.naamBesteller = naamBesteller;		
	}
	
	String getNaamBesteller() {
		return naamBesteller;
	}
	
	
}
