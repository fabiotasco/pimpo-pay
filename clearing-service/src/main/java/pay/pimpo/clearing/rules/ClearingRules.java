
package pay.pimpo.clearing.rules;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionType;

@Component
public class ClearingRules {

	@Autowired
	private PurchaseClearingStrategy purchaseClearingStrategy;

	@Autowired
	private DepositClearingStrategy depositClearingStrategy;

	private final Map<TransactionType, ClearingStrategy> clearingStrategyMap;

	public ClearingRules() {
		clearingStrategyMap = new HashMap<>(TransactionType.values().length);
	}

	@PostConstruct
	public void init() {
		clearingStrategyMap.put(TransactionType.PURCHASE, purchaseClearingStrategy);
		clearingStrategyMap.put(TransactionType.DEPOSIT, depositClearingStrategy);
	}

	public void process(final Transaction transaction) {
		final ClearingStrategy clearingStrategy = clearingStrategyMap.entrySet()
			.parallelStream()
			.filter(entry -> entry.getKey() == transaction.getType())
			.findAny()
			.orElseThrow(() -> new UnsupportedOperationException(
				"Clearing not supported for transaction type: " + transaction.getType()))
			.getValue();

		clearingStrategy.run(transaction);
	}

}
