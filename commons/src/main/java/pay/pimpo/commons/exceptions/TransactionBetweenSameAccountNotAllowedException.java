
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;
import pay.pimpo.commons.entities.Account;

public class TransactionBetweenSameAccountNotAllowedException extends PimpoPayException {

	private static final long serialVersionUID = 9209877401901086642L;

	private final Account account;

	public TransactionBetweenSameAccountNotAllowedException(final Account account) {
		super(
			"Transactions between the same account are not allowed: " + account.getId(),
			StandardErrors.TRANSACTION_BETWEEN_SAME_ACCOUNT_NOT_ALLOWED);
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

}
