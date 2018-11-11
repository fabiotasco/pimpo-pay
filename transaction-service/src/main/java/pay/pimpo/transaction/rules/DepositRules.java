
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
import pay.pimpo.commons.entities.PlanType;
import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionStatus;
import pay.pimpo.commons.entities.TransactionType;
import pay.pimpo.commons.exceptions.AccountNotEnrolledOnPlanException;
import pay.pimpo.configurations.HazelcastConfiguration;
import pay.pimpo.transaction.converters.TransactionConverter;
import pay.pimpo.transaction.dto.DepositDto;
import pay.pimpo.transaction.dto.TransactionResponseDto;
import pay.pimpo.transaction.repositories.TransactionRepository;

@Component
public class DepositRules {

	@Autowired
	private AccountClient accountClient;

	@Autowired
	private TransactionConverter transactionConverter;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private HazelcastInstance hazelcastInstance;

	public Response<TransactionResponseDto> process(final DepositDto depositDto, final Long userId)
		throws AccountNotEnrolledOnPlanException {

		final Response<Account> response
			= accountClient.fetchHolderAccount(new FetchHolderAccountDto(depositDto.getHodlerAccountDto(), userId));
		if (!response.isSuccess()) {
			return new Response<>(response.getErrors());
		}

		final Account holderAccount = response.getContent();

		final Transaction transaction
			= transactionConverter.convert(depositDto, TransactionType.DEPOSIT, holderAccount);

		// Conta deve ter o plano Pré-pago habilitado!
		if (!isEnrolledOnPrepaidPlan(holderAccount)) {
			final AccountNotEnrolledOnPlanException exception
				= new AccountNotEnrolledOnPlanException(holderAccount, PlanType.PREPAID);
			saveTransaction(transaction, TransactionStatus.DENIED, exception.getError());

			throw exception;
		}
		saveTransaction(transaction, TransactionStatus.AUTHORIZED, null);

		hazelcastInstance.getQueue(HazelcastConfiguration.CLEARING_QUEUE_NAME).add(transaction);

		return new Response<>(new TransactionResponseDto(transaction.getEvents().get(0).getStatus()));
	}

	private boolean isEnrolledOnPrepaidPlan(final Account account) {
		return account.getPlans()
			.parallelStream()
			.filter(plan -> plan.getPlanType() == PlanType.PREPAID)
			.findAny()
			.isPresent();
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
