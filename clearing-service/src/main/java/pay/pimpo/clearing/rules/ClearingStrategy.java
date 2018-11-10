
package pay.pimpo.clearing.rules;

import pay.pimpo.commons.entities.TransactionEvent;

public interface ClearingStrategy {

	void clearTransaction(TransactionEvent transactionEvent);

}
