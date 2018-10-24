
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

@Component
public class AccountNumberRules {

	@Autowired
	private AccountNumberRepository accountNumberRepository;

	@Autowired
	private AccountNumberStatusRules accountNumberStatusRules;

	@Autowired
	private NetworkOperatorRules networkOperatorRules;

	/**
	 * Cria um número de conta.
	 *
	 * @param number Número da conta.
	 * @param status Status da conta.
	 * @return O número da conta com o status informado.
	 * @throws Exception Caso a operador ou status informados não sejam encontrados.
	 */
	public AccountNumber createAccountNumber(final PhoneDto phoneDto, final String status, final Account account)
		throws Exception {

		final AccountNumberStatus accountNumberStatus = accountNumberStatusRules.findByStatus(status);
		final NetworkOperator networkOperator = networkOperatorRules.findByName(phoneDto.getNetworkOperator());

		return new AccountNumber(phoneDto.getNumber(), networkOperator, accountNumberStatus, account);
	}

	/**
	 * Verifica se um número já está associado a outra conta. Não é permitido ter o mesmo número ativo vinculado a mais
	 * de uma conta.
	 *
	 * @param number Número da conta.
	 * @Return Verdadeiro, se a conta for única.
	 * @throws ActiveAccountNumberNotUniqueException Caso já exista uma conta com esse número ativado.
	 */
	public boolean checkAccountNumberActiveUniqueness(final String number) throws ActiveAccountNumberNotUniqueException {
		final List<AccountNumber> numbers = accountNumberRepository.findByNumber(number);
		final long activeAccountNumbers = numbers.parallelStream()
			.filter(accountNumber -> accountNumber.getStatus().getName().equals("Active"))
			.count();

		if (activeAccountNumbers > 0) {
			throw new ActiveAccountNumberNotUniqueException(number);
		}
		return true;
	}

}
