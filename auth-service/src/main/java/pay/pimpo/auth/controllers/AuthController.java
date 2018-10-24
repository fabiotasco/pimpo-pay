
package pay.pimpo.auth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@GetMapping("/authenticate")
	ResponseEntity<Void> authenticate() {
		return ResponseEntity.ok().build();
	}

}
