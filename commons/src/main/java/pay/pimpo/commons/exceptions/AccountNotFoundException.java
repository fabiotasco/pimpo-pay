
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

public class AccountNotFoundException extends PimpoPayException {

	private static final long serialVersionUID = -7221275042794439933L;

	public AccountNotFoundException() {
		super("Account not found!", StandardErrors.ACCOUNT_NOT_FOUND);
	}

}
