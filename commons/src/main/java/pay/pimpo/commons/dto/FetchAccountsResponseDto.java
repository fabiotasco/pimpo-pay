
package pay.pimpo.commons.dto;

import pay.pimpo.commons.entities.Account;

public class FetchAccountsResponseDto {

	private Account holderAccount;
	private Account destinationAccount;

	FetchAccountsResponseDto() {}

	public FetchAccountsResponseDto(final Account holderAccount, final Account destinationAccount) {
		this.holderAccount = holderAccount;
		this.destinationAccount = destinationAccount;
	}

	public Account getHolderAccount() {
		return holderAccount;
	}

	public Account getDestinationAccount() {
		return destinationAccount;
	}

	@Override
	public String toString() {
		return "FetchAccountsResponseDto [holderAccount=" + holderAccount
			+ ", destinationAccount="
			+ destinationAccount
			+ "]";
	}

}
