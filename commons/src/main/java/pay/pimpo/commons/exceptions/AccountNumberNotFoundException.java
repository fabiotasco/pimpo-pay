
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

public class AccountNumberNotFoundException extends PimpoPayException {

	private static final long serialVersionUID = -3232588668478887036L;

	public AccountNumberNotFoundException(final String number) {
		super("Account number not found: " + number, StandardErrors.ACCOUNT_NUMBER_NOT_FOUND);
	}

}
