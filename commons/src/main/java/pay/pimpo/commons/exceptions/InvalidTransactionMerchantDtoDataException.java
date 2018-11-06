
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

public class InvalidTransactionMerchantDtoDataException extends PimpoPayException {

	private static final long serialVersionUID = -4187829993616459653L;

	public InvalidTransactionMerchantDtoDataException() {
		super("Merchant data is missing!", StandardErrors.INVALID_TRANSACTION_MERCHANT_DTO_DATA);
	}

}
