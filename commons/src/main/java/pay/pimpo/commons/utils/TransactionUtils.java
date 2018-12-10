
package pay.pimpo.commons.utils;

import java.util.List;

import pay.pimpo.commons.dto.DestinationAccountDto;
import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionEvent;
import pay.pimpo.commons.entities.TransactionStatus;
import pay.pimpo.commons.exceptions.InvalidPhoneException;
import pay.pimpo.commons.validators.PhoneValidator;

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

	public static boolean validateDestinationAccount(final DestinationAccountDto destinationAccountDto)
		throws InvalidPhoneException {

		if (isNotBlank(destinationAccountDto.getHash())) {
			return true;
		}

		final String number = destinationAccountDto.getNumber();
		if (isNotBlank(number) && PhoneValidator.validateNumber(number)) {
			return true;
		}
		return false;
	}

	public static boolean isNotBlank(final String field) {
		return field != null && !field.isEmpty();
	}

}
