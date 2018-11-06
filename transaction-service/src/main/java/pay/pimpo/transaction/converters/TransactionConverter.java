
package pay.pimpo.transaction.converters;

import org.springframework.stereotype.Component;

import pay.pimpo.commons.entities.Account;
import pay.pimpo.transaction.dto.TransactionDto;
import pay.pimpo.transaction.entities.Transaction;

@Component
public class TransactionConverter {

	public Transaction
		convert(final TransactionDto transactionDto, final Account holderAccount, final Account destinationAccount) {
		return new Transaction(
			transactionDto.getAmount(),
			transactionDto.getInstallments(),
			transactionDto.getDate(),
			transactionDto.getCurrency(),
			transactionDto.getPlan(),
			holderAccount.getId(),
			destinationAccount.getId());
	}

}
