
package pay.pimpo.commons.exceptions;

/**
 * Lançada quando não encontrar a operadora.
 *
 * @author fabio.tasco
 */
public class NetworkOperatorNotFoundException extends Exception {

	private static final long serialVersionUID = -4128408247917093216L;

	private final String networkOperator;

	public NetworkOperatorNotFoundException(final String networkOperator) {
		super("Network Operator not found: " + networkOperator);
		this.networkOperator = networkOperator;
	}

	/**
	 * @return Operadora que não foi encontrada.
	 */
	public String getNetworkOperator() {
		return networkOperator;
	}

}
