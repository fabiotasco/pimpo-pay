
package pay.pimpo.commons.api;

public class Error {

	private String code;

	private String message;

	public Error() {}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return code + ": " + message;
	}

}
