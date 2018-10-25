
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

/**
 * Lançada quando detectar que existe mais de uma conta com o número ativado.
 *
 * @author fabio.tasco
 */
public class ActiveAccountNumberNotUniqueException extends PimpoPayException {

	private static final long serialVersionUID = -4261597320173461560L;

	private final String number;

	public ActiveAccountNumberNotUniqueException(final String number) {
		super(
			"There are other accounts with this number activated: " + number,
			StandardErrors.ACTIVE_ACCOUNT_NUMBER_NOT_UNIQUE);
		this.number = number;
	}

	/**
	 * @return O número da conta duplicada.
	 */
	public String getNumber() {
		return number;
	}

}
