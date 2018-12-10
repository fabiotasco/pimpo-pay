
package pay.pimpo.clearing.rules;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionEvent;
import pay.pimpo.commons.entities.TransactionStatus;
import pay.pimpo.commons.entities.TransactionType;
import pay.pimpo.commons.utils.TransactionUtils;

@Component
public class ClearingRules {

	@Autowired
	private PurchaseClearingStrategy purchaseClearingStrategy;

	@Autowired
	private DepositClearingStrategy depositClearingStrategy;

	@Autowired
	private TransferClearingStrategy transferClearingStrategy;

	private final Map<TransactionType, ClearingStrategy> clearingStrategyMap;

	public ClearingRules() {
		clearingStrategyMap = new HashMap<>(TransactionType.values().length);
	}

	@PostConstruct
	public void init() {
		clearingStrategyMap.put(TransactionType.PURCHASE, purchaseClearingStrategy);
		clearingStrategyMap.put(TransactionType.DEPOSIT, depositClearingStrategy);
		clearingStrategyMap.put(TransactionType.TRANSFER, transferClearingStrategy);
	}

	public void process(final Transaction transaction) {
		final ClearingStrategy clearingStrategy = clearingStrategyMap.entrySet()
			.parallelStream()
			.filter(entry -> entry.getKey() == transaction.getType())
			.findAny()
			.orElseThrow(() -> new UnsupportedOperationException(
				"Clearing not supported for transaction type: " + transaction.getType()))
			.getValue();

		if (isCancel(transaction)) {
			clearingStrategy.cancel(transaction);
		} else {
			clearingStrategy.clear(transaction);
		}
	}

	private boolean isCancel(final Transaction transaction) {
		final TransactionEvent event = TransactionUtils.getMostRecentEvent(transaction);
		if (event == null) {
			return false;
		}
		return event.getStatus() == TransactionStatus.CANCELLED;
	}

}
