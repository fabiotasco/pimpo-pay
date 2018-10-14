
package pay.pimpo.commons.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Gerador de hash baseado no algorítmo SHA-512.
 *
 * @author fabio.tasco
 */
public class HashGenerator {

	private static final Logger LOG = LoggerFactory.getLogger(HashGenerator.class);

	private HashGenerator() {}

	public static String generate(final String text, final String salt) {
		try {
			final MessageDigest md = MessageDigest.getInstance("SHA-256");

			md.update(salt.getBytes(StandardCharsets.UTF_8));
			final byte[] bytes = md.digest(text.getBytes(StandardCharsets.UTF_8));

			final StringBuilder sb = new StringBuilder();
			for (final byte b : bytes) {
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();

		} catch (final NoSuchAlgorithmException e) {
			LOG.error("Algorithm not available for hashing!", e);
			return null;
		}
	}

}
