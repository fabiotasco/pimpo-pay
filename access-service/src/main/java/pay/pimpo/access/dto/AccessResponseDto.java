
package pay.pimpo.access.dto;

import java.util.List;

import pay.pimpo.commons.dto.PhoneDto;
import pay.pimpo.commons.entities.AccountStatus;

public class AccessResponseDto {

	private final String hash;
	private final List<ContractDto> contracts;
	private final List<PlanDto> plans;
	private final List<PhoneDto> phones;
	private final AccountStatus status;

	public AccessResponseDto(
		final String hash,
		final List<ContractDto> contracts,
		final List<PlanDto> plans,
		final List<PhoneDto> phones,
		final AccountStatus status) {

		this.hash = hash;
		this.contracts = contracts;
		this.plans = plans;
		this.phones = phones;
		this.status = status;
	}

	public String getHash() {
		return hash;
	}

	public List<ContractDto> getContracts() {
		return contracts;
	}

	public List<PlanDto> getPlans() {
		return plans;
	}

	public List<PhoneDto> getPhones() {
		return phones;
	}

	public AccountStatus getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "EnrollResponseDto [hash=" + hash
			+ ", contracts="
			+ contracts
			+ ", plans="
			+ plans
			+ ", phones="
			+ phones
			+ ", status="
			+ status
			+ "]";
	}

}
