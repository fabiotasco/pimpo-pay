
package pay.pimpo.commons.dto;

import javax.validation.constraints.NotNull;

public class DocumentDto {

	@NotNull
	private String value;

	@NotNull
	private SupportedDocumentType type;

	DocumentDto() {}

	public DocumentDto(final String value, final SupportedDocumentType type) {
		this.value = value;
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public SupportedDocumentType getType() {
		return type;
	}

}
