
package pay.pimpo.commons.dto;

import javax.validation.constraints.NotNull;

public class HolderAccountDto {

	@NotNull
	private String number;

	HolderAccountDto() {}

	public HolderAccountDto(final String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return number;
	}

}
