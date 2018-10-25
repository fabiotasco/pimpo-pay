
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

/**
 * Exceção lançada quando detectar um documento inválido.
 *
 * @author fabio.tasco
 */
public class InvalidDocumentFormatException extends PimpoPayException {

	private static final long serialVersionUID = 4820159386105562247L;

	public InvalidDocumentFormatException(final String message) {
		super(message, StandardErrors.INVALID_DOCUMENT_FORMAT);
	}

	public InvalidDocumentFormatException(final String message, final Throwable e) {
		super(message, e, StandardErrors.INVALID_DOCUMENT_FORMAT);
	}

}
