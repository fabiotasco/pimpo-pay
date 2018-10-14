
package pay.pimpo.account.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.account.repositories.AccountTypeRepository;
import pay.pimpo.commons.entities.AccountType;
import pay.pimpo.commons.exceptions.AccountTypeNotFoundException;

@Component
public class AccountTypeRules {

	@Autowired
	private AccountTypeRepository accountTypeRepository;

	/**
	 * Busca pelo tipo de conta.
	 *
	 * @param type O tipo de conta.
	 * @return O tipo de conta.
	 * @throws AccountTypeNotFoundException Quando não encontrar o tipo de conta informado.
	 */
	public AccountType findAccountType(final String type) throws AccountTypeNotFoundException {
		final AccountType accountType = accountTypeRepository.findByName(type);
		if (accountType == null) {
			throw new AccountTypeNotFoundException(type);
		}
		return accountType;
	}

}
