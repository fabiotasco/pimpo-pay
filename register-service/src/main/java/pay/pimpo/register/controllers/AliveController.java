
package pay.pimpo.register.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AliveController {

	private static final Logger LOG = LoggerFactory.getLogger(AliveController.class);

	@GetMapping("/alive")
	ResponseEntity<Void> isAlive() {
		LOG.debug("I am alive!!!");
		return ResponseEntity.ok().build();
	}

}
