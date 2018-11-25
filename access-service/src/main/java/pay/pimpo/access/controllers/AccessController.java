
package pay.pimpo.access.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pay.pimpo.access.converters.AccessResponseDtoConverter;
import pay.pimpo.access.dto.AccessResponseDto;
import pay.pimpo.access.dto.EnrollDto;
import pay.pimpo.access.rules.AccessRules;
import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.dto.LoginDto;
import pay.pimpo.commons.entities.Account;

@RestController
@ResponseStatus(HttpStatus.OK)
public class AccessController {

	@Autowired
	private AccessRules accessRules;

	@Autowired
	private AccessResponseDtoConverter accessResponseDtoConverter;

	@PostMapping("/login")
	public Response<AccessResponseDto> login(@RequestBody @Valid final LoginDto loginDto) {
		return convertResponse(accessRules.login(loginDto));
	}

	@PostMapping("/enroll")
	public Response<AccessResponseDto> enrollment(@RequestBody final EnrollDto enrollDto) throws Exception {
		return convertResponse(accessRules.enroll(enrollDto));
	}

	private Response<AccessResponseDto> convertResponse(final Response<Account> response) {
		if (response.isSuccess()) {
			return new Response<>(accessResponseDtoConverter.convert(response.getContent()));
		}
		return new Response<>(response.getErrors());
	}

}
