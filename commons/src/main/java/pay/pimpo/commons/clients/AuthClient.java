
package pay.pimpo.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "${api.client.auth-service.url}")
public interface AuthClient {

	@GetMapping("/authenticate")
	ResponseEntity<Void> auth(@RequestHeader("Authorization") String basicAuthentication);

}
