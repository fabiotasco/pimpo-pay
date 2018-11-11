
package pay.pimpo.clearing.rules;

import org.springframework.beans.factory.annotation.Autowired;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.builders.TransactionEventBuilder;
import pay.pimpo.commons.clients.TransactionClient;
import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionStatus;

abstract class ClearingStrategy {

	@Autowired
	private TransactionClient transactionClient;

	abstract void run(Transaction transaction);

	/**
	 * Adiciona um evento de acordo com a resposta.
	 *
	 * @param transaction Transação relacionada ao evento.
	 * @param response Resposta.
	 */
	protected void addTransactionEvent(final Transaction transaction, final Response<?> response) {
		if (response.isSuccess()) {
			transactionClient.addEvent(
				new TransactionEventBuilder().setStatus(TransactionStatus.SETTLED).setTransaction(transaction).build());

		} else {
			transactionClient.addEvent(new TransactionEventBuilder().setStatus(TransactionStatus.DENIED)
				.setTransaction(transaction)
				.setReasonCode(response.getErrors().get(0))
				.build());
		}
	}

}
