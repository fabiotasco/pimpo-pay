
package pay.pimpo.commons.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pay.pimpo.commons.builders.TransactionEventBuilder;

@Entity
public class TransactionEvent implements Serializable {

	private static final long serialVersionUID = 4575873266326809912L;

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 55)
	private TransactionStatus status;

	@Column(length = 8)
	private String reasonCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date createdAt;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(nullable = false)
	private Transaction transaction;

	/**
	 * Utilizar o Builder!
	 *
	 * @see TransactionEventBuilder
	 */
	public TransactionEvent() {}

	@PrePersist
	public void prePersist() {
		createdAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(final TransactionStatus status) {
		this.status = status;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(final String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(final Transaction transaction) {
		this.transaction = transaction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (reasonCode == null ? 0 : reasonCode.hashCode());
		result = prime * result + (status == null ? 0 : status.hashCode());
		result = prime * result + (transaction == null ? 0 : transaction.hashCode());
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
		final TransactionEvent other = (TransactionEvent) obj;
		if (reasonCode == null) {
			if (other.reasonCode != null) {
				return false;
			}
		} else if (!reasonCode.equals(other.reasonCode)) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		if (transaction == null) {
			if (other.transaction != null) {
				return false;
			}
		} else if (!transaction.equals(other.transaction)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TransactionEvent [id=" + id
			+ ", status="
			+ status
			+ ", reasonCode="
			+ reasonCode
			+ ", createdAt="
			+ createdAt
			+ ", transaction="
			+ transaction
			+ "]";
	}

}
