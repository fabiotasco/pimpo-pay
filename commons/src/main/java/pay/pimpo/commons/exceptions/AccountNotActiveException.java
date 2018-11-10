
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

public class AccountNotActiveException extends PimpoPayException {

	private static final long serialVersionUID = -7247256099636892750L;

	private final String hash;

	public AccountNotActiveException(final String hash) {
		super("Account not active: " + hash, StandardErrors.ACCOUNT_NOT_ACTIVE);
		this.hash = hash;
	}

	public String getHash() {
		return hash;
	}

}
