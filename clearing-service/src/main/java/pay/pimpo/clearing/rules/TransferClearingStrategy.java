
package pay.pimpo.clearing.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.clients.AccountClient;
import pay.pimpo.commons.dto.TransferBalanceDto;
import pay.pimpo.commons.entities.Transaction;

@Component
public class TransferClearingStrategy extends ClearingStrategy {

	@Autowired
	private AccountClient accountClient;

	@Override
	void clear(final Transaction transaction) {
		final Response<Void> response = accountClient.transferBalance(new TransferBalanceDto(
			transaction.getHolderAccountId(),
			transaction.getDestinationAccountId(),
			transaction.getAmount()));

		addTransactionEvent(transaction, response);
	}

	@Override
	void cancel(final Transaction transaction) {
		accountClient.transferBalance(new TransferBalanceDto(
			transaction.getHolderAccountId(),
			transaction.getDestinationAccountId(),
			-transaction.getAmount()));
	}

}
