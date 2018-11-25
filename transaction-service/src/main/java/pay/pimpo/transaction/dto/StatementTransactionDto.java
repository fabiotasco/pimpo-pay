
package pay.pimpo.transaction.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import pay.pimpo.commons.entities.CurrencyType;
import pay.pimpo.commons.entities.PlanType;
import pay.pimpo.commons.entities.TransactionStatus;
import pay.pimpo.commons.entities.TransactionType;

@JsonInclude(Include.NON_EMPTY)
public class StatementTransactionDto {

	private final Long id;
	private final Date date;
	private final Double amount;
	private final CurrencyType currencyType;
	private final Integer installments;
	private final TransactionType type;
	private final PlanType planType;
	private final TransactionStatus status;
	private final String reasonCode;

	public StatementTransactionDto(
		final Long id,
		final Date date,
		final Double amount,
		final CurrencyType currencyType,
		final Integer installments,
		final TransactionType type,
		final PlanType planType,
		final TransactionStatus status,
		final String reasonCode) {

		this.id = id;
		this.date = date;
		this.amount = amount;
		this.currencyType = currencyType;
		this.installments = installments;
		this.type = type;
		this.planType = planType;
		this.status = status;
		this.reasonCode = reasonCode;
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public Double getAmount() {
		return amount;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public Integer getInstallments() {
		return installments;
	}

	public TransactionType getType() {
		return type;
	}

	public PlanType getPlanType() {
		return planType;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	@Override
	public String toString() {
		return "StatementTransactionDto [id=" + id
			+ ", date="
			+ date
			+ ", amount="
			+ amount
			+ ", currencyType="
			+ currencyType
			+ ", installments="
			+ installments
			+ ", type="
			+ type
			+ ", planType="
			+ planType
			+ ", status="
			+ status
			+ ", reasonCode="
			+ reasonCode
			+ "]";
	}

}
