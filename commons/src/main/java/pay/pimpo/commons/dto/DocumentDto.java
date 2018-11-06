
package pay.pimpo.commons.dto;

import javax.validation.constraints.NotNull;

import pay.pimpo.commons.entities.DocumentType;

public class DocumentDto {

	@NotNull
	private String value;

	@NotNull
	private DocumentType type;

	DocumentDto() {}

	public DocumentDto(final String value, final DocumentType type) {
		this.value = value;
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public DocumentType getType() {
		return type;
	}

}
