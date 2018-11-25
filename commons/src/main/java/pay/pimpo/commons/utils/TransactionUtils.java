
package pay.pimpo.commons.utils;

import java.util.List;

import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionEvent;
import pay.pimpo.commons.entities.TransactionStatus;

public class TransactionUtils {

	private TransactionUtils() {}

	public static TransactionEvent getMostRecentEvent(final Transaction transaction) {
		final List<TransactionEvent> events = transaction.getEvents();
		if (events.isEmpty()) {
			return null;
		}
		return events.get(events.size() - 1);
	}

	public static boolean isCancellable(final Transaction transaction) {
		final TransactionEvent event = getMostRecentEvent(transaction);
		if (event == null) {
			return false;
		}
		return event.getStatus() == TransactionStatus.AUTHORIZED || event.getStatus() == TransactionStatus.SETTLED;
	}

}
