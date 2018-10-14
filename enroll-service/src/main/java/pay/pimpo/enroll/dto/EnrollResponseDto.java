
package pay.pimpo.enroll.dto;

import java.util.List;

import pay.pimpo.commons.dto.PhoneDto;

public class EnrollResponseDto {

	private final String hash;
	private final String accountType;
	private final String accountStatus;
	private final List<PlanDto> plans;
	private final List<PhoneDto> phones;

	public EnrollResponseDto(
		final String hash,
		final String accountType,
		final String accountStatus,
		final List<PlanDto> plans,
		final List<PhoneDto> phones) {

		this.hash = hash;
		this.accountType = accountType;
		this.accountStatus = accountStatus;
		this.plans = plans;
		this.phones = phones;
	}

	public String getHash() {
		return hash;
	}

	public String getAccountType() {
		return accountType;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public List<PlanDto> getPlans() {
		return plans;
	}

	public List<PhoneDto> getPhones() {
		return phones;
	}

	@Override
	public String toString() {
		return "EnrollResponseDto [hash=" + hash
			+ ", accountType="
			+ accountType
			+ ", accountStatus="
			+ accountStatus
			+ ", plans="
			+ plans
			+ ", phones="
			+ phones
			+ "]";
	}

}
