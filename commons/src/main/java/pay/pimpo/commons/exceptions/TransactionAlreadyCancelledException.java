
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;
import pay.pimpo.commons.entities.Transaction;

public class TransactionAlreadyCancelledException extends PimpoPayException {

	private static final long serialVersionUID = 5669386955876580990L;

	private final Transaction transaction;

	public TransactionAlreadyCancelledException(final Transaction transaction) {
		super("Transaction already cancelled: " + transaction.getId(), StandardErrors.TRANSACTION_ALREADY_CANCELLED);
		this.transaction = transaction;
	}

	public Transaction getTransaction() {
		return transaction;
	}

}
