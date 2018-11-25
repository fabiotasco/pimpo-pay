
package pay.pimpo.transaction.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import pay.pimpo.commons.dto.PaginationDto;

@JsonInclude(Include.NON_EMPTY)
public class StatementDto {

	private Double balance;

	@JsonProperty("transactions")
	private List<StatementTransactionDto> statementTransactionDtos;

	@JsonProperty("pagination")
	private PaginationDto paginationDto;

	StatementDto() {}

	public StatementDto(
		final Double balance,
		final List<StatementTransactionDto> statementTransactionDtos,
		final PaginationDto paginationDto) {
		this.balance = balance;
		this.statementTransactionDtos = statementTransactionDtos;
		this.paginationDto = paginationDto;
	}

	public Double getBalance() {
		return balance;
	}

	public List<StatementTransactionDto> getStatementTransactionDtos() {
		return statementTransactionDtos;
	}

	public PaginationDto getPaginationDto() {
		return paginationDto;
	}

	@Override
	public String toString() {
		return "StatementDto [balance=" + balance
			+ ", statementTransactionDtos="
			+ statementTransactionDtos
			+ ", paginationDto="
			+ paginationDto
			+ "]";
	}

}
