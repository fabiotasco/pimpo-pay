
package pay.pimpo.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pay.pimpo.auth.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
