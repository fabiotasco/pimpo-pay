
package pay.pimpo.account.rules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.account.repositories.AccountNumberRepository;
import pay.pimpo.commons.dto.PhoneDto;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.AccountNumber;
import pay.pimpo.commons.entities.AccountNumberStatus;
import pay.pimpo.commons.entities.NetworkOperator;
import pay.pimpo.commons.exceptions.ActiveAccountNumberNotUniqueException;
import pay.pimpo.commons.exceptions.NetworkOperatorNotFoundException;

@Component
public class AccountNumberRules {

	@Autowired
	private AccountNumberRepository accountNumberRepository;

	@Autowired
	private NetworkOperatorRules networkOperatorRules;

	/**
	 * Cria um número de conta.
	 *
	 * @param number Número da conta.
	 * @param status Status da conta.
	 * @return O número da conta com o status informado.
	 * @throws NetworkOperatorNotFoundException Caso a operador informados não seja encontrado.
	 */
	public AccountNumber
		createAccountNumber(final PhoneDto phoneDto, final AccountNumberStatus status, final Account account)
			throws NetworkOperatorNotFoundException {

		final NetworkOperator networkOperator = networkOperatorRules.findByName(phoneDto.getNetworkOperator());
		return new AccountNumber(phoneDto.getNumber(), networkOperator, status, account);
	}

	/**
	 * Verifica se um número já está associado a outra conta. Não é permitido ter o mesmo número ativo vinculado a mais
	 * de uma conta.
	 *
	 * @param number Número da conta.
	 * @Return Verdadeiro, se a conta for única.
	 * @throws ActiveAccountNumberNotUniqueException Caso já exista uma conta com esse número ativado.
	 */
	public boolean checkAccountNumberActiveUniqueness(final String number)
		throws ActiveAccountNumberNotUniqueException {
		final List<AccountNumber> numbers = accountNumberRepository.findByNumber(number);
		final long activeAccountNumbers = numbers.parallelStream()
			.filter(accountNumber -> accountNumber.getStatus() == AccountNumberStatus.ACTIVE)
			.count();

		if (activeAccountNumbers > 0) {
			throw new ActiveAccountNumberNotUniqueException(number);
		}
		return true;
	}

	public AccountNumber findActiveNumber(final List<AccountNumber> numbers) {
		return numbers.parallelStream()
			.filter(accountNumber -> accountNumber.getStatus() == AccountNumberStatus.ACTIVE)
			.findAny()
			.get();
	}

}
