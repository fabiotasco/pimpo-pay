
package pay.pimpo.account.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.account.repositories.AccountNumberStatusRepository;
import pay.pimpo.account.repositories.AccountStatusRepository;
import pay.pimpo.account.repositories.AccountTypeRepository;
import pay.pimpo.account.repositories.NetworkOperatorRepository;
import pay.pimpo.account.rules.AccountNumberStatusRules;
import pay.pimpo.account.rules.AccountStatusRules;
import pay.pimpo.account.rules.AccountTypeRules;
import pay.pimpo.account.rules.NetworkOperatorRules;
import pay.pimpo.commons.entities.AccountNumberStatus;
import pay.pimpo.commons.entities.AccountStatus;
import pay.pimpo.commons.entities.AccountType;
import pay.pimpo.commons.entities.NetworkOperator;
import pay.pimpo.commons.exceptions.AccountNumberStatusNotFound;
import pay.pimpo.commons.exceptions.AccountStatusNotFoundException;
import pay.pimpo.commons.exceptions.AccountTypeNotFoundException;
import pay.pimpo.commons.exceptions.NetworkOperatorNotFoundException;

@Component
public class AccountHelper {

	private static final String ACTIVE = "Active";
	private static final String ACCOUNT_TYPE_PERSONAL = "Personal";
	private static final String NETWORK_OPERATOR = "Network Operator";

	@Autowired
	private AccountTypeRepository accountTypeRepository;

	@Autowired
	private AccountStatusRepository accountStatusRepository;

	@Autowired
	private AccountNumberStatusRepository accountNumberStatusRepository;

	@Autowired
	private NetworkOperatorRepository networkOperatorRepository;

	@Autowired
	private AccountTypeRules accountTypeRules;

	@Autowired
	private AccountStatusRules accountStatusRules;

	@Autowired
	private NetworkOperatorRules networkOperatorRules;

	@Autowired
	private AccountNumberStatusRules accountNumberStatusRules;

	public void insertDomainValues() {
		accountTypeRepository.save(new AccountType(ACCOUNT_TYPE_PERSONAL));
		accountStatusRepository.save(new AccountStatus(ACTIVE));
		networkOperatorRepository.save(new NetworkOperator(NETWORK_OPERATOR));
		accountNumberStatusRepository.save(new AccountNumberStatus(ACTIVE));
	}

	public AccountType findActiveAccountType() throws AccountTypeNotFoundException {
		return accountTypeRules.findAccountType(ACCOUNT_TYPE_PERSONAL);
	}

	public AccountStatus findActiveAccountStatus() throws AccountStatusNotFoundException {
		return accountStatusRules.findAccountStatus(ACTIVE);
	}

	public NetworkOperator findOperatorNetwork() throws NetworkOperatorNotFoundException {
		return networkOperatorRules.findByName(NETWORK_OPERATOR);
	}

	public AccountNumberStatus findActiveAccountNumberStatus() throws AccountNumberStatusNotFound {
		return accountNumberStatusRules.findByStatus(ACTIVE);
	}

}
