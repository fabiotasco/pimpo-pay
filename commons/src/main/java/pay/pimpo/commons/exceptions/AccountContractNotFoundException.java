
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;
import pay.pimpo.commons.entities.AccountContractType;

/**
 * Lançada quando não encontrar o tipo de conta.
 *
 * @author fabio.tasco
 */
public class AccountContractNotFoundException extends PimpoPayException {

	private static final long serialVersionUID = -2386321783929987556L;

	private final AccountContractType contractType;

	public AccountContractNotFoundException(final AccountContractType contractType) {
		super("Account Contract not found: " + contractType, StandardErrors.ACCOUNT_CONTRACT_NOT_FOUND);
		this.contractType = contractType;
	}

	/**
	 * @return O tipo que não foi encontrado.
	 */
	public AccountContractType getContractType() {
		return contractType;
	}

}
