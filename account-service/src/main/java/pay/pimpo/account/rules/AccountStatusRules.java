
package pay.pimpo.account.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.account.repositories.AccountStatusRepository;
import pay.pimpo.commons.entities.AccountStatus;
import pay.pimpo.commons.exceptions.AccountStatusNotFoundException;

@Component
public class AccountStatusRules {

	@Autowired
	private AccountStatusRepository accountStatusRepository;

	public AccountStatus findAccountStatus(final String status) throws AccountStatusNotFoundException {
		final AccountStatus accountStatus = accountStatusRepository.findByName(status);
		if (accountStatus == null) {
			throw new AccountStatusNotFoundException(status);
		}
		return accountStatus;
	}

}
