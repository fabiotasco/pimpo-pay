
package pay.pimpo.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pay.pimpo.commons.entities.AccountNumberStatus;

public interface AccountNumberStatusRepository extends JpaRepository<AccountNumberStatus, Integer> {

	AccountNumberStatus findByName(String name);

}
