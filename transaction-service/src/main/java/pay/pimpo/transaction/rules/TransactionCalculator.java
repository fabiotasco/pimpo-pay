
package pay.pimpo.transaction.rules;

import org.springframework.stereotype.Component;

import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.PlanType;
import pay.pimpo.commons.exceptions.InsufficientFundsException;

@Component
public class TransactionCalculator {

	/**
	 * Verifica se a conta possui fundos de acordo com o tipo de transação.
	 *
	 * @param account Conta sensibilizada.
	 * @param amount Valor da transação.
	 * @param planType Tipo do plano.
	 * @return Verdadeiro, se tiver fundos.
	 * @throws InsufficientFundsException Quando não houver fundos para transações do tipo {@link PlanType#PREPAID}.
	 */
	public boolean checkForFunds(final Account account, final Double amount, final PlanType planType)
		throws InsufficientFundsException {
		switch (planType) {
			case PREPAID:
				return checkBalance(account, amount);
			case CREDIT:
				// TODO: Implementar checkCreditLimit(transactionDto, account);
			default:
				throw new UnsupportedOperationException("Operation not available for plan: " + planType);
		}
	}

	private boolean checkBalance(final Account account, final Double amount) throws InsufficientFundsException {
		if (account.getBalance().compareTo(amount) == -1) {
			throw new InsufficientFundsException(account, amount);
		}
		return true;
	}

}
