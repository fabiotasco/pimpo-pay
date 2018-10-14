
package pay.pimpo.account.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pay.pimpo.account.rules.NetworkOperatorRules;
import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.entities.NetworkOperator;
import pay.pimpo.commons.exceptions.NetworkOperatorNotFoundException;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/network-operators")
public class NetworkOperatorController {

	@Autowired
	private NetworkOperatorRules networkOperatorRules;

	@GetMapping("/{name}")
	Response<NetworkOperator> findByName(@PathVariable final String name) throws NetworkOperatorNotFoundException {
		return new Response<>(networkOperatorRules.findByName(name));
	}

}
