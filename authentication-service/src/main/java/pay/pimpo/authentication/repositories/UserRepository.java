
package pay.pimpo.authentication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pay.pimpo.authentication.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

}
