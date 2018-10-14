
package pay.pimpo.account.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.account.repositories.NetworkOperatorRepository;
import pay.pimpo.commons.entities.NetworkOperator;
import pay.pimpo.commons.exceptions.NetworkOperatorNotFoundException;

@Component
public class NetworkOperatorRules {

	@Autowired
	private NetworkOperatorRepository networkOperatorRepository;

	public NetworkOperator findByName(final String name) throws NetworkOperatorNotFoundException {
		final NetworkOperator networkOperator = networkOperatorRepository.findByName(name);
		if (networkOperator == null) {
			throw new NetworkOperatorNotFoundException(name);
		}
		return networkOperator;
	}

}
