
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
@FeignClient(name = "account-service", url = "localhost:9010/account")
public interface AccountClient {

	// TODO: Colocar as URLs dos Clients no properties e apontar para o API Gateway

	@PostMapping("/create-account")
	Response<Account> createAccount(@RequestBody final CreateAccountDto createAccountDto);

}
