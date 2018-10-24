
package pay.pimpo.commons.api;

import pay.pimpo.commons.builders.ErrorBuilder;

public class StandardErrors {

	// USER Errors
	public static final Error USER_EXISTS
		= new ErrorBuilder().setCode("USR-0001").setMessage("User already exists!").build();

	// ACCOUNT Errors
	public static final Error ACTIVE_ACCOUNT_NUMBER_NOT_UNIQUE
		= new ErrorBuilder().setCode("ACT-0001").setMessage("There is an account with this active number!").build();

}
