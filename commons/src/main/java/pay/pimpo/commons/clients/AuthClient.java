
package pay.pimpo.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import pay.pimpo.commons.api.Response;

/**
 * @author fabio.tasco
 * @see AuthController
 */
@FeignClient(name = "auth-service", url = "${api.client.auth-service.url}")
public interface AuthClient {

	String USER_ID_HEADER_KEY = "pp-user-id";

	@GetMapping("/authenticate")
	Response<Long> authenticate(@RequestHeader("Authorization") String basicAuthentication);

}
