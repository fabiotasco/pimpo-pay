
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;
import pay.pimpo.commons.entities.Transaction;

public class TransactionNotCancellableException extends PimpoPayException {

	private static final long serialVersionUID = 3391243620871205423L;

	private final Transaction transaction;

	public TransactionNotCancellableException(final Transaction transaction) {
		super("Transaction cannot be cancelled: " + transaction.getId(), StandardErrors.TRANSACTION_NOT_CANCELLABLE);
		this.transaction = transaction;
	}

	public Transaction getTransaction() {
		return transaction;
	}

}
