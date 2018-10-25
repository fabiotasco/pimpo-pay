
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

/**
 * Lançada quando não encontrar o status da conta.
 *
 * @author fabio.tasco
 */
public class AccountStatusNotFoundException extends PimpoPayException {

	private static final long serialVersionUID = 4233080474991261292L;

	private final String status;

	public AccountStatusNotFoundException(final String status) {
		super("Account Status not found: " + status, StandardErrors.ACCOUNT_STATUS_NOT_FOUND);
		this.status = status;
	}

	/**
	 * @return O status que não foi encontrado.
	 */
	public String getStatus() {
		return status;
	}

}
