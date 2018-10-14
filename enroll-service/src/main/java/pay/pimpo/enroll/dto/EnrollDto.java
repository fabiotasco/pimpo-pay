
package pay.pimpo.enroll.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;

import pay.pimpo.commons.dto.DocumentDto;
import pay.pimpo.commons.dto.PhoneDto;

public class EnrollDto {

	@NotNull
	private String password;

	@JsonAlias("document")
	private DocumentDto documentDto;

	@JsonAlias("phone")
	private PhoneDto phoneDto;

	EnrollDto() {}

	public EnrollDto(final String password, final DocumentDto documentDto, final PhoneDto phoneDto) {
		this.password = password;
		this.documentDto = documentDto;
		this.phoneDto = phoneDto;
	}

	public String getPassword() {
		return password;
	}

	public DocumentDto getDocumentDto() {
		return documentDto;
	}

	public PhoneDto getPhoneDto() {
		return phoneDto;
	}

}
