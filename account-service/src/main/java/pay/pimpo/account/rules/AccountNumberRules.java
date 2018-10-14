
package pay.pimpo.account.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.commons.dto.PhoneDto;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.AccountNumber;
import pay.pimpo.commons.entities.AccountNumberStatus;
import pay.pimpo.commons.entities.NetworkOperator;

@Component
public class AccountNumberRules {

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

}
