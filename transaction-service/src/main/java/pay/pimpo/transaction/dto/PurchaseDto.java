
package pay.pimpo.transaction.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonProperty;

import pay.pimpo.commons.dto.DestinationAccountDto;
import pay.pimpo.commons.dto.HolderAccountDto;
import pay.pimpo.commons.entities.CurrencyType;
import pay.pimpo.commons.entities.PlanType;

public class PurchaseDto {

	@NotNull
	private Date date;

	@NotNull
	@Positive
	private Double amount;

	@NotNull
	private CurrencyType currency;

	@NotNull
	private PlanType plan;

	@NotNull
	@PositiveOrZero
	private Integer installments;

	@NotNull
	@JsonProperty("destinationAccount")
	private DestinationAccountDto destinationAccountDto;

	@NotNull
	@JsonProperty("holderAccount")
	private HolderAccountDto hodlerAccountDto;

	PurchaseDto() {}

	public Date getDate() {
		return date;
	}

	public Double getAmount() {
		return amount;
	}

	public CurrencyType getCurrency() {
		return currency;
	}

	public PlanType getPlan() {
		return plan;
	}

	public Integer getInstallments() {
		return installments;
	}

	public DestinationAccountDto getDestinationAccountDto() {
		return destinationAccountDto;
	}

	public HolderAccountDto getHodlerAccountDto() {
		return hodlerAccountDto;
	}

	@Override
	public String toString() {
		return "TransactionDto [date=" + date
			+ ", amount="
			+ amount
			+ ", currency="
			+ currency
			+ ", plan="
			+ plan
			+ ", installments="
			+ installments
			+ ", destinationAccountDto="
			+ destinationAccountDto
			+ ", hodlerAccountDto="
			+ hodlerAccountDto
			+ "]";
	}

}
