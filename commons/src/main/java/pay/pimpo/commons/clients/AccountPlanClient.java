
package pay.pimpo.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.entities.AccountPlan;

/**
 * @author fabio.tasco
 * @see AccountPlanController
 */
@FeignClient(name = "account-plan-service", url = "${api.client.account-service.url}")
public interface AccountPlanClient {

	@PostMapping("/plans/{name}")
	Response<AccountPlan> findByName(@PathVariable("name") final String name);

}
