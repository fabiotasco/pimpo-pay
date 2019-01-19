
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

public class UserNotFoundException extends PimpoPayException {

	private static final long serialVersionUID = -426691366269015470L;

	private String username;
	private Long id;

	public UserNotFoundException(final String username) {
		super("User not found with username: " + username, StandardErrors.USER_NOT_FOUND);
		this.username = username;
	}

	public UserNotFoundException(final Long id) {
		super("User not found with id: " + id, StandardErrors.USER_NOT_FOUND);
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public Long getId() {
		return id;
	}

}
