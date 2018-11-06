
package pay.pimpo.account.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.account.repositories.NetworkOperatorRepository;
import pay.pimpo.account.rules.NetworkOperatorRules;
import pay.pimpo.commons.entities.NetworkOperator;
import pay.pimpo.commons.exceptions.NetworkOperatorNotFoundException;

@Component
public class AccountHelper {

	private static final String NETWORK_OPERATOR = "Network Operator";

	@Autowired
	private NetworkOperatorRepository networkOperatorRepository;

	@Autowired
	private NetworkOperatorRules networkOperatorRules;

	public void insertDomainValues() {
		networkOperatorRepository.save(new NetworkOperator(NETWORK_OPERATOR));
	}

	public NetworkOperator findOperatorNetwork() throws NetworkOperatorNotFoundException {
		return networkOperatorRules.findByName(NETWORK_OPERATOR);
	}

}
