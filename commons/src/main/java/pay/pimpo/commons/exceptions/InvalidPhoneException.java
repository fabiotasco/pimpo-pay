
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

/**
 * Exceção lançada quando detectar um telefone inválido.
 *
 * @author fabio.tasco
 */
public class InvalidPhoneException extends PimpoPayException {

	private static final long serialVersionUID = -7445184587960979173L;

	public InvalidPhoneException(final String message) {
		super(message, StandardErrors.INVALID_PHONE_FORMAT);
	}

}
