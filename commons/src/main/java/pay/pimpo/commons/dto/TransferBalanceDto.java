
package pay.pimpo.commons.dto;

public class TransferBalanceDto {

	private Long holderAccountId;
	private Long destinationAccountId;
	private Double amount;

	TransferBalanceDto() {}

	public TransferBalanceDto(final Long holderAccountId, final Long destinationAccountId, final Double amount) {
		this.holderAccountId = holderAccountId;
		this.destinationAccountId = destinationAccountId;
		this.amount = amount;
	}

	public Long getHolderAccountId() {
		return holderAccountId;
	}

	public Long getDestinationAccountId() {
		return destinationAccountId;
	}

	public Double getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "TransferBalanceDto [holderAccountId=" + holderAccountId
			+ ", destinationAccountId="
			+ destinationAccountId
			+ ", amount="
			+ amount
			+ "]";
	}

}
