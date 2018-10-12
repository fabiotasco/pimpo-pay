
package pay.pimpo.auth.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

	@GetMapping("/authenticate")
	ResponseEntity<Void> authenticate() {
		LOG.debug("Authenticating...");
		return ResponseEntity.ok().build();
	}

}
