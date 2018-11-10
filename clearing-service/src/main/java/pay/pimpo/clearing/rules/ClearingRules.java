
package pay.pimpo.clearing.rules;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.commons.entities.TransactionEvent;
import pay.pimpo.commons.entities.TransactionType;

@Component
public class ClearingRules {

	@Autowired
	private PurchaseClearingStrategy purchaseClearingStrategy;

	private final Map<TransactionType, ClearingStrategy> clearingStrategyMap;

	public ClearingRules() {
		clearingStrategyMap = new HashMap<>(TransactionType.values().length);
	}

	@PostConstruct
	public void init() {
		clearingStrategyMap.put(TransactionType.PURCHASE, purchaseClearingStrategy);
	}

	public void process(final TransactionEvent transactionEvent) {
		final ClearingStrategy clearingStrategy = clearingStrategyMap.entrySet()
			.parallelStream()
			.filter(entry -> entry.getKey() == transactionEvent.getTransaction().getType())
			.findAny()
			.orElseThrow(() -> new UnsupportedOperationException(
				"Clearing not supported for transaction type: " + transactionEvent.getTransaction().getType()))
			.getValue();

		clearingStrategy.clearTransaction(transactionEvent);
	}

}
