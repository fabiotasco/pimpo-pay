
package pay.pimpo.access.dto;

import pay.pimpo.commons.entities.AccountContractType;

public class ContractDto {

	private final AccountContractType contractType;

	public ContractDto(final AccountContractType contractType) {
		this.contractType = contractType;
	}

	public AccountContractType getContractType() {
		return contractType;
	}

	@Override
	public String toString() {
		return "ContractDto [contractType=" + contractType + "]";
	}

}
