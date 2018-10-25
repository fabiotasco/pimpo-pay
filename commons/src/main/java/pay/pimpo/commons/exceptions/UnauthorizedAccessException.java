
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

public class UnauthorizedAccessException extends PimpoPayException {

	private static final long serialVersionUID = -7437979939326924399L;

	public UnauthorizedAccessException() {
		super(StandardErrors.UNAUTHORIZED_ACCESS);
	}

}
