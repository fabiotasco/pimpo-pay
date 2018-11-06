
package pay.pimpo.transaction.rules;

import org.springframework.stereotype.Component;

import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.PlanType;
import pay.pimpo.commons.exceptions.InsufficientFundsException;
import pay.pimpo.transaction.dto.TransactionDto;

@Component
public class TransactionCalculator {

	/**
	 * Verifica se a conta possui fundos de acordo com o tipo de transação.
	 *
	 * @param transactionDto Dados da transação.
	 * @param account Conta sensibilizada.
	 * @return Verdadeiro, se tiver fundos.
	 * @throws InsufficientFundsException Quando não houver fundos para transações do tipo
	 *         {@link PlanType#PREPAID}.
	 */
	public boolean checkForFunds(final TransactionDto transactionDto, final Account account)
		throws InsufficientFundsException {
		switch (transactionDto.getPlan()) {
			case PREPAID:
				return checkBalance(transactionDto, account);
			case CREDIT:
				// TODO: Implementar checkCreditLimit(transactionDto, account);
			default:
				throw new UnsupportedOperationException(
					"Operation not available for plan: " + transactionDto.getPlan());
		}
	}

	private boolean checkBalance(final TransactionDto transactionDto, final Account account)
		throws InsufficientFundsException {

		final Double balance = account.getBalance();
		final Double amount = transactionDto.getAmount();

		if (balance.compareTo(amount) == -1) {
			throw new InsufficientFundsException(transactionDto.getAmount(), account);
		}
		return true;
	}

}
