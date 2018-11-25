
package pay.pimpo.clearing.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.clients.AccountClient;
import pay.pimpo.commons.dto.TransferBalanceDto;
import pay.pimpo.commons.entities.Transaction;

@Component
public class PurchaseClearingStrategy extends ClearingStrategy {

	@Autowired
	private AccountClient accountClient;

	@Override
	public void clear(final Transaction transaction) {
		switch (transaction.getPlanType()) {
			case PREPAID:
				clearPrepaidPurchase(transaction);
				break;

			case CREDIT:
				// TODO: Implementar a liquidação do crédito!
			default:
				throw new UnsupportedOperationException(
					"Purchase clearing not supported for plan: " + transaction.getPlanType());
		}
	}

	private void clearPrepaidPurchase(final Transaction transaction) {
		final Response<Void> response = accountClient.transferBalance(new TransferBalanceDto(
			transaction.getHolderAccountId(),
			transaction.getDestinationAccountId(),
			transaction.getAmount()));

		addTransactionEvent(transaction, response);
	}

	@Override
	public void cancel(final Transaction transaction) {
		switch (transaction.getPlanType()) {
			case PREPAID:
				cancelPrepaidPurchase(transaction);
				break;
			case CREDIT:
				// TODO: Implementar a liquidação do crédito!
			default:
				throw new UnsupportedOperationException(
					"Purchase cancel not supported for plan: " + transaction.getPlanType());
		}
	}

	private void cancelPrepaidPurchase(final Transaction transaction) {
		accountClient.transferBalance(new TransferBalanceDto(
			transaction.getHolderAccountId(),
			transaction.getDestinationAccountId(),
			-transaction.getAmount()));
	}

}
