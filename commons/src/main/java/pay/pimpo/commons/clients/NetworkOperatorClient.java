
package pay.pimpo.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.entities.NetworkOperator;

@FeignClient(name = "network-operator-service", url = "${api.client.account-service.url}")
public interface NetworkOperatorClient {

	@GetMapping("/network-operators/{name}")
	Response<NetworkOperator> findByName(@PathVariable("name") final String name);

}
