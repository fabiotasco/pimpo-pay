
package pay.pimpo.auth.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pay.pimpo.auth.rules.UserRules;
import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.api.StandardErrors;
import pay.pimpo.commons.dto.CreateUserDto;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/users")
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRules userRules;

	@PostMapping
	Response<Long> createUser(@RequestBody final CreateUserDto createUserDto) throws Exception {
		try {
			return new Response<>(userRules.createUser(createUserDto.getUsername(), createUserDto.getPassword()));

		} catch (final DataIntegrityViolationException e) {
			LOG.error("Error while creating user: {}.", createUserDto.getUsername(), e);
			return new Response<>(StandardErrors.USER_EXISTS);
		}
	}

	@DeleteMapping("/{id}")
	Response<Long> deleteUser(@PathVariable("id") final Long id) {
		return new Response<>(userRules.deleteUser(id));
	}

}
