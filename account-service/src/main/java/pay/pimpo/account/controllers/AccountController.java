
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
import pay.pimpo.commons.dto.DestinationAccountDto;
import pay.pimpo.commons.dto.FetchAccountsDto;
import pay.pimpo.commons.dto.FetchAccountsResponseDto;
import pay.pimpo.commons.dto.FetchHolderAccountDto;
import pay.pimpo.commons.dto.SumAmountDto;
import pay.pimpo.commons.dto.TransferBalanceDto;
import pay.pimpo.commons.entities.Account;

@RestController
@ResponseStatus(HttpStatus.OK)
class AccountController {

	@Autowired
	private AccountRules accountRules;

	@PostMapping("/create-account")
	Response<Account> createAccount(@RequestBody final CreateAccountDto createAccountDto) throws Exception {
		return new Response<>(accountRules.createAccount(createAccountDto));
	}

	/** Busca a conta portador de destino. */
	@PostMapping("/fetch-accounts")
	Response<FetchAccountsResponseDto> fetchAccounts(@RequestBody final FetchAccountsDto fetchAccountsDto)
		throws Exception {
		return new Response<>(accountRules.fetchAccounts(fetchAccountsDto));
	}

	/** Busca a conta portador. */
	@PostMapping("/fetch-holder-account")
	Response<Account> fetchHolderAccount(@RequestBody final FetchHolderAccountDto fetchHolderAccountDto)
		throws Exception {
		return new Response<>(accountRules.fetchHolderAccount(fetchHolderAccountDto));
	}

	@PostMapping("/fetch-destination-account")
	Response<Account> fetchDestinationAccount(@RequestBody final DestinationAccountDto destinationAccountDto)
		throws Exception {
		return new Response<>(accountRules.fetchDestinationAccount(destinationAccountDto));
	}

	@PostMapping("/transfer-balance")
	Response<Void> transferBalance(@RequestBody final TransferBalanceDto transferBalanceDto) throws Exception {
		return new Response<>(accountRules.transferBalance(transferBalanceDto));
	}

	@PostMapping("/sum-amount")
	Response<Void> sumAmount(@RequestBody final SumAmountDto sumAmountDto) throws Exception {
		return new Response<>(accountRules.sumAmount(sumAmountDto.getAccountId(), sumAmountDto.getAmount()));
	}

	@GetMapping("/{userId}")
	Response<Account> findByUserId(@PathVariable("userId") final Long userId) throws Exception {
		return new Response<>(accountRules.findActiveAccountByUserId(userId));
	}

}
