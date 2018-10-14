
package pay.pimpo.commons.dto;

import javax.validation.constraints.NotNull;

public class PhoneDto {

	@NotNull
	private String number;

	@NotNull
	private String networkOperator;

	private String status;

	PhoneDto() {}

	public PhoneDto(final String number, final String networkOperator, final String status) {
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

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "PhoneDto [number=" + number + ", networkOperator=" + networkOperator + ", status=" + status + "]";
	}

}
