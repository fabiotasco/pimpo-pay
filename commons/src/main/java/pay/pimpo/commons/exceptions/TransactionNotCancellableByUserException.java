
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;
import pay.pimpo.commons.entities.Transaction;

public class TransactionNotCancellableByUserException extends PimpoPayException {

	private static final long serialVersionUID = -9037359692503833611L;

	private final Transaction transaction;
	private final Long userId;

	public TransactionNotCancellableByUserException(final Transaction transaction, final Long userId) {
		super(
			"Transaction " + transaction.getId() + " cannot be cancelled by user: " + userId,
			StandardErrors.TRANSACTION_NOT_CANCELLABLE_BY_USER);
		this.transaction = transaction;
		this.userId = userId;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public Long getUserId() {
		return userId;
	}

}
