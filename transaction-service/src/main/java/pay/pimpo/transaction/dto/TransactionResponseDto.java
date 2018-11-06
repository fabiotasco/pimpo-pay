
package pay.pimpo.transaction.dto;

import pay.pimpo.transaction.entities.TransactionStatus;

public class TransactionResponseDto {

	private final TransactionStatus status;

	public TransactionResponseDto(final TransactionStatus status) {
		this.status = status;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return status.name();
	}

}
