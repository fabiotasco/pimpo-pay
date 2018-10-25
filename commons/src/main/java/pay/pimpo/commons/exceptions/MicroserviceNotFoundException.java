
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

public class MicroserviceNotFoundException extends PimpoPayException {

	private static final long serialVersionUID = 3919214164721137612L;

	public MicroserviceNotFoundException(final String message) {
		super(message, StandardErrors.MICROSERVICE_NOT_FOUND);
	}

}
