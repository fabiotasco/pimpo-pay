
package pay.pimpo.commons.dto;

/**
 * Tipo de documentos suportados pela solução.
 * 
 * @author fabio.tasco
 */
public enum SupportedDocumentType {

	CPF,
	CNPJ;

	public static String getAssociatedAccountType(final SupportedDocumentType type) {
		switch (type) {
			case CPF:
				return "Personal";
			case CNPJ:
				return "Business";
			default:
				throw new UnsupportedOperationException("Unsupported document type: " + type);
		}
	}

}
