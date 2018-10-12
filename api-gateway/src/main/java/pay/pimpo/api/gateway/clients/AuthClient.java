
package pay.pimpo.api.gateway.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "http://localhost:9000/auth")
public interface AuthClient {

	@GetMapping("/authenticate")
	ResponseEntity<Void> auth(@RequestHeader("Authorization") String basicAuthentication);

}
