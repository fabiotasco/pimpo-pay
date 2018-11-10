
package pay.pimpo.enroll.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.enroll.converters.EnrollResponseDtoConverter;
import pay.pimpo.enroll.dto.EnrollDto;
import pay.pimpo.enroll.dto.EnrollResponseDto;
import pay.pimpo.enroll.rules.EnrollRules;

@RestController
@ResponseStatus(HttpStatus.OK)
public class EnrollController {

	@Autowired
	private EnrollRules enrollRules;

	@Autowired
	private EnrollResponseDtoConverter enrollResponseDtoConverter;

	@PostMapping("/enrollment")
	public Response<EnrollResponseDto> enrollment(@RequestBody final EnrollDto enrollDto) throws Exception {
		final Response<Account> response = enrollRules.enroll(enrollDto);
		if (response.isSuccess()) {
			return new Response<>(enrollResponseDtoConverter.convert(response.getContent()));
		} else {
			return new Response<>(response.getErrors());
		}
	}

}
