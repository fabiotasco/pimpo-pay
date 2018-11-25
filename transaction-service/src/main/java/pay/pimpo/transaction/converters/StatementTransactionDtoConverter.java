
package pay.pimpo.transaction.converters;

import org.springframework.stereotype.Component;

import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionEvent;
import pay.pimpo.commons.utils.TransactionUtils;
import pay.pimpo.transaction.dto.StatementTransactionDto;

@Component
public class StatementTransactionDtoConverter {

	public StatementTransactionDto convert(final Transaction transaction) {
		// Busca o evento mais recente para adicionar à resposta!
		final TransactionEvent lastTransactionEvent = TransactionUtils.getMostRecentEvent(transaction);

		return new StatementTransactionDto(
			transaction.getId(),
			transaction.getDate(),
			transaction.getAmount(),
			transaction.getCurrencyType(),
			transaction.getInstallments(),
			transaction.getType(),
			transaction.getPlanType(),
			lastTransactionEvent != null ? lastTransactionEvent.getStatus() : null,
			lastTransactionEvent != null ? lastTransactionEvent.getReasonCode() : null);
	}

}
