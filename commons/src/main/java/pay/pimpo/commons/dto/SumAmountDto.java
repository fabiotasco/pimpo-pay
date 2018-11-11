
package pay.pimpo.commons.dto;

public class SumAmountDto {

	private Long accountId;
	private Double amount;

	SumAmountDto() {}

	public SumAmountDto(final Long accountId, final Double amount) {
		this.accountId = accountId;
		this.amount = amount;
	}

	public Long getAccountId() {
		return accountId;
	}

	public Double getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "SumAmountDto [accountId=" + accountId + ", amount=" + amount + "]";
	}

}
