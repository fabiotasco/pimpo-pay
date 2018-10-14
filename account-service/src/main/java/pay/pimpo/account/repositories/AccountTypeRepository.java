
package pay.pimpo.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pay.pimpo.commons.entities.AccountType;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {

	AccountType findByName(String name);

}
