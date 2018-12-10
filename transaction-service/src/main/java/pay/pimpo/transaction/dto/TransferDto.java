
package pay.pimpo.transaction.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

import pay.pimpo.commons.dto.DestinationAccountDto;
import pay.pimpo.commons.dto.HolderAccountDto;
import pay.pimpo.commons.entities.CurrencyType;

public class TransferDto {

	@NotNull
	private Date date;

	@NotNull
	@Positive
	private Double amount;

	@NotNull
	private CurrencyType currency;

	@NotNull
	@JsonProperty("destinationAccount")
	private DestinationAccountDto destinationAccountDto;

	@NotNull
	@JsonProperty("holderAccount")
	private HolderAccountDto holderAccountDto;

	TransferDto() {}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(final Double amount) {
		this.amount = amount;
	}

	public CurrencyType getCurrency() {
		return currency;
	}

	public void setCurrency(final CurrencyType currency) {
		this.currency = currency;
	}

	public DestinationAccountDto getDestinationAccountDto() {
		return destinationAccountDto;
	}

	public void setDestinationAccountDto(final DestinationAccountDto destinationAccountDto) {
		this.destinationAccountDto = destinationAccountDto;
	}

	public HolderAccountDto getHolderAccountDto() {
		return holderAccountDto;
	}

	public void setHolderAccountDto(final HolderAccountDto holderAccountDto) {
		this.holderAccountDto = holderAccountDto;
	}

	@Override
	public String toString() {
		return "TransferDto [date=" + date
			+ ", amount="
			+ amount
			+ ", currency="
			+ currency
			+ ", destinationAccountDto="
			+ destinationAccountDto
			+ ", holderAccountDto="
			+ holderAccountDto
			+ "]";
	}

}
