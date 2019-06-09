
package pay.pimpo.commons.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * @author fabio.tasco
 * @see StandardErrors
 */
public class StandardErrorsTest {

	@Test
	public void testStandardErrorCodeUniqueness() throws Exception {
		final Map<String, Error> standardErrorsCodeMap = new HashMap<>();

		final Field[] fields = StandardErrors.class.getFields();
		for (final Field field : fields) {
			final Object object = field.get(null);

			if (object instanceof Error) {
				final Error value = (Error) object;
				final String key = value.getCode();

				// Verifica se o erro já foi mapeado anteriormente
				final Error mappedError = standardErrorsCodeMap.get(key);
				assertNull("Error code duplicated: " + value.getCode(), mappedError);

				standardErrorsCodeMap.put(key, value);
			} else {
				fail("Only Error is acceptable in StandardError class!");
			}
		}
	}

	@Test
	public void testStandardErrorCodeFormat() throws Exception {
		final Pattern pattern = Pattern.compile("[A-Z]{3}-[0-9]{4}$");

		final Field[] fields = StandardErrors.class.getFields();
		for (final Field field : fields) {
			final Object object = field.get(null);

			if (object instanceof Error) {
				final Error value = (Error) object;
				final String code = value.getCode();

				if (!pattern.matcher(code).matches()) {
					fail("Code does not match pattern [???-####]: " + code);
				}
			}
		}
	}

	@Test
	public void testGetMessageFromCode() throws Exception {
		final String errorCode = "TRX-0003";
		final String message = StandardErrors.getMessage(errorCode);
		assertNotNull(message);
	}

	@Test
	public void testGetMessageFromInvalidCode() throws Exception {
		final String errorCode = "InvalidCode";
		final String message = StandardErrors.getMessage(errorCode);
		assertNull(message);
	}

}
