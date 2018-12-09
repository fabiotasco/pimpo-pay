
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
		final List<AccountNumber> accountNumbers = accountNumberRepository.findByNumber(number);
		final long activeAccountNumbersCounter = countActiveAccountNumbers(accountNumbers);

		if (activeAccountNumbersCounter > 0) {
			throw new ActiveAccountNumberNotUniqueException(number);
		}
		return true;
	}

	/**
	 * Conta a quantidade de número ativos na conta.
	 *
	 * @param accountNumbers Lista com os números da conta.
	 * @return
	 */
	private long countActiveAccountNumbers(final List<AccountNumber> accountNumbers) {
		return accountNumbers.parallelStream()
			.filter(accountNumber -> accountNumber.getStatus() == AccountNumberStatus.ACTIVE)
			.count();
	}

	/**
	 * Busca um número ativo da conta.
	 *
	 * @param accountNumbers Lista de contas.
	 * @return O número ativo.
	 */
	public AccountNumber getActiveNumber(final List<AccountNumber> accountNumbers) {
		return accountNumbers.parallelStream()
			.filter(accountNumber -> accountNumber.getStatus() == AccountNumberStatus.ACTIVE)
			.findAny()
			.get();
	}

	/**
	 * Consulta a conta com o número ativo.
	 *
	 * @param number Número da conta.
	 * @return A conta com número ativo.
	 */
	public AccountNumber findActiveAccountNumber(final String number) {
		final List<AccountNumber> numbers = accountNumberRepository.findByNumber(number);
		return getActiveNumber(numbers);
	}

}
