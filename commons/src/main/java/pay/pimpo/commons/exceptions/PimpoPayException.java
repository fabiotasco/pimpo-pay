
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.Error;

public abstract class PimpoPayException extends RuntimeException {

	private static final long serialVersionUID = 8150909647694419302L;

	private final Error error;

	protected PimpoPayException(final Error error) {
		this.error = error;
	}

	protected PimpoPayException(final String message, final Error error) {
		super(message);
		this.error = error;
	}

	protected PimpoPayException(final String message, final Throwable throwable, final Error error) {
		super(message, throwable);
		this.error = error;
	}

	public Error getError() {
		return error;
	}

}
