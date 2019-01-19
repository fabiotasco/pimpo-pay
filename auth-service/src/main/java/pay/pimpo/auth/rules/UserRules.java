
package pay.pimpo.auth.rules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import pay.pimpo.auth.entities.User;
import pay.pimpo.auth.repositories.UserRepository;
import pay.pimpo.commons.dto.LoginDto;
import pay.pimpo.commons.exceptions.UserNotFoundException;

@Component
public class UserRules {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public Long createUser(final String username, final String password) {
		final User user = new User(username, encoder.encode(password));
		userRepository.save(user);

		return user.getId();
	}

	public Long deleteUser(final Long id) {
		final Optional<User> userOptional = userRepository.findById(id);

		if (userOptional.isPresent()) {
			userRepository.delete(userOptional.get());
		}
		return id;
	}

	public Long findByUsername(final String username) throws UserNotFoundException {
		final User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UserNotFoundException(username);
		}
		return user.getId();
	}

	public Long login(final LoginDto loginDto) throws UserNotFoundException {
		final User user = userRepository.findByUsername(loginDto.getUsername());
		if (user == null) {
			throw new UserNotFoundException(loginDto.getUsername());
		}
		final boolean passwordMatcher = encoder.matches(loginDto.getPassword(), user.getPassword());
		if (!passwordMatcher) {
			throw new UserNotFoundException(loginDto.getUsername());
		}
		return user.getId();
	}

	public String findUsernameById(final Long id) throws UserNotFoundException {
		final Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			return userOptional.get().getUsername();
		}
		throw new UserNotFoundException(id);
	}

}
