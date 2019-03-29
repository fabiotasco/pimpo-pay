
package pay.pimpo.transaction.rules;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.clients.AccountClient;
import pay.pimpo.commons.converters.PaginationDtoConverter;
import pay.pimpo.commons.dto.PaginationDto;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.transaction.converters.StatementTransactionDtoConverter;
import pay.pimpo.transaction.dto.StatementDto;
import pay.pimpo.transaction.dto.StatementTransactionDto;
import pay.pimpo.transaction.repositories.TransactionRepository;

@Component
public class StatementRules {

	@Autowired
	private AccountClient accountClient;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private StatementTransactionDtoConverter statementTransactionDtoConverter;

	@Autowired
	private PaginationDtoConverter paginationDtoConverter;

	public Response<StatementDto> process(final Long userId, final PageRequest pageRequest) {
		final Response<Account> response = accountClient.findByUserId(userId);
		if (!response.isSuccess()) {
			return new Response<>(response.getErrors());
		}
		final Account account = response.getContent();
		// TODO: Retornar no extrato as transações feitas pelo usuário e as que ele também é destinatário
		final Page<Transaction> page
			= transactionRepository.findAllByHolderAccountIdOrderByDateDesc(account.getId(), pageRequest);
		final List<StatementTransactionDto> statementTransactionDtoList = convertTransactions(page);
		final PaginationDto paginationDto = paginationDtoConverter.convert(page);

		final StatementDto statementDto
			= new StatementDto(account.getBalance(), statementTransactionDtoList, paginationDto);

		return new Response<>(statementDto);
	}

	private List<StatementTransactionDto> convertTransactions(final Page<Transaction> page) {
		return page.getContent()
			.stream()
			.map(transaction -> statementTransactionDtoConverter.convert(transaction))
			.collect(Collectors.toList());
	}

}
