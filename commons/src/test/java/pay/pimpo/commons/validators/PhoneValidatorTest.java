
package pay.pimpo.commons.validators;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import pay.pimpo.commons.exceptions.InvalidPhoneException;

public class PhoneValidatorTest {

	private static final String VALID_PHONE = "+5511987654321";
	private PhoneValidator phoneValidator;

	@Before
	public void before() {
		phoneValidator = new PhoneValidator();
	}

	@Test
	public void testValidPhoneNumber() throws InvalidPhoneException {
		assertTrue(phoneValidator.validateNumber(VALID_PHONE));
	}

	@Test(expected = InvalidPhoneException.class)
	public void testInvalidFormat() throws InvalidPhoneException {
		// Remove o sinal de + do número: 5511987654321
		final String number = VALID_PHONE.substring(1, VALID_PHONE.length() - 1);
		phoneValidator.validateNumber(number);
	}

	@Test(expected = InvalidPhoneException.class)
	public void testInvalidLength() throws InvalidPhoneException {
		// Remove os 2 últimos dígitos do número: +55119876543
		final String invalidLengthNumber = VALID_PHONE.substring(0, VALID_PHONE.length() - 3);
		phoneValidator.validateNumber(invalidLengthNumber);
	}

}
