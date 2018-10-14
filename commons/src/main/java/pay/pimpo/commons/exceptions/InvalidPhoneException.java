
package pay.pimpo.commons.exceptions;

/**
 * Exceção lançada quando detectar um telefone inválido.
 * 
 * @author fabio.tasco
 */
public class InvalidPhoneException extends Exception {

	private static final long serialVersionUID = -7445184587960979173L;

	public InvalidPhoneException(final String message) {
		super(message);
	}

}
