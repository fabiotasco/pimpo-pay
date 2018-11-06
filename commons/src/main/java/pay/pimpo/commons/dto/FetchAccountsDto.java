
package pay.pimpo.commons.dto;

import pay.pimpo.commons.entities.PlanType;

public class FetchAccountsDto {

	private PlanType planType;
	private DestinationAccountDto destinationAccountDto;
	private HolderAccountDto holderAccountDto;
	private Long userId;

	FetchAccountsDto() {}

	public FetchAccountsDto(
		final PlanType planType,
		final DestinationAccountDto destinationAccountDto,
		final HolderAccountDto holderAccountDto,
		final Long userId) {
		this.planType = planType;
		this.destinationAccountDto = destinationAccountDto;
		this.holderAccountDto = holderAccountDto;
		this.userId = userId;
	}

	public PlanType getPlanType() {
		return planType;
	}

	public DestinationAccountDto getDestinationAccountDto() {
		return destinationAccountDto;
	}

	public HolderAccountDto getHolderAccountDto() {
		return holderAccountDto;
	}

	public Long getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "FetchAccountsDto [planType=" + planType
			+ ", destinationAccountDto="
			+ destinationAccountDto
			+ ", holderAccountDto="
			+ holderAccountDto
			+ ", userId="
			+ userId
			+ "]";
	}

}
