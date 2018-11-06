
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.PlanType;

public class AccountNotEnrolledOnPlanException extends PimpoPayException {

	private static final long serialVersionUID = -934675483162060545L;

	private final Account account;
	private final PlanType planType;

	public AccountNotEnrolledOnPlanException(final Account account, final PlanType planType) {
		super(
			"Account " + account.toString() + " not enrolled on plan " + planType,
			StandardErrors.ACCOUNT_NOT_ENROLLED_ON_PLAN);
		this.account = account;
		this.planType = planType;
	}

	public Account getAccount() {
		return account;
	}

	public PlanType getPlanType() {
		return planType;
	}

}
