
package pay.pimpo.transaction.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.clients.AccountClient;
import pay.pimpo.commons.dto.FetchAccountsDto;
import pay.pimpo.commons.dto.FetchAccountsResponseDto;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.exceptions.InsufficientFundsException;
import pay.pimpo.commons.exceptions.InvalidTransactionMerchantDtoDataException;
import pay.pimpo.transaction.builders.TransactionEventBuilder;
import pay.pimpo.transaction.converters.TransactionConverter;
import pay.pimpo.transaction.dto.TransactionDto;
import pay.pimpo.transaction.dto.TransactionResponseDto;
import pay.pimpo.transaction.entities.Transaction;
import pay.pimpo.transaction.entities.TransactionEvent;
import pay.pimpo.transaction.entities.TransactionStatus;
import pay.pimpo.transaction.entities.TransactionType;
import pay.pimpo.transaction.repositories.TransactionEventRepository;
import pay.pimpo.transaction.repositories.TransactionRepository;

@Component
public class PurchaseRules {

	@Autowired
	private AccountClient accountClient;

	@Autowired
	private TransactionCalculator transactionCalculator;

	@Autowired
	private TransactionConverter transactionConverter;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionEventRepository transactionEventRepository;

	public Response<TransactionResponseDto> process(final TransactionDto transactionDto, final Long userId)
		throws Exception {
		if (!validateFields(transactionDto)) {
			throw new InvalidTransactionMerchantDtoDataException();
		}

		// Verifica as contas
		final Response<FetchAccountsResponseDto> fetchAccountsResponse
			= accountClient.fetchAccounts(new FetchAccountsDto(
				transactionDto.getPlan(),
				transactionDto.getDestinationAccountDto(),
				transactionDto.getHodlerAccountDto(),
				userId));
		if (!fetchAccountsResponse.isSuccess()) {
			return new Response<>(fetchAccountsResponse.getErrors());
		}

		// Verifica o saldo
		final Account holderAccount = fetchAccountsResponse.getContent().getHolderAccount();
		TransactionEvent transactionEvent;
		try {
			transactionCalculator.checkForFunds(transactionDto, holderAccount);

			// Salva a transação como aprovada
			transactionEvent = saveTransactionAndEvent(transactionDto,
				holderAccount,
				fetchAccountsResponse.getContent().getDestinationAccount(),
				TransactionStatus.AUTHORIZED);

		} catch (final InsufficientFundsException e) {
			// Salvar a transação como negada!
			transactionEvent = saveTransactionAndEvent(transactionDto,
				holderAccount,
				fetchAccountsResponse.getContent().getDestinationAccount(),
				TransactionStatus.DENIED);

			throw e;
		}
		// TODO: Atualizar o balanço quando for prepaid!
		// TODO: Enfileirar requisição para o sistema de clearing!
		return new Response<>(new TransactionResponseDto(transactionEvent.getStatus()));
	}

	private boolean validateFields(final TransactionDto transactionDto)
		throws InvalidTransactionMerchantDtoDataException {

		return isNotBlank(transactionDto.getDestinationAccountDto().getDocument())
			|| isNotBlank(transactionDto.getDestinationAccountDto().getHash());
	}

	private boolean isNotBlank(final String field) {
		return field != null && !field.isEmpty();
	}

	private TransactionEvent saveTransactionAndEvent(
		final TransactionDto transactionDto,
		final Account holderAccount,
		final Account destinationAccount,
		final TransactionStatus status) {

		final Transaction transaction = transactionConverter.convert(transactionDto, holderAccount, destinationAccount);
		transactionRepository.save(transaction);

		final TransactionEvent event = new TransactionEventBuilder().setStatus(status)
			.setType(TransactionType.PURCHASE)
			.setTransaction(transaction)
			.build();
		transactionEventRepository.save(event);

		return event;
	}

}
