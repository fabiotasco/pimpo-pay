
package pay.pimpo.transaction.converters;

import org.springframework.stereotype.Component;

import pay.pimpo.commons.api.StandardErrors;
import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionEvent;
import pay.pimpo.commons.utils.TransactionUtils;
import pay.pimpo.transaction.dto.StatementTransactionDto;

@Component
public class StatementTransactionDtoConverter {

	public StatementTransactionDto convert(final Transaction transaction, final Long accountId) {
		final Double amountWithSign = addSignToAmount(transaction, accountId);

		// Busca o evento mais recente para adicionar à resposta!
		final TransactionEvent lastTransactionEvent = TransactionUtils.getMostRecentEvent(transaction);
		final String reasonMessage = getReasonMessageFromLastTransactionEvent(lastTransactionEvent);

		return new StatementTransactionDto(
			transaction.getId(),
			transaction.getDate(),
			amountWithSign,
			transaction.getCurrencyType(),
			transaction.getInstallments(),
			transaction.getType(),
			transaction.getPlanType(),
			lastTransactionEvent != null ? lastTransactionEvent.getStatus() : null,
			lastTransactionEvent != null ? lastTransactionEvent.getReasonCode() : null,
			reasonMessage);

	}

	/**
	 * <pre>
	 * +---------------------------------+
	 * |          | HOLDER | DESTINATION |
	 * | PURCHASE |    +   |      -      |
	 * | DEPOSIT  |    +   |     N/A     |
	 * | TRANSFER |    +   |      -      |
	 * +---------------------------------+
	 * </pre>
	 * 
	 * @param transaction
	 * @param accountId
	 * @return
	 */
	private Double addSignToAmount(final Transaction transaction, final Long accountId) {
		final boolean isHolderAccount = transaction.getHolderAccountId() == accountId;

		final Double amount = transaction.getAmount();
		switch (transaction.getType()) {
			case PURCHASE:
			case TRANSFER:
				// Se a transação foi feita pelo holder, é uma saída (negativo).
				// Caso contrário, é uma entrada para a conta destination (positivo).
				return isHolderAccount ? amount * -1 : amount;
			case DEPOSIT:
				// Depósito é sempre uma entrada.
				return amount;
			default:
				throw new IllegalArgumentException(
					"Tipo de transação desconhecido para aplicar o sinal (positivo on negativo): "
						+ transaction.getType());
		}
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
