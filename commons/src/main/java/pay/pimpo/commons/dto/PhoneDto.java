
package pay.pimpo.commons.dto;

import javax.validation.constraints.NotNull;

import pay.pimpo.commons.entities.AccountNumberStatus;

public class PhoneDto {

	@NotNull
	private String number;

	@NotNull
	private String networkOperator;

	private AccountNumberStatus status;

	PhoneDto() {}

	public PhoneDto(final String number, final String networkOperator) {
		this.number = number;
		this.networkOperator = networkOperator;
	}

	public PhoneDto(final String number, final String networkOperator, final AccountNumberStatus status) {
		this.number = number;
		this.networkOperator = networkOperator;
		this.status = status;
	}

	public String getNumber() {
		return number;
	}

	public String getNetworkOperator() {
		return networkOperator;
	}

	public AccountNumberStatus getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "PhoneDto [number=" + number + ", networkOperator=" + networkOperator + ", status=" + status + "]";
	}

}
