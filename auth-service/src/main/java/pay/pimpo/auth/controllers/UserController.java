
package pay.pimpo.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pay.pimpo.auth.rules.UserRules;
import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.dto.CreateUserDto;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRules userRules;

	@PostMapping
	Response<Long> createUser(@RequestBody final CreateUserDto createUserDto) {
		return new Response<>(userRules.createUser(createUserDto.getUsername(), createUserDto.getPassword()));
	}

}
