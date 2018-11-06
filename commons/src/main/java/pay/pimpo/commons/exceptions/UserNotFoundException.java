
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

public class UserNotFoundException extends PimpoPayException {

	private static final long serialVersionUID = -426691366269015470L;

	private final String username;

	public UserNotFoundException(final String username) {
		super("User not found with username: " + username, StandardErrors.USER_NOT_FOUND);
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
