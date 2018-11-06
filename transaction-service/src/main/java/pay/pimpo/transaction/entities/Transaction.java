
package pay.pimpo.transaction.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pay.pimpo.commons.entities.CurrencyType;
import pay.pimpo.commons.entities.PlanType;

@Entity
public class Transaction implements Serializable {

	private static final long serialVersionUID = -3019154321169210699L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, precision = 12, scale = 2)
	private Double amount;

	@Column(nullable = false)
	private Integer installments;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date date;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 3)
	private CurrencyType currencyType;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 55)
	private PlanType planType;

	/** Chave lógica para a tabela Account. */
	@Column(nullable = false)
	private Long holderAccountId;

	/** Chave lógica para a tabela Account. */
	@Column(nullable = false)
	private Long destinationAccountId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transaction")
	private List<TransactionEvent> events;

	Transaction() {}

	public Transaction(
		final Double amount,
		final Integer installments,
		final Date date,
		final CurrencyType currencyType,
		final PlanType planType,
		final Long holderAccountId,
		final Long destinationAccountId) {

		this.amount = amount;
		this.installments = installments;
		this.date = date;
		this.currencyType = currencyType;
		this.planType = planType;
		this.holderAccountId = holderAccountId;
		this.destinationAccountId = destinationAccountId;
		events = new ArrayList<>(1);
	}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public Integer getInstallments() {
		return installments;
	}

	public Date getDate() {
		return date;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public PlanType getPlanType() {
		return planType;
	}

	public Long getHolderAccountId() {
		return holderAccountId;
	}

	public Long getDestinationAccountId() {
		return destinationAccountId;
	}

	public List<TransactionEvent> getEvents() {
		return events;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (amount == null ? 0 : amount.hashCode());
		result = prime * result + (currencyType == null ? 0 : currencyType.hashCode());
		result = prime * result + (destinationAccountId == null ? 0 : destinationAccountId.hashCode());
		result = prime * result + (holderAccountId == null ? 0 : holderAccountId.hashCode());
		result = prime * result + (installments == null ? 0 : installments.hashCode());
		result = prime * result + (planType == null ? 0 : planType.hashCode());
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
		final Transaction other = (Transaction) obj;
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
		if (destinationAccountId == null) {
			if (other.destinationAccountId != null) {
				return false;
			}
		} else if (!destinationAccountId.equals(other.destinationAccountId)) {
			return false;
		}
		if (holderAccountId == null) {
			if (other.holderAccountId != null) {
				return false;
			}
		} else if (!holderAccountId.equals(other.holderAccountId)) {
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
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id
			+ ", amount="
			+ amount
			+ ", installments="
			+ installments
			+ ", date="
			+ date
			+ ", currencyType="
			+ currencyType
			+ ", planType="
			+ planType
			+ ", holderAccountId="
			+ holderAccountId
			+ ", destinationAccountId="
			+ destinationAccountId
			+ ", events="
			+ events
			+ "]";
	}

}
