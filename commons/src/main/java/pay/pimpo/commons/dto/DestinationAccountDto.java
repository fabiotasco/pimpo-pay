
package pay.pimpo.commons.dto;

public class DestinationAccountDto {

	private String hash;
	// TODO: Alterar o document por number!
	private String document;

	DestinationAccountDto() {}

	public String getHash() {
		return hash;
	}

	public String getDocument() {
		return document;
	}

	@Override
	public String toString() {
		return "MerchantDto [hash=" + hash + ", document=" + document + "]";
	}

}
