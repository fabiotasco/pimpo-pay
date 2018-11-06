
package pay.pimpo.account.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pay.pimpo.account.rules.AccountRules;
import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.dto.CreateAccountDto;
import pay.pimpo.commons.dto.FetchAccountsDto;
import pay.pimpo.commons.dto.FetchAccountsResponseDto;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.exceptions.AccountNotFoundException;

@RestController
@ResponseStatus(HttpStatus.OK)
class AccountController {

	@Autowired
	private AccountRules accountRules;

	@PostMapping("/create-account")
	Response<Account> createAccount(@RequestBody final CreateAccountDto createAccountDto) throws Exception {
		return new Response<>(accountRules.createAccount(createAccountDto));
	}

	@GetMapping("/{userId}")
	Response<Account> findByUserId(@PathVariable final Long userId) throws AccountNotFoundException {
		return new Response<>(accountRules.findByUserId(userId));
	}

	@PostMapping("/fetch-accounts")
	Response<FetchAccountsResponseDto> fetchAccounts(@RequestBody final FetchAccountsDto fetchAccountsDto)
		throws Exception {
		return new Response<>(accountRules.fetchAccounts(fetchAccountsDto));
	}

}
