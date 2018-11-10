
package pay.pimpo.commons.builders;

import pay.pimpo.commons.api.Error;
import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionEvent;
import pay.pimpo.commons.entities.TransactionStatus;

public class TransactionEventBuilder {

	private final TransactionEvent instance;

	public TransactionEventBuilder() {
		instance = new TransactionEvent();
	}

	public TransactionEventBuilder setStatus(final TransactionStatus status) {
		instance.setStatus(status);
		return this;
	}

	public TransactionEventBuilder setReasonCode(final Error error) {
		instance.setReasonCode(error.getCode());
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
