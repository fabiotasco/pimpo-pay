
package pay.pimpo.account.rules;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import pay.pimpo.account.helpers.AccountHelper;
import pay.pimpo.account.repositories.AccountRepository;
import pay.pimpo.commons.dto.PhoneDto;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.exceptions.ActiveAccountNumberNotUniqueException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AccountNumberRulesTest {

	@Autowired
	private AccountHelper accountHelper;

	@Autowired
	private AccountNumberRules accountNumberRules;

	@Autowired
	private AccountRepository accountRepository;

	@Before
	public void before() {
		accountHelper.insertDomainValues();
	}

	@Test(expected = ActiveAccountNumberNotUniqueException.class)
	public void testCheckAccountNumberUniqueness() throws Exception {
		final String number = "+5511999990000";

		final Account account = createTestAccount();

		final PhoneDto phoneDto = new PhoneDto(
			number,
			accountHelper.findOperatorNetwork().getName(),
			accountHelper.findActiveAccountNumberStatus().getName());

		account.getNumbers()
			.add(accountNumberRules
				.createAccountNumber(phoneDto, accountHelper.findActiveAccountStatus().getName(), account));

		accountRepository.save(account);

		accountNumberRules.checkAccountNumberActiveUniqueness(number);
	}

	private Account createTestAccount() throws Exception {
		return new Account("hash", accountHelper.findActiveAccountType(), accountHelper.findActiveAccountStatus(), 1L);
	}

}
