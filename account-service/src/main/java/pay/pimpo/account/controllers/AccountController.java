
package pay.pimpo.account.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pay.pimpo.account.rules.AccountRules;
import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.api.StandardErrors;
import pay.pimpo.commons.dto.CreateAccountDto;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.exceptions.ActiveAccountNumberNotUniqueException;

@RestController
@ResponseStatus(HttpStatus.OK)
class AccountController {

	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountRules accountRules;

	@PostMapping("/create-account")
	Response<Account> createAccount(@RequestBody final CreateAccountDto createAccountDto) throws Exception {
		try {
			return new Response<>(accountRules.createAccount(createAccountDto));

		} catch (final ActiveAccountNumberNotUniqueException e) {
			LOG.error("Error while creating account: {}.", createAccountDto.toString(), e);
			return new Response<>(StandardErrors.ACTIVE_ACCOUNT_NUMBER_NOT_UNIQUE);
		}
	}

}
