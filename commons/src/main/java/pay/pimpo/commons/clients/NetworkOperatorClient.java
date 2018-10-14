
package pay.pimpo.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.entities.NetworkOperator;
import pay.pimpo.commons.exceptions.NetworkOperatorNotFoundException;

@FeignClient(name = "network-operator-service", url = "localhost:9010/account/network-operators")
public interface NetworkOperatorClient {

	@GetMapping("/{name}")
	Response<NetworkOperator> findByName(@PathVariable("name") final String name)
		throws NetworkOperatorNotFoundException;

}
