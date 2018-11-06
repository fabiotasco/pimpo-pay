
package pay.pimpo.account.rules;

import org.springframework.stereotype.Component;

import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.AccountPlan;
import pay.pimpo.commons.entities.PlanType;

@Component
public class AccountPlanRules {

	public AccountPlan createPlan(final Account account) {
		return new AccountPlan(PlanType.PREPAID, account);
	}

}
