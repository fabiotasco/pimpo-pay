
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

/**
 * Lançada quando não encontrar o plano.
 *
 * @author fabio.tasco
 */
public class AccountPlanNotFoundException extends PimpoPayException {

	private static final long serialVersionUID = -4567681585091469243L;

	private final String plan;

	public AccountPlanNotFoundException(final String plan) {
		super("Account Plan not found: " + plan, StandardErrors.ACCOUNT_PLAN_NOT_FOUND);
		this.plan = plan;
	}

	public String getPlan() {
		return plan;
	}

}
