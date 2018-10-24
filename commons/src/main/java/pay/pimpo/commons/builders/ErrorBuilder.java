
package pay.pimpo.commons.builders;

import pay.pimpo.commons.api.Error;

public class ErrorBuilder {

	private final Error instance;

	public ErrorBuilder() {
		instance = new Error();
	}

	public ErrorBuilder setCode(final String code) {
		instance.setCode(code);
		return this;
	}

	public ErrorBuilder setMessage(final String message) {
		instance.setMessage(message);
		return this;
	}

	public Error build() {
		return instance;
	}

}
