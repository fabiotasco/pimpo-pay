
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

public class ActiveAccountNumberNotFound extends PimpoPayException {

	private static final long serialVersionUID = -8273820204735886605L;

	private final String number;

	public ActiveAccountNumberNotFound(final String number) {
		super("Active account number not found: " + number, StandardErrors.ACTIVE_ACCOUNT_NUMBER_NOT_FOUND);
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

}
