
package pay.pimpo.account.rules;

import org.springframework.stereotype.Component;

import pay.pimpo.commons.dto.DocumentDto;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.AccountContract;

@Component
public class AccountContractRules {

	public AccountContract createContract(final DocumentDto documentDto, final Account account) {
		return new AccountContract(documentDto.getType().getAssociatedContractType(), account);
	}

}
