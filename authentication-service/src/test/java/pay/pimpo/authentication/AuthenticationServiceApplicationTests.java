
package pay.pimpo.authentication;

import java.util.Random;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

public class AuthenticationServiceApplicationTests {

	@Test
	public void secretGenerator() {
		final byte[] bytes = new byte[20];
		new Random().nextBytes(bytes);
		final String secret = Hex.encodeHexString(bytes, true);

		System.out.println(secret);

	}

}
