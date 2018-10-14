
package pay.pimpo.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pay.pimpo.commons.entities.AccountStatus;

public interface AccountStatusRepository extends JpaRepository<AccountStatus, Integer> {

	AccountStatus findByName(String name);

}
