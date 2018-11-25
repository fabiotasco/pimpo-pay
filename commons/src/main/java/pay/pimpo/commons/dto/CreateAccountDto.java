
package pay.pimpo.commons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO usado para a criação de novas contas.
 *
 * @author fabio.tasco
 */
public class CreateAccountDto {

	@JsonProperty("document")
	private DocumentDto documentDto;

	@JsonProperty("phone")
	private PhoneDto phoneDto;

	private Long userId;

	CreateAccountDto() {}

	public CreateAccountDto(final DocumentDto documentDto, final PhoneDto phoneDto, final Long userId) {
		this.documentDto = documentDto;
		this.phoneDto = phoneDto;
		this.userId = userId;
	}

	public DocumentDto getDocumentDto() {
		return documentDto;
	}

	public PhoneDto getPhoneDto() {
		return phoneDto;
	}

	public Long getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "CreateAccountDto [documentDto=" + documentDto + ", phoneDto=" + phoneDto + ", userId=" + userId + "]";
	}

}
