
package pay.pimpo.transaction.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.commons.entities.TransactionEvent;
import pay.pimpo.commons.exceptions.TransactionNotFoundException;
import pay.pimpo.transaction.repositories.TransactionEventRepository;

@Component
public class TransactionRules {

	@Autowired
	private TransactionEventRepository transactionEventRepository;

	public Void addEvent(final TransactionEvent transactionEvent) throws TransactionNotFoundException {
		if (transactionEvent.getTransaction() == null) {
			throw new TransactionNotFoundException();
		}
		transactionEventRepository.save(transactionEvent);
		return null;
	}

}
