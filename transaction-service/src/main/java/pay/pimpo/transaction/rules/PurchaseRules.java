
package pay.pimpo.transaction.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;

import pay.pimpo.commons.api.Error;
import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.builders.TransactionEventBuilder;
import pay.pimpo.commons.clients.AccountClient;
import pay.pimpo.commons.dto.FetchAccountsDto;
import pay.pimpo.commons.dto.FetchAccountsResponseDto;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionStatus;
import pay.pimpo.commons.entities.TransactionType;
import pay.pimpo.commons.exceptions.InsufficientFundsException;
import pay.pimpo.commons.exceptions.InvalidTransactionMerchantDtoDataException;
import pay.pimpo.configurations.HazelcastConfiguration;
import pay.pimpo.transaction.converters.TransactionConverter;
import pay.pimpo.transaction.dto.PurchaseDto;
import pay.pimpo.transaction.dto.TransactionResponseDto;
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
	private HazelcastInstance hazelcastInstance;

	public Response<TransactionResponseDto> process(final PurchaseDto purchaseDto, final Long userId) throws Exception {
		if (!validateFields(purchaseDto)) {
			throw new InvalidTransactionMerchantDtoDataException();
		}

		// Verifica as contas
		final Response<FetchAccountsResponseDto> fetchAccountsResponse
			= accountClient.fetchAccounts(new FetchAccountsDto(
				purchaseDto.getPlan(),
				purchaseDto.getDestinationAccountDto(),
				purchaseDto.getHodlerAccountDto(),
				userId));
		if (!fetchAccountsResponse.isSuccess()) {
			return new Response<>(fetchAccountsResponse.getErrors());
		}

		final Account holderAccount = fetchAccountsResponse.getContent().getHolderAccount();

		final Transaction transaction = transactionConverter.convert(purchaseDto,
			TransactionType.PURCHASE,
			holderAccount,
			fetchAccountsResponse.getContent().getDestinationAccount());
		try {
			// Verifica o saldo
			transactionCalculator.checkForFunds(holderAccount, purchaseDto.getAmount(), purchaseDto.getPlan());

			// Salva a transação como aprovada
			saveTransaction(transaction, TransactionStatus.AUTHORIZED, null);

			// Enfileira!
			hazelcastInstance.getQueue(HazelcastConfiguration.CLEARING_QUEUE_NAME).add(transaction);

			return new Response<>(new TransactionResponseDto(transaction.getEvents().get(0).getStatus()));

		} catch (final InsufficientFundsException e) {
			// Salva a transação como negada!
			saveTransaction(transaction, TransactionStatus.DENIED, e.getError());
			throw e;
		}
	}

	private boolean validateFields(final PurchaseDto transactionDto) throws InvalidTransactionMerchantDtoDataException {
		return isNotBlank(transactionDto.getDestinationAccountDto().getDocument())
			|| isNotBlank(transactionDto.getDestinationAccountDto().getHash());
	}

	private boolean isNotBlank(final String field) {
		return field != null && !field.isEmpty();
	}

	private void saveTransaction(final Transaction transaction, final TransactionStatus status, final Error error) {
		final TransactionEventBuilder transactionEventBuilder
			= new TransactionEventBuilder().setStatus(status).setTransaction(transaction);
		if (error != null) {
			transactionEventBuilder.setReasonCode(error);
		}
		transaction.getEvents().add(transactionEventBuilder.build());

		transactionRepository.save(transaction);
	}

}
