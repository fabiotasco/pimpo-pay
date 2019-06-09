
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
	private final String reasonMessage;

	public StatementTransactionDto(
		final Long id,
		final Date date,
		final Double amount,
		final CurrencyType currencyType,
		final Integer installments,
		final TransactionType type,
		final PlanType planType,
		final TransactionStatus status,
		final String reasonCode,
		final String reasonMessage) {

		this.id = id;
		this.date = date;
		this.amount = amount;
		this.currencyType = currencyType;
		this.installments = installments;
		this.type = type;
		this.planType = planType;
		this.status = status;
		this.reasonCode = reasonCode;
		this.reasonMessage = reasonMessage;
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

	public String getReasonMessage() {
		return reasonMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (amount == null ? 0 : amount.hashCode());
		result = prime * result + (currencyType == null ? 0 : currencyType.hashCode());
		result = prime * result + (date == null ? 0 : date.hashCode());
		result = prime * result + (id == null ? 0 : id.hashCode());
		result = prime * result + (installments == null ? 0 : installments.hashCode());
		result = prime * result + (planType == null ? 0 : planType.hashCode());
		result = prime * result + (reasonCode == null ? 0 : reasonCode.hashCode());
		result = prime * result + (reasonMessage == null ? 0 : reasonMessage.hashCode());
		result = prime * result + (status == null ? 0 : status.hashCode());
		result = prime * result + (type == null ? 0 : type.hashCode());
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
		final StatementTransactionDto other = (StatementTransactionDto) obj;
		if (amount == null) {
			if (other.amount != null) {
				return false;
			}
		} else if (!amount.equals(other.amount)) {
			return false;
		}
		if (currencyType != other.currencyType) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (installments == null) {
			if (other.installments != null) {
				return false;
			}
		} else if (!installments.equals(other.installments)) {
			return false;
		}
		if (planType != other.planType) {
			return false;
		}
		if (reasonCode == null) {
			if (other.reasonCode != null) {
				return false;
			}
		} else if (!reasonCode.equals(other.reasonCode)) {
			return false;
		}
		if (reasonMessage == null) {
			if (other.reasonMessage != null) {
				return false;
			}
		} else if (!reasonMessage.equals(other.reasonMessage)) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
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
			+ ", reasonMessage="
			+ reasonMessage
			+ "]";
	}

}
