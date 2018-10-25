
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

/**
 * Lançada quando não encontrar o status de um número vinculado a uma conta.
 *
 * @author fabio.tasco
 */
public class AccountNumberStatusNotFound extends PimpoPayException {

	private static final long serialVersionUID = -1547451567115235998L;

	private final String status;

	public AccountNumberStatusNotFound(final String status) {
		super("Account Number Status not found: " + status, StandardErrors.ACCOUNT_NUMBER_STATUS_NOT_FOUND);
		this.status = status;
	}

	/**
	 * @return O status que não foi encontrado.
	 */
	public String getStatus() {
		return status;
	}

}
