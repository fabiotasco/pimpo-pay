
package pay.pimpo.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OAuthClientSecretGenerator {

	private static final Logger LOG = LoggerFactory.getLogger(OAuthClientSecretGenerator.class);

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	public void generateClientSecret() {
		LOG.debug(encoder.encode("123456"));
	}

}
