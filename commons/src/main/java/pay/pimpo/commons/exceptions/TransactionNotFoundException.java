
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

public class TransactionNotFoundException extends PimpoPayException {

	private static final long serialVersionUID = 3663628437041487240L;

	public TransactionNotFoundException() {
		super(StandardErrors.TRANSACTION_NOT_FOUND);
	}

}
