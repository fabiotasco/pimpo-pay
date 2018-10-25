
package pay.pimpo.commons.api;

import pay.pimpo.commons.builders.ErrorBuilder;

public class StandardErrors {

	// Auth errors
	public static final Error UNAUTHORIZED_ACCESS
		= new ErrorBuilder().setCode("AUT-0401").setMessage("Unauthorized access!").build();

	// User errors
	public static final Error USER_EXISTS
		= new ErrorBuilder().setCode("USR-0001").setMessage("User already exists!").build();

	// Account errors
	public static final Error ACTIVE_ACCOUNT_NUMBER_NOT_UNIQUE
		= new ErrorBuilder().setCode("ACT-0001").setMessage("Account with this active number already exists!").build();

	public static final Error ACCOUNT_NUMBER_STATUS_NOT_FOUND
		= new ErrorBuilder().setCode("ACT-0002").setMessage("Account number status not found!").build();

	public static final Error ACCOUNT_STATUS_NOT_FOUND
		= new ErrorBuilder().setCode("ACT-0003").setMessage("Account status not found!").build();

	public static final Error ACCOUNT_TYPE_NOT_FOUND
		= new ErrorBuilder().setCode("ACT-0004").setMessage("Account type not found!").build();

	public static final Error INVALID_DOCUMENT_FORMAT
		= new ErrorBuilder().setCode("ACT-0005").setMessage("Invalid document format!").build();

	public static final Error INVALID_PHONE_FORMAT
		= new ErrorBuilder().setCode("ACT-0006").setMessage("Invalid phone format!").build();

	public static final Error NETWORK_OPERATOR_NOT_FOUND
		= new ErrorBuilder().setCode("ACT-0007").setMessage("Network operator not found!").build();

	// Environment errors
	public static final Error MICROSERVICE_NOT_FOUND
		= new ErrorBuilder().setCode("ENV-0001").setMessage("Microservice not found!").build();

	// General errors
	public static final Error INTERNAL_SERVER_ERROR
		= new ErrorBuilder().setCode("GEN-0500").setMessage("Unexpected error ocurred!").build();

}
