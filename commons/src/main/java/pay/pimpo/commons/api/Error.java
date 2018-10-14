
package pay.pimpo.commons.api;

public class Error {

	private int code;

	private String message;

	Error() {}

	public Error(final int code, final String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return code + ": " + message;
	}

}
