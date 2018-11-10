
package pay.pimpo.transaction.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pay.pimpo.commons.entities.TransactionEvent;

public interface TransactionEventRepository extends JpaRepository<TransactionEvent, Long> {

}
