
package pay.pimpo.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.dto.CreateAccountDto;
import pay.pimpo.commons.dto.DestinationAccountDto;
import pay.pimpo.commons.dto.FetchAccountsDto;
import pay.pimpo.commons.dto.FetchAccountsResponseDto;
import pay.pimpo.commons.dto.FetchHolderAccountDto;
import pay.pimpo.commons.dto.SumAmountDto;
import pay.pimpo.commons.dto.TransferBalanceDto;
import pay.pimpo.commons.entities.Account;

/**
 * @author fabio.tasco
 * @see AccountController
 */
@FeignClient(name = "account-service", url = "${api.client.account-service.url}")
public interface AccountClient {

	@PostMapping("/create-account")
	Response<Account> createAccount(@RequestBody CreateAccountDto createAccountDto);

	@PostMapping("/fetch-accounts")
	Response<FetchAccountsResponseDto> fetchAccounts(@RequestBody FetchAccountsDto fetchAccountsDto);

	@PostMapping("/fetch-holder-account")
	Response<Account> fetchHolderAccount(@RequestBody FetchHolderAccountDto fetchHolderAccountDto);

	@PostMapping("/fetch-destination-account")
	Response<Account> fetchDestinationAccount(@RequestBody final DestinationAccountDto destinationAccountDto);

	@PostMapping("/transfer-balance")
	Response<Void> transferBalance(@RequestBody TransferBalanceDto updateBalanceDto);

	@PostMapping("/sum-amount")
	Response<Void> sumAmount(@RequestBody SumAmountDto sumAmountDto);

	@GetMapping("/user/{userId}")
	Response<Account> findByUserId(@PathVariable("userId") final Long userId);

	@GetMapping("/{id}")
	Response<Account> findById(@PathVariable("id") final Long id);

}
