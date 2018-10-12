
package pay.pimpo.authentication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AuthenticationController {

	@GetMapping("/alive")
	ResponseEntity<Void> alive() {
		return ResponseEntity.ok().build();
	}

}
