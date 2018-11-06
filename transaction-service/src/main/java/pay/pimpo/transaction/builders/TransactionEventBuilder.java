
package pay.pimpo.transaction.builders;

import pay.pimpo.transaction.entities.Transaction;
import pay.pimpo.transaction.entities.TransactionEvent;
import pay.pimpo.transaction.entities.TransactionStatus;
import pay.pimpo.transaction.entities.TransactionType;

public class TransactionEventBuilder {

	private final TransactionEvent instance;

	public TransactionEventBuilder() {
		instance = new TransactionEvent();
	}

	public TransactionEventBuilder setType(final TransactionType type) {
		instance.setType(type);
		return this;
	}

	public TransactionEventBuilder setStatus(final TransactionStatus status) {
		instance.setStatus(status);
		return this;
	}

	public TransactionEventBuilder setTransaction(final Transaction transaction) {
		instance.setTransaction(transaction);
		return this;
	}

	public TransactionEvent build() {
		return instance;
	}

}
