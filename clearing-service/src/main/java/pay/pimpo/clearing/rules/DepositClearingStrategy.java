
package pay.pimpo.clearing.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.clients.AccountClient;
import pay.pimpo.commons.dto.SumAmountDto;
import pay.pimpo.commons.entities.Transaction;

@Component
public class DepositClearingStrategy extends ClearingStrategy {

	@Autowired
	private AccountClient accountClient;

	@Override
	public void run(final Transaction transaction) {
		final Response<Void> response
			= accountClient.sumAmount(new SumAmountDto(transaction.getHolderAccountId(), transaction.getAmount()));

		addTransactionEvent(transaction, response);
	}

}
