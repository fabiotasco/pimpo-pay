
package pay.pimpo.transaction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class TransactionDetailsAccountDto {

	private final String document;
	private final String number; // Número do telefone

	public TransactionDetailsAccountDto(final String document, final String number) {
		this.document = document;
		this.number = number;
	}

	public String getDocument() {
		return document;
	}

	public String getNumber() {
		return number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (document == null ? 0 : document.hashCode());
		result = prime * result + (number == null ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TransactionDetailsAccountDto other = (TransactionDetailsAccountDto) obj;
		if (document == null) {
			if (other.document != null) {
				return false;
			}
		} else if (!document.equals(other.document)) {
			return false;
		}
		if (number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!number.equals(other.number)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TransactionAccountDto [document=" + document + ", number=" + number + "]";
	}

}
