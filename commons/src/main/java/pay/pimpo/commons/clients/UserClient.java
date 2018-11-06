
package pay.pimpo.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.dto.CreateUserDto;

@FeignClient(name = "user-service", url = "${api.client.auth-service.url}")
public interface UserClient {

	@PostMapping("/users")
	Response<Long> createUser(@RequestBody final CreateUserDto createUserDto);

	@DeleteMapping("/users/{id}")
	Response<Long> deleteUser(@PathVariable("id") Long id);

	@GetMapping("/users/{username}")
	Response<Long> findByUsername(@PathVariable("username") String username);

}
