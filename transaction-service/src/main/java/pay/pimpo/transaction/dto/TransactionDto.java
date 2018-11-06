
package pay.pimpo.transaction.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonAlias;

import pay.pimpo.commons.dto.DestinationAccountDto;
import pay.pimpo.commons.dto.HolderAccountDto;
import pay.pimpo.commons.entities.PlanType;
import pay.pimpo.commons.entities.CurrencyType;

public class TransactionDto {

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
	@JsonAlias("destinationAccount")
	private DestinationAccountDto destinationAccountDto;

	@NotNull
	@JsonAlias("holderAccount")
	private HolderAccountDto hodlerAccountDto;

	TransactionDto() {}

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
