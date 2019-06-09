
package pay.pimpo.commons.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

	@Column(length = 14, nullable = false)
	private String number;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private NetworkOperator networkOperator;

	@Enumerated(EnumType.STRING)
	@Column(length = 55, nullable = false)
	private AccountNumberStatus status;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Account account;

	AccountNumber() {}

	public AccountNumber(
		final String number,
		final NetworkOperator networkOperator,
		final AccountNumberStatus status,
		final Account account) {

		this.number = number;
		this.networkOperator = networkOperator;
		this.status = status;
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public NetworkOperator getNetworkOperator() {
		return networkOperator;
	}

	public AccountNumberStatus getStatus() {
		return status;
	}

	public Account getAccount() {
		return account;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (number == null ? 0 : number.hashCode());
		result = prime * result + (networkOperator == null ? 0 : networkOperator.hashCode());
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
		if (networkOperator == null) {
			if (other.networkOperator != null) {
				return false;
			}
		} else if (!networkOperator.equals(other.networkOperator)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AccountNumber [id=" + id
			+ ", number="
			+ number
			+ ", operator="
			+ networkOperator
			+ ", status="
			+ status
			+ "]";
	}

}
