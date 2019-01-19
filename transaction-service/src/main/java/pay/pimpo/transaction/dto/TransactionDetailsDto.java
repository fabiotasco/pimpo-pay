
package pay.pimpo.transaction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class TransactionDetailsDto {

	private final StatementTransactionDto statementTransactionDto;
	private final TransactionDetailsAccountDto holderAccountDto;
	private final TransactionDetailsAccountDto destinationAccountDto;

	public TransactionDetailsDto(
		final StatementTransactionDto statementTransactionDto,
		final TransactionDetailsAccountDto holderAccountDto,
		final TransactionDetailsAccountDto destinationAccountDto) {
		this.statementTransactionDto = statementTransactionDto;
		this.holderAccountDto = holderAccountDto;
		this.destinationAccountDto = destinationAccountDto;
	}

	public StatementTransactionDto getStatementTransactionDto() {
		return statementTransactionDto;
	}

	public TransactionDetailsAccountDto getHolderAccountDto() {
		return holderAccountDto;
	}

	public TransactionDetailsAccountDto getDestinationAccountDto() {
		return destinationAccountDto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (destinationAccountDto == null ? 0 : destinationAccountDto.hashCode());
		result = prime * result + (holderAccountDto == null ? 0 : holderAccountDto.hashCode());
		result = prime * result + (statementTransactionDto == null ? 0 : statementTransactionDto.hashCode());
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
		final TransactionDetailsDto other = (TransactionDetailsDto) obj;
		if (destinationAccountDto == null) {
			if (other.destinationAccountDto != null) {
				return false;
			}
		} else if (!destinationAccountDto.equals(other.destinationAccountDto)) {
			return false;
		}
		if (holderAccountDto == null) {
			if (other.holderAccountDto != null) {
				return false;
			}
		} else if (!holderAccountDto.equals(other.holderAccountDto)) {
			return false;
		}
		if (statementTransactionDto == null) {
			if (other.statementTransactionDto != null) {
				return false;
			}
		} else if (!statementTransactionDto.equals(other.statementTransactionDto)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TransactionDetailsResponseDto [statementTransactionDto=" + statementTransactionDto
			+ ", holderAccount="
			+ holderAccountDto
			+ ", destinationAccount="
			+ destinationAccountDto
			+ "]";
	}

}
