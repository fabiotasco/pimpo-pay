
package pay.pimpo.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import pay.pimpo.commons.api.Response;

@FeignClient(name = "auth-service", url = "${api.client.auth-service.url}")
public interface AuthClient {

	@GetMapping("/authenticate")
	Response<Long> authenticate(@RequestHeader("Authorization") String basicAuthentication);

}
