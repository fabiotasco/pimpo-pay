
package pay.pimpo.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pay.pimpo.commons.entities.AccountNumber;

public interface AccountNumberRepository extends JpaRepository<AccountNumber, Long> {

}
