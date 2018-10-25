
package pay.pimpo.commons.exceptions;

import pay.pimpo.commons.api.StandardErrors;

/**
 * Lançada quando não encontrar a operadora.
 *
 * @author fabio.tasco
 */
public class NetworkOperatorNotFoundException extends PimpoPayException {

	private static final long serialVersionUID = -4128408247917093216L;

	private final String networkOperator;

	public NetworkOperatorNotFoundException(final String networkOperator) {
		super("Network Operator not found: " + networkOperator, StandardErrors.NETWORK_OPERATOR_NOT_FOUND);
		this.networkOperator = networkOperator;
	}

	/**
	 * @return Operadora que não foi encontrada.
	 */
	public String getNetworkOperator() {
		return networkOperator;
	}

}
