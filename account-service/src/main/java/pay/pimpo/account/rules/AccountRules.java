
package pay.pimpo.account.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.account.repositories.AccountRepository;
import pay.pimpo.commons.dto.CreateAccountDto;
import pay.pimpo.commons.dto.DocumentDto;
import pay.pimpo.commons.dto.SupportedDocumentType;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.AccountStatus;
import pay.pimpo.commons.entities.AccountType;
import pay.pimpo.commons.security.HashGenerator;

@Component
public class AccountRules {

	@Autowired
	private AccountTypeRules accountTypeRules;

	@Autowired
	private AccountStatusRules accountStatusRules;

	@Autowired
	private AccountPlanRules accountPlanRules;

	@Autowired
	private AccountNumberRules accountNumberRules;

	@Autowired
	private AccountRepository accountRepository;

	/**
	 * Cria uma nova conta.
	 *
	 * @param createAccountDto Dados para a nova conta.
	 * @return A conta criada.
	 * @throws Exception Caso não seja possível criar a conta.
	 */
	public Account createAccount(final CreateAccountDto createAccountDto) throws Exception {
		final AccountType type = accountTypeRules.findAccountType(
			SupportedDocumentType.getAssociatedAccountType(createAccountDto.getDocumentDto().getType()));

		final AccountStatus status = accountStatusRules.findAccountStatus("Active");

		final String hash = generateHash(createAccountDto.getDocumentDto());

		final Account account = new Account(hash, type, status, createAccountDto.getUserId());

		accountPlanRules.listAccountPlan().forEach(plan -> account.getPlans().add(plan));

		// TODO: O mesmo número ativo não pode ser criado para contas do mesmo tipo
		account.getNumbers()
			.add(accountNumberRules.createAccountNumber(createAccountDto.getPhoneDto(), "Active", account));

		accountRepository.save(account);

		return account;
	}

	private String generateHash(final DocumentDto documentDto) {
		final String text = "#" + documentDto.getValue();
		final String salt = "@" + documentDto.getType();

		return HashGenerator.generate(text, salt);
	}

}
