
package pay.pimpo.transaction.converters;

import java.util.List;

import org.springframework.stereotype.Component;

import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.AccountNumber;
import pay.pimpo.commons.entities.AccountNumberStatus;
import pay.pimpo.transaction.dto.TransactionDetailsAccountDto;

@Component
public class TransactionDetailsAccountDtoConverter {

	public TransactionDetailsAccountDto convert(final String document, final Account account) {
		return new TransactionDetailsAccountDto(document, getActiveNumber(account.getNumbers()).getNumber());
	}

	/**
	 * Busca um número ativo da conta.
	 *
	 * @param accountNumbers Lista de contas.
	 * @return O número ativo.
	 * @duplicated Duplicado de AccountNumberRules#getActiveNumber().
	 */
	public AccountNumber getActiveNumber(final List<AccountNumber> accountNumbers) {
		return accountNumbers.parallelStream()
			.filter(accountNumber -> accountNumber.getStatus() == AccountNumberStatus.ACTIVE)
			.findAny()
			.get();
	}

}
