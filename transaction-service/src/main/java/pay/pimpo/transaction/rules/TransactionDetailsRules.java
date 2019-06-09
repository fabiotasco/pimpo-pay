
package pay.pimpo.transaction.rules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.clients.AccountClient;
import pay.pimpo.commons.clients.UserClient;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionType;
import pay.pimpo.commons.exceptions.TransactionNotFoundException;
import pay.pimpo.transaction.converters.StatementTransactionDtoConverter;
import pay.pimpo.transaction.converters.TransactionDetailsAccountDtoConverter;
import pay.pimpo.transaction.dto.StatementTransactionDto;
import pay.pimpo.transaction.dto.TransactionDetailsAccountDto;
import pay.pimpo.transaction.dto.TransactionDetailsDto;
import pay.pimpo.transaction.repositories.TransactionRepository;

@Component
public class TransactionDetailsRules {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private StatementTransactionDtoConverter statementTransactionDtoConverter;

	@Autowired
	private AccountClient accountClient;

	@Autowired
	private UserClient userClient;

	@Autowired
	private TransactionDetailsAccountDtoConverter transactionDetailsAccountDtoConverter;

	public Response<TransactionDetailsDto> process(final Long transactionId, final Long userId)
		throws TransactionNotFoundException {
		final Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
		if (!transactionOptional.isPresent()) {
			throw new TransactionNotFoundException();
		}
		final Transaction transaction = transactionOptional.get();

		final Response<Account> response = accountClient.findByUserId(userId);
		if (!response.isSuccess()) {
			return new Response<>(response.getErrors());
		}
		final Account account = response.getContent();

		final StatementTransactionDto statementTransactionDto
			= statementTransactionDtoConverter.convert(transaction, account.getId());

		final Response<TransactionDetailsAccountDto> holderAccountDtoResponse
			= convertAccount(transaction.getHolderAccountId());
		if (!holderAccountDtoResponse.isSuccess()) {
			return new Response<>(holderAccountDtoResponse.getErrors());
		}

		// Depósito não tem destinário, pois cai na própria conta!
		Response<TransactionDetailsAccountDto> destinationAccountDtoResponse = null;
		if (transaction.getType() != TransactionType.DEPOSIT) {
			destinationAccountDtoResponse = convertAccount(transaction.getDestinationAccountId());
			if (!destinationAccountDtoResponse.isSuccess()) {
				return new Response<>(destinationAccountDtoResponse.getErrors());
			}
		}

		return new Response<>(
			new TransactionDetailsDto(
				statementTransactionDto,
				holderAccountDtoResponse.getContent(),
				destinationAccountDtoResponse != null ? destinationAccountDtoResponse.getContent() : null));
	}

	private Response<TransactionDetailsAccountDto> convertAccount(final Long accountId) {
		final Response<Account> accountResponse = accountClient.findById(accountId);
		if (!accountResponse.isSuccess()) {
			return new Response<>(accountResponse.getErrors());
		}
		final Response<String> usernameResponse = userClient.findUsernameById(accountResponse.getContent().getUserId());
		if (!usernameResponse.isSuccess()) {
			return new Response<>(usernameResponse.getErrors());
		}

		return new Response<>(
			transactionDetailsAccountDtoConverter.convert(usernameResponse.getContent(), accountResponse.getContent()));

	}

}
