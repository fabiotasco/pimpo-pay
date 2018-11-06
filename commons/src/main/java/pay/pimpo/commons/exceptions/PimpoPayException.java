
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.Error;

public abstract class PimpoPayException extends Exception {

	private static final long serialVersionUID = 8150909647694419302L;

	private final Error error;

	public PimpoPayException(final Error error) {
		this.error = error;
	}

	public PimpoPayException(final String message, final Error error) {
		super(message);
		this.error = error;
	}

	public PimpoPayException(final String message, final Throwable throwable, final Error error) {
		super(message, throwable);
		this.error = error;
	}

	public Error getError() {
		return error;
	}

}
