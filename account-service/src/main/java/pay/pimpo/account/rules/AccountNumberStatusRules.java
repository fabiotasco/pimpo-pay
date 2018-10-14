
package pay.pimpo.account.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.account.repositories.AccountNumberStatusRepository;
import pay.pimpo.commons.entities.AccountNumberStatus;
import pay.pimpo.commons.exceptions.AccountNumberStatusNotFound;

@Component
public class AccountNumberStatusRules {

	@Autowired
	private AccountNumberStatusRepository accountNumberStatusRepository;

	/**
	 * Busca pelo status.
	 *
	 * @param status Nome do status.
	 * @return O status encontrado.
	 * @throws AccountNumberStatusNotFound Se não encontrar o status informado.
	 */
	public AccountNumberStatus findByStatus(final String status) throws AccountNumberStatusNotFound {
		final AccountNumberStatus accountNumberStatus = accountNumberStatusRepository.findByName(status);
		if (accountNumberStatus == null) {
			throw new AccountNumberStatusNotFound(status);
		}
		return accountNumberStatus;
	}

}
