
package pay.pimpo.transaction.converters;

import org.springframework.stereotype.Component;

import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.PlanType;
import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionType;
import pay.pimpo.transaction.dto.DepositDto;
import pay.pimpo.transaction.dto.PurchaseDto;
import pay.pimpo.transaction.dto.TransferDto;

@Component
public class TransactionConverter {

	public Transaction convert(
		final PurchaseDto transactionDto,
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

	public Transaction
		convert(final DepositDto creditDto, final TransactionType transactionType, final Account holderAccount) {
		return new Transaction(
			creditDto.getDate(),
			creditDto.getAmount(),
			creditDto.getCurrency(),
			null,
			transactionType,
			PlanType.PREPAID,
			holderAccount.getId(),
			null);
	}

	public Transaction convert(
		final TransferDto transferDto,
		final TransactionType transactionType,
		final Account holderAccount,
		final Account destinationAccount) {

		return new Transaction(
			transferDto.getDate(),
			transferDto.getAmount(),
			transferDto.getCurrency(),
			null,
			transactionType,
			PlanType.PREPAID,
			holderAccount.getId(),
			destinationAccount.getId());
	}

}
