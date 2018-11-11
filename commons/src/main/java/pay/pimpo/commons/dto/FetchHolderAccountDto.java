
package pay.pimpo.commons.dto;

public class FetchHolderAccountDto {

	private HolderAccountDto holderAccountDto;
	private Long userId;

	FetchHolderAccountDto() {}

	public FetchHolderAccountDto(final HolderAccountDto holderAccountDto, final Long userId) {
		this.holderAccountDto = holderAccountDto;
		this.userId = userId;
	}

	public HolderAccountDto getHolderAccountDto() {
		return holderAccountDto;
	}

	public Long getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "FetchHolderAccountsDto [holderAccountDto=" + holderAccountDto + ", userId=" + userId + "]";
	}

}
