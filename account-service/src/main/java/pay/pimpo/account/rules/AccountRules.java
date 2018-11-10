
package pay.pimpo.account.rules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.account.repositories.AccountRepository;
import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.clients.UserClient;
import pay.pimpo.commons.dto.CreateAccountDto;
import pay.pimpo.commons.dto.DocumentDto;
import pay.pimpo.commons.dto.FetchAccountsDto;
import pay.pimpo.commons.dto.FetchAccountsResponseDto;
import pay.pimpo.commons.dto.TransferBalanceDto;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.AccountNumber;
import pay.pimpo.commons.entities.AccountNumberStatus;
import pay.pimpo.commons.entities.AccountPlan;
import pay.pimpo.commons.entities.AccountStatus;
import pay.pimpo.commons.entities.PlanType;
import pay.pimpo.commons.exceptions.AccountNotActiveException;
import pay.pimpo.commons.exceptions.AccountNotEnrolledOnPlanException;
import pay.pimpo.commons.exceptions.AccountNotFoundException;
import pay.pimpo.commons.exceptions.ActiveAccountNumberNotFound;
import pay.pimpo.commons.security.HashGenerator;
import pay.pimpo.commons.validators.PhoneValidator;

@Component
public class AccountRules {

	@Autowired
	private AccountContractRules accountContractRules;

	@Autowired
	private AccountPlanRules accountPlanRules;

	@Autowired
	private AccountNumberRules accountNumberRules;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PhoneValidator phoneValidator;

	@Autowired
	private UserClient userClient;

	/**
	 * Cria uma nova conta.
	 *
	 * @param createAccountDto Dados para a nova conta.
	 * @return A conta criada.
	 * @throws Exception Caso não seja possível criar a conta.
	 */
	public Account createAccount(final CreateAccountDto createAccountDto) throws Exception {
		accountNumberRules.checkAccountNumberActiveUniqueness(createAccountDto.getPhoneDto().getNumber());

		final String hash = generateHash(createAccountDto.getDocumentDto());

		final Account account = new Account(hash, 0.0, AccountStatus.ACTIVE, createAccountDto.getUserId());

		account.getContracts().add(accountContractRules.createContract(createAccountDto.getDocumentDto(), account));

		// XXX: Será adicionado somente o tipo pré-pago, por enquanto...
		account.getPlans().add(accountPlanRules.createPlan(account));

		account.getNumbers()
			.add(accountNumberRules
				.createAccountNumber(createAccountDto.getPhoneDto(), AccountNumberStatus.ACTIVE, account));

		accountRepository.save(account);

		return account;
	}

	private String generateHash(final DocumentDto documentDto) {
		final String text = "#" + documentDto.getValue();
		final String salt = "@" + documentDto.getType();

		return HashGenerator.generate(text, salt);
	}

	public Account findActiveAccountByUserId(final Long userId) throws Exception {
		final Account account = accountRepository.findByUserId(userId);
		if (account == null) {
			throw new AccountNotFoundException();
		}
		if (account.getStatus() != AccountStatus.ACTIVE) {
			throw new AccountNotActiveException(account.getHash());
		}
		return account;
	}

	public FetchAccountsResponseDto fetchAccounts(final FetchAccountsDto fetchAccountsDto) throws Exception {
		final Account holderAccount = fetchHolderAccount(fetchAccountsDto);
		final Account destinationAccount = fetchDestinationAccount(fetchAccountsDto);

		checkAccountEnrollmentOnPlan(holderAccount, fetchAccountsDto.getPlanType());
		checkAccountEnrollmentOnPlan(destinationAccount, fetchAccountsDto.getPlanType());

		// TODO: HolderAccount tem que ser diferente de DestinationAccount - Validar!

		return new FetchAccountsResponseDto(holderAccount, destinationAccount);
	}

	private Account fetchHolderAccount(final FetchAccountsDto fetchAccountsDto) throws Exception {
		phoneValidator.validateNumber(fetchAccountsDto.getHolderAccountDto().getNumber());

		final Account holderAccount = findActiveAccountByUserId(fetchAccountsDto.getUserId());
		final AccountNumber holderAccountNumber = accountNumberRules.findActiveNumber(holderAccount.getNumbers());
		if (!holderAccountNumber.getNumber().equals(fetchAccountsDto.getHolderAccountDto().getNumber())) {
			throw new ActiveAccountNumberNotFound(fetchAccountsDto.getHolderAccountDto().getNumber());
		}
		return holderAccount;
	}

	private Account fetchDestinationAccount(final FetchAccountsDto fetchAccountsDto) throws Exception {
		Account destinationAccount = null;
		if (fetchAccountsDto.getDestinationAccountDto().getHash() != null) {
			// Busca pelo Hash da conta
			destinationAccount = accountRepository.findByHash(fetchAccountsDto.getDestinationAccountDto().getHash());

		} else if (fetchAccountsDto.getDestinationAccountDto().getDocument() != null) {
			// Busca pelo documento do cliente.
			final Response<Long> response
				= userClient.findByUsername(fetchAccountsDto.getDestinationAccountDto().getDocument());
			if (response.isSuccess()) {
				destinationAccount = findActiveAccountByUserId(response.getContent());
			}
		}
		if (destinationAccount == null) {
			throw new AccountNotFoundException();
		}
		return destinationAccount;
	}

	private void checkAccountEnrollmentOnPlan(final Account account, final PlanType planType)
		throws AccountNotEnrolledOnPlanException {

		final Optional<AccountPlan> optional
			= account.getPlans().parallelStream().filter(plan -> plan.getPlanType() == planType).findAny();
		if (!optional.isPresent()) {
			throw new AccountNotEnrolledOnPlanException(account, planType);
		}
	}

	public Void transferBalance(final TransferBalanceDto transferBalanceDto) throws Exception {
		// Debita a conta do portador
		sumAmount(transferBalanceDto.getHolderAccountId(), -transferBalanceDto.getAmount());
		// Credita a conta destino.
		sumAmount(transferBalanceDto.getDestinationAccountId(), +transferBalanceDto.getAmount());

		return null;
	}

	private void sumAmount(final Long id, final Double amount) throws Exception {
		final Account holderAccount = findActiveAccountById(id);
		holderAccount.setBalance(holderAccount.getBalance().doubleValue() + amount.doubleValue());

		accountRepository.save(holderAccount);
	}

	private Account findActiveAccountById(final Long id) throws Exception {
		final Optional<Account> optional = accountRepository.findById(id);
		if (!optional.isPresent()) {
			throw new AccountNotFoundException();
		}
		final Account account = optional.get();
		if (account.getStatus() != AccountStatus.ACTIVE) {
			throw new AccountNotActiveException(account.getHash());
		}
		return optional.get();
	}

}
