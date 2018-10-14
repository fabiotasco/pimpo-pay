
package pay.pimpo.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.dto.CreateUserDto;

@FeignClient(name = "user-service", url = "localhost:9000/auth")
public interface UserClient {

	@PostMapping("/users")
	Response<Long> createUser(@RequestBody final CreateUserDto createUserDto);

}
