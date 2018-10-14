
package pay.pimpo.commons.exceptions;

/**
 * Exceção lançada quando detectar um documento inválido.
 *
 * @author fabio.tasco
 */
public class InvalidDocumentException extends Exception {

	private static final long serialVersionUID = 4820159386105562247L;

	public InvalidDocumentException(final String message) {
		super(message);
	}

	public InvalidDocumentException(final String message, final Throwable e) {
		super(message, e);
	}

}
