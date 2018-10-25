
package pay.pimpo.auth.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pay.pimpo.auth.entities.User;
import pay.pimpo.commons.api.Response;

@RestController
public class AuthController {

	@GetMapping("/authenticate")
	Response<Long> authenticate() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final User user = (User) authentication.getPrincipal();

		return new Response<>(user.getId());
	}

}
