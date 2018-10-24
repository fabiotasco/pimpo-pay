
package pay.pimpo.account.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pay.pimpo.commons.entities.AccountNumber;

public interface AccountNumberRepository extends JpaRepository<AccountNumber, Long> {

	List<AccountNumber> findByNumber(String number);

}
