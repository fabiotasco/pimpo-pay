
package pay.pimpo.authentication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pay.pimpo.authentication.entities.User;
import pay.pimpo.authentication.repositories.UserRepository;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final Optional<User> user = userRepository.findByUsername(username);
		if (!user.isPresent()) {
			throw new BadCredentialsException("Bad credentials");
		}
		new AccountStatusUserDetailsChecker().check(user.get());

		return user.get();
	}

}
