
package pay.pimpo.auth.rules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import pay.pimpo.auth.entities.User;
import pay.pimpo.auth.repositories.UserRepository;

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

}
