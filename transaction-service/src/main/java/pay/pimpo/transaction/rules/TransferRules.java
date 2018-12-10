
package pay.pimpo.transaction.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;

import pay.pimpo.commons.api.Error;
import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.builders.TransactionEventBuilder;
import pay.pimpo.commons.clients.AccountClient;
import pay.pimpo.commons.dto.FetchHolderAccountDto;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionStatus;
import pay.pimpo.commons.entities.TransactionType;
import pay.pimpo.commons.exceptions.InsufficientFundsException;
import pay.pimpo.commons.exceptions.InvalidTransactionMerchantDtoDataException;
import pay.pimpo.commons.exceptions.TransactionBetweenSameAccountNotAllowedException;
import pay.pimpo.commons.utils.TransactionUtils;
import pay.pimpo.configurations.HazelcastConfiguration;
import pay.pimpo.transaction.converters.TransactionConverter;
import pay.pimpo.transaction.dto.TransactionResponseDto;
import pay.pimpo.transaction.dto.TransferDto;
import pay.pimpo.transaction.repositories.TransactionRepository;

@Component
public class TransferRules {

	@Autowired
	private AccountClient accountClient;

	@Autowired
	private TransactionConverter transactionConverter;

	@Autowired
	private TransactionCalculator transactionCalculator;

	@Autowired
	private HazelcastInstance hazelcastInstance;

	@Autowired
	private TransactionRepository transactionRepository;

	public Response<TransactionResponseDto> process(final TransferDto transferDto, final Long userId) throws Exception {
		if (!TransactionUtils.validateDestinationAccount(transferDto.getDestinationAccountDto())) {
			throw new InvalidTransactionMerchantDtoDataException();
		}

		// Verifica a conta do portador
		final Response<Account> fetchHolderAccountResponse
			= accountClient.fetchHolderAccount(new FetchHolderAccountDto(transferDto.getHolderAccountDto(), userId));
		if (!fetchHolderAccountResponse.isSuccess()) {
			return new Response<>(fetchHolderAccountResponse.getErrors());
		}
		// Verifica a conta do destinatário
		final Response<Account> fetchDestinationAccountResponse
			= accountClient.fetchDestinationAccount(transferDto.getDestinationAccountDto());
		if (!fetchDestinationAccountResponse.isSuccess()) {
			return new Response<>(fetchDestinationAccountResponse.getErrors());
		}
		final Account holderAccount = fetchHolderAccountResponse.getContent();
		final Account destinationAccount = fetchDestinationAccountResponse.getContent();

		// As contas não podem ser iguais em uma compra!
		if (holderAccount.equals(destinationAccount)) {
			throw new TransactionBetweenSameAccountNotAllowedException(holderAccount);
		}

		final Transaction transaction
			= transactionConverter.convert(transferDto, TransactionType.TRANSFER, holderAccount, destinationAccount);
		try {
			// Verifica o saldo
			transactionCalculator.checkForFunds(holderAccount, transferDto.getAmount(), transaction.getPlanType());

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

	private void saveTransaction(final Transaction transaction, final TransactionStatus status, final Error error) {
		final TransactionEventBuilder builder
			= new TransactionEventBuilder().setStatus(status).setTransaction(transaction);
		if (error != null) {
			builder.setReasonCode(error);
		}
		transaction.getEvents().add(builder.build());

		transactionRepository.save(transaction);
	}

}
