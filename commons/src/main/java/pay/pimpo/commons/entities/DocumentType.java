
package pay.pimpo.commons.entities;

/**
 * Tipo de documentos suportados.
 *
 * @author fabio.tasco
 */
public enum DocumentType {

	CPF(AccountContractType.PERSONAL),
	CNPJ(AccountContractType.BUSINESS);

	private AccountContractType associatedContractType;

	private DocumentType(final AccountContractType associatedContractType) {
		this.associatedContractType = associatedContractType;
	}

	public AccountContractType getAssociatedContractType() {
		return associatedContractType;
	}

}
