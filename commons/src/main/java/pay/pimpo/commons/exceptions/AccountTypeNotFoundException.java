
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

/**
 * Lançada quando não encontrar o tipo de conta.
 *
 * @author fabio.tasco
 */
public class AccountTypeNotFoundException extends PimpoPayException {

	private static final long serialVersionUID = -2386321783929987556L;

	private final String type;

	public AccountTypeNotFoundException(final String type) {
		super("Account Type not found: " + type, StandardErrors.ACCOUNT_TYPE_NOT_FOUND);
		this.type = type;
	}

	/**
	 * @return O tipo que não foi encontrado.
	 */
	public String getType() {
		return type;
	}

}
