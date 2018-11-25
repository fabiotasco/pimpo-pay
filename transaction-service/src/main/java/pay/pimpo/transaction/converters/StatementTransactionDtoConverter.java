
package pay.pimpo.transaction.converters;

import org.springframework.stereotype.Component;

import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionEvent;
import pay.pimpo.transaction.dto.StatementTransactionDto;

@Component
public class StatementTransactionDtoConverter {

	public StatementTransactionDto convert(final Transaction transaction) {
		TransactionEvent lastTransactionEvent = null;
		if (transaction.getEvents() != null) {
			lastTransactionEvent = transaction.getEvents().get(transaction.getEvents().size() - 1);
		}

		// TODO: Avaliar de inverter o sinal do amount dependendo do tipo de transação (ex: Purchase o valor deveria ser negativo)
		return new StatementTransactionDto(
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
