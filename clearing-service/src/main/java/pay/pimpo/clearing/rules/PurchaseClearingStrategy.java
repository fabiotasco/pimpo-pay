
package pay.pimpo.clearing.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.builders.TransactionEventBuilder;
import pay.pimpo.commons.clients.AccountClient;
import pay.pimpo.commons.clients.TransactionClient;
import pay.pimpo.commons.dto.TransferBalanceDto;
import pay.pimpo.commons.entities.TransactionEvent;
import pay.pimpo.commons.entities.TransactionStatus;

@Component
public class PurchaseClearingStrategy implements ClearingStrategy {

	@Autowired
	private AccountClient accountClient;

	@Autowired
	private TransactionClient transactionClient;

	@Override
	public void clearTransaction(final TransactionEvent transactionEvent) {
		switch (transactionEvent.getTransaction().getPlanType()) {
			case PREPAID:
				clearPrepaidPurchase(transactionEvent);
				break;

			case CREDIT:
				// TODO: Implementar a liquidação do crédito!
			default:
				throw new UnsupportedOperationException(
					"Purchase clearing not supported for plan: " + transactionEvent.getTransaction().getPlanType());
		}
	}

	private void clearPrepaidPurchase(final TransactionEvent transactionEvent) {
		final Response<Void> response = accountClient.transferBalance(new TransferBalanceDto(
			transactionEvent.getTransaction().getHolderAccountId(),
			transactionEvent.getTransaction().getDestinationAccountId(),
			transactionEvent.getTransaction().getAmount()));

		// Adiciona o evento da transação após o processamento.
		if (response.isSuccess()) {
			transactionClient.addEvent(new TransactionEventBuilder().setStatus(TransactionStatus.SETTLED)
				.setTransaction(transactionEvent.getTransaction())
				.build());
		} else {
			transactionClient.addEvent(new TransactionEventBuilder().setStatus(TransactionStatus.DENIED)
				.setTransaction(transactionEvent.getTransaction())
				.setReasonCode(response.getErrors().get(0))
				.build());
		}
	}

}
