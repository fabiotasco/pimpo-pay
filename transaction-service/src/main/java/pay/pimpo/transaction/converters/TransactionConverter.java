
package pay.pimpo.transaction.converters;

import org.springframework.stereotype.Component;

import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionType;
import pay.pimpo.transaction.dto.TransactionDto;

@Component
public class TransactionConverter {

	public Transaction convert(
		final TransactionDto transactionDto,
		final TransactionType transactionType,
		final Account holderAccount,
		final Account destinationAccount) {

		return new Transaction(
			transactionDto.getDate(),
			transactionDto.getAmount(),
			transactionDto.getCurrency(),
			transactionDto.getInstallments(),
			transactionType,
			transactionDto.getPlan(),
			holderAccount.getId(),
			destinationAccount.getId());
	}

}
