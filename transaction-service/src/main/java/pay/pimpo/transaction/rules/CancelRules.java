
package pay.pimpo.transaction.rules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.builders.TransactionEventBuilder;
import pay.pimpo.commons.clients.AccountClient;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionEvent;
import pay.pimpo.commons.entities.TransactionStatus;
import pay.pimpo.commons.exceptions.TransactionAlreadyCancelledException;
import pay.pimpo.commons.exceptions.TransactionNotCancellableByUserException;
import pay.pimpo.commons.exceptions.TransactionNotCancellableException;
import pay.pimpo.commons.exceptions.TransactionNotFoundException;
import pay.pimpo.commons.utils.TransactionUtils;
import pay.pimpo.configurations.HazelcastConfiguration;
import pay.pimpo.transaction.dto.TransactionResponseDto;
import pay.pimpo.transaction.repositories.TransactionEventRepository;
import pay.pimpo.transaction.repositories.TransactionRepository;

@Component
public class CancelRules {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionEventRepository transactionEventRepository;

	@Autowired
	private HazelcastInstance hazelcastInstance;

	@Autowired
	private AccountClient accountClient;

	public Response<TransactionResponseDto> process(final Long transactionId, final Long userId) throws Exception {
		final Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
		if (!transactionOptional.isPresent()) {
			throw new TransactionNotFoundException();
		}
		final Transaction transaction = transactionOptional.get();

		// Impedir que transações canceladas sejam canceladas novamente
		final TransactionEvent mostRecentEvent = TransactionUtils.getMostRecentEvent(transaction);
		if (mostRecentEvent != null && mostRecentEvent.getStatus() == TransactionStatus.CANCELLED) {
			throw new TransactionAlreadyCancelledException(transaction);
		}

		// Transação deve estar autorizada ou liquidada para ser cancelada
		if (!TransactionUtils.isCancellable(transaction)) {
			throw new TransactionNotCancellableException(transaction);
		}

		// Pra cada tipo de transação, existe uma regra que define quem pode cancelá-la!
		if (!isCancellableByUser(transaction, userId)) {
			throw new TransactionNotCancellableByUserException(transaction, userId);
		}

		// TODO: Impedir que o usuário cancele um depósito que deixe o saldo dele negativo!

		final TransactionEvent event
			= new TransactionEventBuilder().setStatus(TransactionStatus.CANCELLED).setTransaction(transaction).build();

		transaction.getEvents().add(event);
		transactionEventRepository.save(event);

		hazelcastInstance.getQueue(HazelcastConfiguration.CLEARING_QUEUE_NAME).add(transaction);

		return new Response<>(new TransactionResponseDto(event.getStatus()));
	}

	private boolean isCancellableByUser(final Transaction transaction, final Long userId) {
		switch (transaction.getType()) {
			case PURCHASE:
				// Somente a loja (destination) pode cancelar!
				return isEventTriggeredByTransactionOwner(transaction.getDestinationAccountId(), userId);
			case DEPOSIT:
				// Somente o portador (holder) pode cancelar!
				return isEventTriggeredByTransactionOwner(transaction.getHolderAccountId(), userId);
			default:
				throw new UnsupportedOperationException(
					"Transaction not supported to check if is cancellable by user: " + transaction.getType());
		}
	}

	/**
	 * Verifica se o evento de cancelamento foi solicitado pelo dono da transação.
	 *
	 * @param ownerAccountId Id da conta que representa o dono da transação.
	 * @param userId Id do usuário que disparou o evento.
	 * @return Verdadeiro, se foi cancelado pela conta dona da transação. Falso, caso contrário.
	 */
	private boolean isEventTriggeredByTransactionOwner(final Long ownerAccountId, final Long userId) {
		final Response<Account> findAccountByUserIdResponse = accountClient.findByUserId(userId);
		if (!findAccountByUserIdResponse.isSuccess()) {
			return false;
		}
		return ownerAccountId == findAccountByUserIdResponse.getContent().getId();
	}

}
