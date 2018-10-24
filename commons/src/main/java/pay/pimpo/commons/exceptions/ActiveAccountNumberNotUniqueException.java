
package pay.pimpo.commons.exceptions;

/**
 * Lançada quando detectar que existe mais de uma conta com o número ativado.
 *
 * @author fabio.tasco
 */
public class ActiveAccountNumberNotUniqueException extends Exception {

	private static final long serialVersionUID = -4261597320173461560L;

	private final String number;

	public ActiveAccountNumberNotUniqueException(final String number) {
		super("There are other accounts with this number activated: " + number);
		this.number = number;
	}

	/**
	 * @return O número da conta duplicada.
	 */
	public String getNumber() {
		return number;
	}

}
