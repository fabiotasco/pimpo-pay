
package pay.pimpo.transaction.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonAlias;

import pay.pimpo.commons.dto.HolderAccountDto;
import pay.pimpo.commons.entities.CurrencyType;

public class DepositDto {

	@NotNull
	private Date date;

	@NotNull
	@Positive
	private Double amount;

	@NotNull
	private CurrencyType currency;

	@NotNull
	@JsonAlias("holderAccount")
	private HolderAccountDto hodlerAccountDto;

	DepositDto() {}

	public Date getDate() {
		return date;
	}

	public Double getAmount() {
		return amount;
	}

	public CurrencyType getCurrency() {
		return currency;
	}

	public HolderAccountDto getHodlerAccountDto() {
		return hodlerAccountDto;
	}

	@Override
	public String toString() {
		return "DepositDto [date=" + date
			+ ", amount="
			+ amount
			+ ", currency="
			+ currency
			+ ", hodlerAccountDto="
			+ hodlerAccountDto
			+ "]";
	}

}
