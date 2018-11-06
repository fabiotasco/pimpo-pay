
package pay.pimpo.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pay.pimpo.commons.entities.AccountContract;
import pay.pimpo.commons.entities.AccountContractType;

public interface AccountContractRepository extends JpaRepository<AccountContract, Integer> {

	AccountContract findByContractType(AccountContractType contractType);

}
