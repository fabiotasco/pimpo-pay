
package pay.pimpo.transaction.converters;

import org.springframework.stereotype.Component;

import pay.pimpo.commons.api.StandardErrors;
import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionEvent;
import pay.pimpo.commons.utils.TransactionUtils;
import pay.pimpo.transaction.dto.StatementTransactionDto;

@Component
public class StatementTransactionDtoConverter {

	public StatementTransactionDto convert(final Transaction transaction) {
		// Busca o evento mais recente para adicionar à resposta!
		final TransactionEvent lastTransactionEvent = TransactionUtils.getMostRecentEvent(transaction);

		final String reasonMessage = getReasonMessageFromLastTransactionEvent(lastTransactionEvent);

		return new StatementTransactionDto(
			transaction.getId(),
			transaction.getDate(),
			transaction.getAmount(),
			transaction.getCurrencyType(),
			transaction.getInstallments(),
			transaction.getType(),
			transaction.getPlanType(),
			lastTransactionEvent != null ? lastTransactionEvent.getStatus() : null,
			lastTransactionEvent != null ? lastTransactionEvent.getReasonCode() : null,
			reasonMessage);

	}

	private String getReasonMessageFromLastTransactionEvent(final TransactionEvent lastTransactionEvent) {
		if (lastTransactionEvent == null) {
			return null;
		}
		try {
			return StandardErrors.getMessage(lastTransactionEvent.getReasonCode());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// Se não conseguirmos acessar as variáveis, não haverá mensagem de retorno.
			return null;
		}

	}

}
