
package pay.pimpo.account.rules;

import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

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
import pay.pimpo.commons.entities.AccountNumberStatus;
import pay.pimpo.commons.entities.AccountStatus;
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

	@Transactional
	@Test(expected = ActiveAccountNumberNotUniqueException.class)
	public void testCheckAccountNumberUniqueness() throws Exception {
		final String number = "+5511999990000";
		final Account account = createTestAccount();
		final AccountNumberStatus activeNumberStatus = AccountNumberStatus.ACTIVE;

		final PhoneDto phoneDto = new PhoneDto(number, accountHelper.findOperatorNetwork().getName());
		account.getNumbers().add(accountNumberRules.createAccountNumber(phoneDto, activeNumberStatus, account));

		accountRepository.save(account);

		final boolean accountNumberActiveIsUnique = accountNumberRules.checkAccountNumberActiveUniqueness(number);
		assertTrue(accountNumberActiveIsUnique);
	}

	private Account createTestAccount() throws Exception {
		return new Account("hash", 0.0, AccountStatus.ACTIVE, 1L);
	}

}
