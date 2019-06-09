
package pay.pimpo.transaction.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pay.pimpo.commons.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	Page<Transaction> findAllByHolderAccountIdOrDestinationAccountIdOrderByDateDesc(
		Long holderAccountId,
		Long destinationAccountId,
		Pageable pageable);

}
