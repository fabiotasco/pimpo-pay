
package pay.pimpo.commons.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ApiUtilsTest {

	@Test
	public void testFormatEnumName_singleName() {
		final String name = "NAME";
		final String formattedName = ApiUtils.formatEnumName(name);

		assertEquals("Name", formattedName);
	}

	@Test
	public void testFormatEnumName_multipleName() {
		final String name = "NAME_MULTIPLE";
		final String formattedName = ApiUtils.formatEnumName(name);

		assertEquals("Name Multiple", formattedName);
	}

	@Test
	public void testFormatEnumName_nullName() {
		final String name = null;
		final String formattedName = ApiUtils.formatEnumName(name);

		assertNull(formattedName);
	}

	@Test
	public void testFormatEnumName_oneLetterName() {
		final String name = "N";
		final String formattedName = ApiUtils.formatEnumName(name);

		assertEquals("N", formattedName);
	}

	@Test
	public void testFormatEnumName_twoLetterName() {
		final String name = "NA";
		final String formattedName = ApiUtils.formatEnumName(name);

		assertEquals("Na", formattedName);
	}

	@Test
	public void testFormatEnumName_tripleName() {
		final String name = "N_A_O";
		final String formattedName = ApiUtils.formatEnumName(name);

		assertEquals("N A O", formattedName);
	}

}
