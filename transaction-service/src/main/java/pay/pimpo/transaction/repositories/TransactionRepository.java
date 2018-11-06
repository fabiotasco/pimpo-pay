
package pay.pimpo.transaction.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pay.pimpo.transaction.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
