
package pay.pimpo.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pay.pimpo.commons.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByUserId(Long userid);

	Account findByHash(String hash);

}
