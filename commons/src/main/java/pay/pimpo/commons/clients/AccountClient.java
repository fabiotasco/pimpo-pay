
package pay.pimpo.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.dto.CreateAccountDto;
import pay.pimpo.commons.entities.Account;

/**
 * @author fabio.tasco
 * @see AccountController
 */
@FeignClient(name = "account-service", url = "${api.client.account-service.url}")
public interface AccountClient {

	@PostMapping("/create-account")
	Response<Account> createAccount(@RequestBody final CreateAccountDto createAccountDto);

}
