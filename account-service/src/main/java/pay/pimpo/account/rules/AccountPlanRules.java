
package pay.pimpo.account.rules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.account.repositories.AccountPlanRepository;
import pay.pimpo.commons.entities.AccountPlan;

@Component
public class AccountPlanRules {

	@Autowired
	private AccountPlanRepository accountPlanRepository;

	public List<AccountPlan> listAccountPlan() {
		return accountPlanRepository.findAll();
	}

}
