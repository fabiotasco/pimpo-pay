
package pay.pimpo.commons.dto;

public class DestinationAccountDto {

	private String hash;
	private String number;

	DestinationAccountDto() {}

	public String getHash() {
		return hash;
	}

	public String getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "DestinationAccountDto [hash=" + hash + ", number=" + number + "]";
	}

}
