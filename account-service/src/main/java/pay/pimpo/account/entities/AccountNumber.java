
package pay.pimpo.account.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Números de telefone vinculados a uma conta.
 *
 * @author fabio.tasco
 */
@Entity
public class AccountNumber implements Serializable {

	private static final long serialVersionUID = 5717761935751397319L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 14)
	private String number;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private AccountNumberStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Account account;

	AccountNumber() {}

	public AccountNumber(final String number, final AccountNumberStatus status) {
		this.number = number;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	public AccountNumberStatus getStatus() {
		return status;
	}

	public void setStatus(final AccountNumberStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (number == null ? 0 : number.hashCode());
		result = prime * result + (status == null ? 0 : status.hashCode());
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
		final AccountNumber other = (AccountNumber) obj;
		if (number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!number.equals(other.number)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AccountNumber [id=" + id + ", number=" + number + ", status=" + status + "]";
	}

}
