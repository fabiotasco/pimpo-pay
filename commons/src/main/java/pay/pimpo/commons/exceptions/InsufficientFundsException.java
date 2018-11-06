
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;
import pay.pimpo.commons.entities.Account;

public class InsufficientFundsException extends PimpoPayException {

	private static final long serialVersionUID = -4977740841779735184L;

	private final Double amount;
	private final Account account;

	public InsufficientFundsException(final Double amount, final Account account) {
		super(
			String
				.format("Insufficient funds for transaction amount of %.2f for account %s", amount, account.getHash()),
			StandardErrors.INSUFFICIENT_FUNDS);
		this.amount = amount;
		this.account = account;
	}

	public Double getAmount() {
		return amount;
	}

	public Account getAccount() {
		return account;
	}

}
